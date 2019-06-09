package ch.heig.breakout.Brick.Decorator;

import ch.heig.breakout.Bonus;
import ch.heig.breakout.Breakout;
import ch.heig.breakout.Brick.AbstractBrick;
import ch.heig.breakout.Brick.BrickDecorator;
import ch.heig.breakout.Ressources.Images;

import java.awt.*;

public class Shield extends BrickDecorator {

    public Shield(AbstractBrick brick) {
        super(brick);
    }

    @Override
    protected void manageDamages(){
        Breakout.getInstance().removeBrick(this);
        Breakout.getInstance().addBrick(decoratedBrick);
    }

    public int getSideProtected() {
        return 0;
    }

    public void draw(Graphics g) {
        decoratedBrick.draw(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(Images.shield, decoratedBrick.getPosX(), decoratedBrick.getPosY(), null);
    }

    public Bonus destroy() {
        return null;
    }
}
