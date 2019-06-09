package ch.heig.breakout.Player;

import java.awt.*;

public class scotch extends AbstractBar {
    public int getLength() {
        return 0;
    }

    public boolean shoot() {
        return true;
    }

    public void draw(Graphics g) {
        this.draw(g);
    }

    public void move(int dist) {

    }

    public boolean scotch() {
        return false;
    }
}
