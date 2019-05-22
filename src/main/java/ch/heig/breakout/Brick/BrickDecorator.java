package ch.heig.breakout.Brick;

public abstract class BrickDecorator implements AbstractBrick {

    AbstractBrick brickDecorated;

   protected BrickDecorator(AbstractBrick brick) {
        this.brickDecorated = brick;

    }
}
