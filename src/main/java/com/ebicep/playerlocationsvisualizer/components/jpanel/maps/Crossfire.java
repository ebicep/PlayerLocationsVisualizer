package com.ebicep.playerlocationsvisualizer.components.jpanel.maps;

import com.ebicep.playerlocationsvisualizer.Main;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Crossfire extends AbstractMap {

    public Crossfire() {
        super(new ImageIcon(Objects.requireNonNull(Main.class.getResource("/maps/crossfiremap.jpg"))), 6.075);
    }

    @Override
    public Dimension getPreferredSize() {
        return map == null ? new Dimension(2000, 1000) : new Dimension((int) (map.getIconWidth() * .85), (int) (map.getIconHeight() * .85));
    }
}
