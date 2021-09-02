package com.ebicep.playerlocationsvisualizer.database;

import com.ebicep.playerlocationsvisualizer.DatabasePlayer;
import com.ebicep.playerlocationsvisualizer.Location;
import com.ebicep.playerlocationsvisualizer.Main;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DatabaseManager {

    public static boolean connected;
    public static MongoClient mongoClient;
    public static MongoDatabase warlordsGamesDatabase;
    public static MongoCollection<Document> gamesInformation;


    public static boolean isConnected() {
        return connected;
    }

    public static boolean connect() {
        try {
            System.out.println(System.getProperty("user.dir"));
            File myObj = new File(System.getProperty("user.dir") + "/database_key.TXT");
            Scanner myReader = new Scanner(myObj);
            if (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                mongoClient = MongoClients.create(data);
                warlordsGamesDatabase = mongoClient.getDatabase("Warlords_Games");
                gamesInformation = warlordsGamesDatabase.getCollection("Games_Information");
                connected = true;
                System.out.println("Connected");
                return true;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            connected = false;
        }
        return false;
    }

    public static Object getDocumentInfoWithDotNotation(Document document, String dots) throws MongoException {
        if (!connected) return null;

        String[] keys = dots.split("\\.");
        Document doc = document;

        for (int i = 0; i < keys.length - 1; i++) {
            Object o = doc.get(keys[i]);
            if (!(o instanceof Document)) {
                throw new MongoException(String.format("Field '%s' does not exist or s not a Document", keys[i]));
            }
            doc = (Document) o;
        }

        return doc.get(keys[keys.length - 1]);
    }

    public static void updateDatabasePlayers() {
        Main.databasePlayers.clear();
        for (Document document : gamesInformation.find()) {
            String game = DatabaseManager.getDocumentInfoWithDotNotation(document, "date") + " - " + DatabaseManager.getDocumentInfoWithDotNotation(document, "map");
            if(game.equals(Main.selectedGame)) {
                for (Document o : ((ArrayList<Document>) getDocumentInfoWithDotNotation(document, "players.blue"))) {
                    Main.databasePlayers.add(new DatabasePlayer((String) o.get("name"), (String) o.get("uuid"), (String) o.get("x_locations"), (String) o.get("z_locations"), Color.BLUE));
                }
                for (Document o : ((ArrayList<Document>) getDocumentInfoWithDotNotation(document, "players.red"))) {
                    Main.databasePlayers.add(new DatabasePlayer((String) o.get("name"), (String) o.get("uuid"), (String) o.get("x_locations"), (String) o.get("z_locations"), Color.RED));
                }
                break;
            }
        }
    }

}
