package com.ebicep.playerlocationsvisualizer.components.jpanel;

import com.ebicep.playerlocationsvisualizer.Main;
import com.ebicep.playerlocationsvisualizer.database.DatabaseManager;
import org.bson.Document;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

public class PlayerSelector extends JPanel implements ItemListener {

    public PlayerSelector() {
        setBorder(BorderFactory.createTitledBorder("Players"));
        for (Document document : DatabaseManager.gamesInformation.find()) {
            String game = DatabaseManager.getDocumentInfoWithDotNotation(document, "date") + " - " + DatabaseManager.getDocumentInfoWithDotNotation(document, "map");
            if(game.equals(Main.selectedGame)) {
                List<String> bluePlayers = new ArrayList<>();
                List<String> redPlayers = new ArrayList<>();
                for (Document o : ((ArrayList<Document>) DatabaseManager.getDocumentInfoWithDotNotation(document, "players.blue"))) {
                    bluePlayers.add((String) o.get("name"));
                }
                for (Document o : ((ArrayList<Document>) DatabaseManager.getDocumentInfoWithDotNotation(document, "players.red"))) {
                    redPlayers.add((String) o.get("name"));
                }
                for (String bluePlayer : bluePlayers) {
                    JCheckBox player = new JCheckBox(bluePlayer);
                    player.setSelected(true);
                    player.addItemListener(this);
                    add(player);
                }
                for (String redPlayer : redPlayers) {
                    JCheckBox player = new JCheckBox(redPlayer);
                    player.setSelected(true);
                    player.addItemListener(this);
                    add(player);
                }
                break;
            }
        }

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        Main.getMap();
    }
}
