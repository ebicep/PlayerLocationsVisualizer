package com.ebicep.playerlocationsvisualizer.components.jcombobox;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MapSelector extends JComboBox<String> {

    private String[] maps = {"Rift", "Crossfire", "Valley"};

    public MapSelector() {
        for (String map : maps) {
            addItem(map);
        }
        setSelectedIndex(0);
        setEditable(false);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox<String> cb = (JComboBox<String>) e.getSource();
        String map = (String) cb.getSelectedItem();
        System.out.println(map);
    }
}
