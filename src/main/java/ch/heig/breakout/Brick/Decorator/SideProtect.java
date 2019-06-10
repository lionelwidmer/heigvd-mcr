package ch.heig.breakout.Brick.Decorator;

import ch.heig.breakout.Brick.AbstractBrick;
import ch.heig.breakout.Bonus;
import ch.heig.breakout.Brick.BrickDecorator;
import ch.heig.breakout.Player.Ball;

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

    @Override
    protected void manageDamages(Ball ball){
        if(lastTouchedSide == Side.top || lastTouchedSide == Side.bottom) board.removeBrick(this);
    }

    public Rectangle getHitbox() {
        return decoratedBrick.getHitbox();
    }
}
