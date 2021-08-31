package com.ebicep.playerlocationsvisualizer.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.File;
import java.io.FileNotFoundException;
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
                return true;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            connected = false;
        }
        return false;
    }
}
