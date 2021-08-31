package com.ebicep.playerlocationsvisualizer;


import com.ebicep.playerlocationsvisualizer.components.jpanel.maps.Rift;
import com.ebicep.playerlocationsvisualizer.database.DatabaseManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Main {

    public static void main(String[] args) {
        System.out.println("here");
        CompletableFuture.supplyAsync(DatabaseManager::connect)
                .thenAccept(Main::createAndShowGUI);
    }

    private static void createAndShowGUI(boolean connected) {
        System.out.println("?");
        JFrame f = new JFrame("PlayerLocationsVisualizer");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setLayout(new FlowLayout());
        List<Location> locations = new ArrayList<>();
        String x = "-80,-59,-40,-21,-1,17,37,45,53,66,86,66,49,31,14,-5,-24,-39,-48,-54,-67,-85,-94,-74,-65,-56,-29,-10,12,";
        String z = "-26,-28,-28,-24,-14,-1,5,-14,0,17,23,18,8,5,-4,-7,-8,2,16,-2,-17,-23,-17,-28,-8,26,35,70,52,";
        List<Integer> xInt = new ArrayList<>();
        List<Integer> zInt = new ArrayList<>();
        for (String s : x.split(",")) {
            xInt.add(Integer.parseInt(s));
        }
        for (String s : z.split(",")) {
            zInt.add(Integer.parseInt(s));
        }
        for (int i = 0; i < xInt.size(); i++) {
            locations.add(new Location(xInt.get(i), zInt.get(i)));
        }
        f.add(new Rift(locations));

        JButton button = new JButton();
        button.setSize(10, 5);
        f.add(button);
        JComboBox<String> comboBox = new JComboBox<>();
        f.add(comboBox);

        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
