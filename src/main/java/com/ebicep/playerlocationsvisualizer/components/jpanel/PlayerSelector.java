package com.ebicep.playerlocationsvisualizer.components.jpanel;

import com.ebicep.playerlocationsvisualizer.Main;
import com.ebicep.playerlocationsvisualizer.database.DatabaseManager;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

public class PlayerSelector extends JPanel implements ItemListener {

    private List<JCheckBox> checkBoxes = new ArrayList<>();

    public PlayerSelector() {
        setName("Players");
        setBorder(BorderFactory.createTitledBorder("Players"));
        setLayout(new GridBagLayout());
        resetPlayers();
    }

    public void resetPlayers() {
        removeAll();
        checkBoxes.clear();
        GridBagConstraints gc;
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
                int x = 0;
                int y = 0;
                List<String> players = new ArrayList<>();
                players.addAll(bluePlayers);
                players.addAll(redPlayers);
                for (String p : players) {
                    gc = new GridBagConstraints();
                    gc.fill = GridBagConstraints.HORIZONTAL;
                    gc.gridx = x;
                    gc.gridy = y;
                    JCheckBox player = new JCheckBox(p);
                    player.setSelected(true);
                    player.addItemListener(this);
                    if(bluePlayers.contains(p)) {
                        player.setForeground(new Color(0, 0 ,255));
                    } else {
                        player.setForeground(new Color(255, 0 ,0));
                    }
                    add(player, gc);
                    checkBoxes.add(player);

                    x++;
                    if(x >= 3) {
                        x = 0;
                        y++;
                    }
                }
                break;
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        JCheckBox checkBox = (JCheckBox) e.getItem();
        Main.databasePlayers.stream().filter(p -> p.getName().equals(checkBox.getText())).forEach(db -> db.setShow(!db.isShow()));
        Main.reload();
    }

    public List<JCheckBox> getCheckBoxes() {
        return checkBoxes;
    }
}
