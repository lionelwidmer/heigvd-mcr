package ch.heig.breakout.Brick;

import ch.heig.breakout.Bonus;

public class Brick implements AbstractBrick {

    public int getSideProtected() {
        return 0;
    }

    public void draw() {

    }

    public Bonus destroy() {
        return null;
    }
}
