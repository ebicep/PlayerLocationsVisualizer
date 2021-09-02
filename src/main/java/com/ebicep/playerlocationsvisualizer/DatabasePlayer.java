package com.ebicep.playerlocationsvisualizer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DatabasePlayer {

    private String name;
    private String uuid;
    private String xLocations;
    private String zLocations;
    private boolean show = true;
    private Color color;

    public DatabasePlayer(String name, String uuid, String xLocations, String zLocations, Color color) {
        this.name = name;
        this.uuid = uuid;
        this.xLocations = xLocations;
        this.zLocations = zLocations;
        this.color = color;
    }

    public List<Location> getLocations() {
        List<Location> locations = new ArrayList<>();
        List<Integer> xInt = new ArrayList<>();
        List<Integer> zInt = new ArrayList<>();
        for (String s : xLocations.split(",")) {
            xInt.add(Integer.parseInt(s));
        }
        for (String s : zLocations.split(",")) {
            zInt.add(Integer.parseInt(s));
        }
        for (int i = 0; i < xInt.size(); i++) {
            locations.add(new Location(xInt.get(i), zInt.get(i)));
        }
        return locations;
    }

    public String getName() {
        return name;
    }

    public String getUuid() {
        return uuid;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
