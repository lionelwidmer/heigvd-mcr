package ch.heig.breakout.Brick.Decorator;

import ch.heig.breakout.Bonus;
import ch.heig.breakout.Brick.AbstractBrick;
import ch.heig.breakout.Brick.BrickDecorator;

import java.awt.*;

public class Shield extends BrickDecorator {

    Shield(AbstractBrick brick) {
        super(brick);
    }

    public int getSideProtected() {
        return 0;
    }

    public void draw(Graphics g) {

    }

    public Bonus destroy() {
        return null;
    }
}
