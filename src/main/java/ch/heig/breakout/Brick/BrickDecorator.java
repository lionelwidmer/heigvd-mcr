package ch.heig.breakout.Brick;

import ch.heig.breakout.Breakout;

/**
 * @brief   : Abstract class BrickDecorator héritant de AbstractBrick
 * gérant les décorateur de brick
 */
public abstract class BrickDecorator extends AbstractBrick {

    protected AbstractBrick decoratedBrick;

    public BrickDecorator(Breakout board, int x, int y) {
        super(board, x, y);
    }

    public BrickDecorator(AbstractBrick brick) {
        super(brick.board, brick.getPosX(), brick.getPosY());
        this.decoratedBrick = brick;
    }
}
