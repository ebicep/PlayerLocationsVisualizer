package com.ebicep.playerlocationsvisualizer.components.jpanel;

import com.ebicep.playerlocationsvisualizer.components.jcombobox.GameSelector;
import com.ebicep.playerlocationsvisualizer.components.jcombobox.MapSelector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Options extends JPanel {

    JButton button = new JButton("Reload");
    JComboBox<String> mapSelector = new MapSelector();
    JComboBox<String> gameSelector = new GameSelector();

    public Options(LayoutManager layout) {
        super(layout);

        button.setSize(20, 10);
        button.addActionListener(e -> {
            System.out.println("CLICKED");
        });

        JPanel options = new JPanel();
        options.add(button);
        //options.add(mapSelector);
        options.add(gameSelector);
        add(options);
    }


}
