package Brick.Decorator;

import Brick.AbstractBrick;
import Brick.BrickDecorator;

public class Shield extends BrickDecorator {

    Shield(AbstractBrick brick) {
        super(brick);
    }

    @Override
    public int getSideProtected() {
        return 0;
    }

    @Override
    public void draw() {

    }

    @Override
    public Bonus destroy() {
        return null;
    }
}
