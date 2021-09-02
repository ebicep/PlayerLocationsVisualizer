package com.ebicep.playerlocationsvisualizer.components.jpanel;

import com.ebicep.playerlocationsvisualizer.Main;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PlayerOptions extends JPanel {

    public PlayerOptions() {
        setName("PlayerOptions");
        setBorder(new CompoundBorder(BorderFactory.createTitledBorder("PlayerOptions"), new EmptyBorder(5, 0, 5, 0)));
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(2, 2, 2, 2);
        JButton showAllPlayers = new JButton("Show All");
        showAllPlayers.addActionListener(e -> Main.options.getPlayerSelector().getCheckBoxes().forEach(jCheckBox -> jCheckBox.setSelected(true)));
        add(showAllPlayers, gc);

        gc = new GridBagConstraints();
        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(2, 2, 2, 2);
        JButton showNoPlayers = new JButton("Show None");
        showNoPlayers.addActionListener(e -> Main.options.getPlayerSelector().getCheckBoxes().forEach(jCheckBox -> jCheckBox.setSelected(false)));
        add(showNoPlayers, gc);

        gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 1;
        gc.gridwidth = 2;
        //gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(5, 2, 2, 2);
        JButton randomizeColor = new JButton("Randomize Color");
        randomizeColor.addActionListener(e -> {
            Main.databasePlayers.forEach(databasePlayer -> {
                databasePlayer.setColor(new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
            });
            Main.reload();
        });
        add(randomizeColor, gc);
    }

}
