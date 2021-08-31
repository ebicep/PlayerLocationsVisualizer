package com.ebicep.playerlocationsvisualizer;

public class Location {
    private int x;
    private int z;

    public Location(int x, int z) {
        this.x = x;
        this.z = z;
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", z=" + z +
                '}';
    }

    public int getX() {
        return x;
    }

    public int getXScaled(double scale, int offset) {
        return (int) (this.x * scale + offset);
    }

    public int getZ() {
        return z;
    }

    public int getYScaled(double scale, int offset) {
        return (int) (this.z * scale + offset);
    }
}
