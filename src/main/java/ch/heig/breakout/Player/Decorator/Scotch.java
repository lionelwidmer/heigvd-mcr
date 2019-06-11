package ch.heig.breakout.Player.Decorator;

import ch.heig.breakout.Player.AbstractBar;

import java.awt.*;

public class Scotch extends AbstractBar {
    public int getLength() {
        return 0;
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
