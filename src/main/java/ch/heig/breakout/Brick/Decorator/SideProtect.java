package ch.heig.breakout.Brick.Decorator;

import ch.heig.breakout.Brick.AbstractBrick;
import ch.heig.breakout.Bonus;
import ch.heig.breakout.Brick.BrickDecorator;
import ch.heig.breakout.Player.Ball;

import java.awt.*;

/**
 * @brief   : Décorateur de brick protégeant un de ses côtés
 * (impossible à détruire de ce côté)
 */
public class SideProtect extends BrickDecorator {

    public SideProtect(AbstractBrick brick) {
        super(brick);
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        //g2.setPaint(Images.rays);
        g2.setPaint(Color.GRAY);
        g2.fill3DRect(posX, posY, WIDTH, HEIGHT, true);
    }

    @Override
    protected void manageDamages(){
        if(lastTouchedSide == Side.top || lastTouchedSide == Side.bottom) board.removeBrick(this);
    }

    public Rectangle getHitbox() {
        return decoratedBrick.getHitbox();
    }
}
