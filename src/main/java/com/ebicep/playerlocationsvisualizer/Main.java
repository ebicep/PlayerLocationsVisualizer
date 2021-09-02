package com.ebicep.playerlocationsvisualizer;


import com.ebicep.playerlocationsvisualizer.components.jcombobox.GameSelector;
import com.ebicep.playerlocationsvisualizer.components.jpanel.Options;
import com.ebicep.playerlocationsvisualizer.components.jpanel.PlayerSelector;
import com.ebicep.playerlocationsvisualizer.components.jpanel.maps.AbstractMap;
import com.ebicep.playerlocationsvisualizer.components.jpanel.maps.Rift;
import com.ebicep.playerlocationsvisualizer.database.DatabaseManager;
import org.bson.Document;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static final JFrame f = new JFrame("PlayerLocationsVisualizer");
    public static String selectedGame;
    public static List<DatabasePlayer> databasePlayers = new ArrayList<>();
    public static AbstractMap map;
    public static Options options;
    public static HashMap<String, Image> playerHeads = new HashMap<>();

    public static void main(String[] args) {
        Logger.getLogger( "org.mongodb.driver" ).setLevel(Level.SEVERE);
        DatabaseManager.connect();
        for (Document document : DatabaseManager.gamesInformation.find()) {
            for (Document o : ((ArrayList<Document>) DatabaseManager.getDocumentInfoWithDotNotation(document, "players.blue"))) {
                if(!Main.playerHeads.containsKey((String) o.get("uuid"))) {
                    try {
                        Main.playerHeads.put((String) o.get("uuid"), ImageIO.read(new URL("https://crafatar.com/avatars/" + o.get("uuid"))));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            for (Document o : ((ArrayList<Document>) DatabaseManager.getDocumentInfoWithDotNotation(document, "players.red"))) {
                if(!Main.playerHeads.containsKey((String) o.get("uuid"))) {
                    try {
                        Main.playerHeads.put((String) o.get("uuid"), ImageIO.read(new URL("https://crafatar.com/avatars/" + o.get("uuid"))));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        while (!DatabaseManager.connected) {
        }
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(true);

        Main.map = new Rift();
        Main.options = new Options();

        f.setLayout(new FlowLayout());
        f.add(map);
        f.add(options);

        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public static void addMap(AbstractMap map) {
        Main.map = map;
        options.getPlayerSelector().resetPlayers();
        reload();
    }

    public static void reload() {
        f.revalidate();
        f.repaint();
        f.pack();
    }

}
