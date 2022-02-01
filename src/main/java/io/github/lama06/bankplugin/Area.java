package io.github.lama06.bankplugin;

public class Area {
    public int x1;
    public int y1;
    public int z1;

    public int x2;
    public int y2;
    public int z2;

    public Area(int x1, int y1, int z1, int x2, int y2, int z2) {
        this.x1 = x1;
        this.y1 = y1;
        this.z1 = z1;
        this.x2 = x2;
        this.y2 = y2;
        this.z2 = z2;
    }

    public boolean isInside(int x, int y, int z) {
        return x >= x1 && x <= x2 &&
                y >= y1 && y <= y2 &&
                z >= z1 && z <= z2;
    }
}
