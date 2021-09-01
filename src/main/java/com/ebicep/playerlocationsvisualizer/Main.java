package com.ebicep.playerlocationsvisualizer;


import com.ebicep.playerlocationsvisualizer.components.jcombobox.GameSelector;
import com.ebicep.playerlocationsvisualizer.components.jcombobox.MapSelector;
import com.ebicep.playerlocationsvisualizer.components.jpanel.Options;
import com.ebicep.playerlocationsvisualizer.components.jpanel.PlayerSelector;
import com.ebicep.playerlocationsvisualizer.components.jpanel.maps.AbstractMap;
import com.ebicep.playerlocationsvisualizer.components.jpanel.maps.Crossfire;
import com.ebicep.playerlocationsvisualizer.components.jpanel.maps.Rift;
import com.ebicep.playerlocationsvisualizer.database.DatabaseManager;

import javax.smartcardio.Card;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static final JFrame f = new JFrame("PlayerLocationsVisualizer");
    public static String selectedGame;
    public static List<JPanel> jPanels = new ArrayList<>();
    public static List<DatabasePlayer> databasePlayers = new ArrayList<>();

    public static void main(String[] args) {
        Logger.getLogger( "org.mongodb.driver" ).setLevel(Level.SEVERE);
        DatabaseManager.connect();
        while (!DatabaseManager.connected) {
        }
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);

        JPanel options = new JPanel();
        options.setName("Options");
        options.add(new GameSelector());
        options.setBorder(new EmptyBorder(new Insets(10, 15, 10, 15)));
        options.add(Box.createRigidArea(new Dimension(0, 10)));
        options.add(new PlayerSelector());
        options.add(Box.createRigidArea(new Dimension(0, 500)));

        DatabaseManager.updateDatabasePlayers();

        JPanel map = new JPanel();
        map.setName("Map");
        map.setBorder(new CompoundBorder(BorderFactory.createTitledBorder("Map"), new EmptyBorder(5, 5, 5, 5)));
        map.setMaximumSize(new Dimension(2000, 1000));
        map.add(new Rift());


        BoxLayout layout1 = new BoxLayout(map, BoxLayout.Y_AXIS);
        BoxLayout layout2 = new BoxLayout(options, BoxLayout.Y_AXIS);
        map.setLayout(layout1);
        options.setLayout(layout2);

        jPanels.add(map);
        jPanels.add(options);

        f.setLayout(new FlowLayout());
        f.add(map);
        f.add(options);

        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public static void addMap(AbstractMap map) {
        JPanel jPanel = getMap();
        for (Component component : Objects.requireNonNull(jPanel).getComponents()) {
            if(component.getClass().getGenericSuperclass().equals(AbstractMap.class)) {
                jPanel.remove(component);
                jPanel.add(map);
                jPanel.revalidate();
                jPanel.repaint();
                f.pack();
            }
        }
    }

    public static JPanel getMap() {
        for (JPanel jPanel : jPanels) {
            if(jPanel.getName().equals("Map")) {
                return jPanel;
            }
        }
        return null;
    }

}
