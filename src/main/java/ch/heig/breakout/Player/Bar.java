package ch.heig.breakout.Player;

import java.awt.*;

public class Bar extends AbstractBar {
    private final int MARGEIN = 85;

    public Bar(int x, int y){
        super();
        posX = (x-getLength())/2;
        posY = y - MARGEIN;
    }

    public int getLength() {
        return 160;
    }

    public boolean shoot() {
        return false;
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fill3DRect(posX, posY, getLength(), HEIGHT, true);
    }

    public void move(int dist) {
        posX += dist;
    }

    public boolean scotch() {
        return false;
    }
}
