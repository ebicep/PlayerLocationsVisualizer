package com.ebicep.playerlocationsvisualizer.components.jcombobox;

import com.ebicep.playerlocationsvisualizer.Main;
import com.ebicep.playerlocationsvisualizer.components.jpanel.maps.Crossfire;
import com.ebicep.playerlocationsvisualizer.components.jpanel.maps.Rift;
import com.ebicep.playerlocationsvisualizer.database.DatabaseManager;
import org.bson.Document;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;

public class GameSelector extends JComboBox<String> {

    public GameSelector() {
        setBorder(new CompoundBorder(BorderFactory.createTitledBorder("Games"), new EmptyBorder(5, 5, 5, 5)));

        for (Document document : DatabaseManager.gamesInformation.find()) {
            addItem(DatabaseManager.getDocumentInfoWithDotNotation(document, "date") + " - " + DatabaseManager.getDocumentInfoWithDotNotation(document, "map"));
        }
        Main.selectedGame = getItemAt(getItemCount() - 1);
        setSelectedIndex(getItemCount() - 1);
        setEditable(false);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        String game = (String) cb.getSelectedItem();
        Main.selectedGame = game;
        assert game != null;
        String map = game.substring(game.indexOf("-") + 2);
        switch (map) {
            case "Rift":
                Main.addMap(new Rift());
                break;
            case "Crossfire":
                Main.addMap(new Crossfire());
                break;
        }
        System.out.println(map);
        System.out.println(Main.getMap());
    }
}
