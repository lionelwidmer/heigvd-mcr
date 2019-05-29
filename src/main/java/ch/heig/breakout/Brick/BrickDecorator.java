package ch.heig.breakout.Brick;

public abstract class BrickDecorator extends AbstractBrick {

    AbstractBrick brickDecorated;

    public BrickDecorator(int x, int y) {
        super(x, y);
    }

    public BrickDecorator(AbstractBrick brick) {
        super();
        this.brickDecorated = brick;
    }
}
