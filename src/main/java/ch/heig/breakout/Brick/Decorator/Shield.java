package ch.heig.breakout.Brick.Decorator;

import ch.heig.breakout.Brick.AbstractBrick;
import ch.heig.breakout.Brick.BrickDecorator;
import ch.heig.breakout.Ressources.Images;

import java.awt.*;

/**
 * @brief   : Décorateur de brick lui ajoutant un bouclier
 * (doit être touché 2 fois)
 */
public class Shield extends BrickDecorator {

    public Shield(AbstractBrick brick) {
        super(brick);
    }

    @Override
    protected void manageDamages(){
        board.addBrick(decoratedBrick);
        board.removeBrick(this);
    }

    public void draw(Graphics g) {
        decoratedBrick.draw(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(Images.shield, decoratedBrick.getPosX(), decoratedBrick.getPosY(), null);
    }

    public Rectangle getHitbox() {
        return decoratedBrick.getHitbox();
    }
}
