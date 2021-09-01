package com.ebicep.playerlocationsvisualizer.components.jpanel.maps;

import com.ebicep.playerlocationsvisualizer.DatabasePlayer;
import com.ebicep.playerlocationsvisualizer.Location;
import com.ebicep.playerlocationsvisualizer.Main;
import com.sun.scenario.animation.SplineInterpolator;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Path2D;
import java.awt.geom.QuadCurve2D;
import java.util.List;
import java.util.Objects;

public class Rift extends AbstractMap {

    public Rift() {
        super(new ImageIcon(Objects.requireNonNull(Main.class.getResource("/maps/riftmap.jpg"))), 6.075);
    }

    @Override
    public Dimension getPreferredSize() {
        return map == null ? new Dimension(1500, 1000) : new Dimension(map.getIconWidth(), map.getIconHeight());
    }

}
