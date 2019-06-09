package ch.heig.breakout.Brick.Decorator;

import ch.heig.breakout.Brick.AbstractBrick;
import ch.heig.breakout.Bonus;
import ch.heig.breakout.Brick.BrickDecorator;

import java.awt.*;

public class SideProtect extends BrickDecorator {

    SideProtect(AbstractBrick brick) {
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
