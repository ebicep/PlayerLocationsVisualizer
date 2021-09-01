package com.ebicep.playerlocationsvisualizer.components.jpanel.maps;

import com.ebicep.playerlocationsvisualizer.DatabasePlayer;
import com.ebicep.playerlocationsvisualizer.Location;
import com.ebicep.playerlocationsvisualizer.Main;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class AbstractMap extends JPanel {

    protected ImageIcon map;
    protected final int xOffset;
    protected final int yOffset;
    protected final double scale;

    public AbstractMap(ImageIcon map, double scale) {
        this.map = map;
        this.scale = scale;
        this.xOffset = map.getIconWidth() / 2 + 6;
        this.yOffset = map.getIconHeight() / 2 - 7;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        if (map != null) {
            int x = (getWidth() - map.getIconWidth()) / 2;
            int y = (getHeight() - map.getIconHeight()) / 2;
            g2d.drawImage(map.getImage(), x, y, this);

            g2d.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.setColor(Color.RED);
            g2d.setStroke(new BasicStroke(3));
            g2d.translate(x, y);
            int dimensions = 10;
            for (DatabasePlayer databasePlayer : Main.databasePlayers) {
                List<Location> locations = databasePlayer.getLocations();
                for (int i = 0; i < locations.size(); i++) {
                    Location location = locations.get(i);
                    if(i != 0) {
                        Location previousLocation = locations.get(i - 1);
                        g2d.setColor(Color.BLACK);
                        g2d.drawLine((int) (location.getX() * scale) + xOffset, (int) (location.getZ() * scale) + yOffset, (int) (previousLocation.getX() * scale) + xOffset, (int) (previousLocation.getZ() * scale) + yOffset);
                    }
                    if(i == 0) {
                        g2d.setColor(Color.GREEN);
                        g2d.fillOval((int) (location.getX() * scale) + xOffset - dimensions/2, (int) (location.getZ() * scale) + yOffset - dimensions/2, dimensions, dimensions);
                    } else if(i == locations.size() - 1) {
                        g2d.setColor(Color.RED);
                        g2d.fillOval((int) (location.getX() * scale) + xOffset - dimensions/2, (int) (location.getZ() * scale) + yOffset - dimensions/2, dimensions, dimensions);
                    }

//                if(i < locations.size() - 2) {
//                    Location locationAfter1 = locations.get(i + 1);
//                    Location locationAfter2 = locations.get(i + 2);
//
//                    QuadCurve2D q = new QuadCurve2D.Double();
//                    q.setCurve(location.getXScaled(scale, xOffset), location.getYScaled(scale, yOffset), locationAfter1.getXScaled(scale, xOffset), locationAfter1.getYScaled(scale, yOffset), locationAfter2.getXScaled(scale, xOffset), locationAfter2.getYScaled(scale, yOffset));
//                    g2d.draw(q);
//
//                    i++;
//                }


                }
                g2d.setColor(Color.BLUE);
//            g2d.fillOval((int) ((-96.5 * 6.075) + xOffset), (int) ((-17.5 * 6.075) + yOffset), 5,5);
//            g2d.fillRect((xOffset) - 1, yOffset - 1, 2,2);

            }

        }

        g2d.dispose();
    }
}