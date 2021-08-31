package com.ebicep.playerlocationsvisualizer.components.jpanel.maps;

import com.ebicep.playerlocationsvisualizer.Location;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class AbstractMap extends JPanel {

    protected ImageIcon map;
    protected List<Location> locations;

    public AbstractMap(List<Location> locations) {
        this.locations = locations;
    }
    
    @Override
    public Dimension getPreferredSize() {
        return map == null ? new Dimension(200, 200) : new Dimension(map.getIconWidth(), map.getIconHeight());
    }

}