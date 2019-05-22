package Brick;

public abstract class BrickDecorator implements AbstractBrick {

    AbstractBrick brickDecorated;

    BrickDecorator(AbstractBrick brick) {
        this.brickDecorated = brick;

    }
}
