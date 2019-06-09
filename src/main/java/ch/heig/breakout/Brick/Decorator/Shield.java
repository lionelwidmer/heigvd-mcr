package ch.heig.breakout.Brick.Decorator;

import ch.heig.breakout.Bonus;
import ch.heig.breakout.Brick.AbstractBrick;
import ch.heig.breakout.Brick.BrickDecorator;
import ch.heig.breakout.Ressources.Images;
import org.w3c.dom.css.Rect;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Shield extends BrickDecorator {

    public Shield(AbstractBrick brick) {
        super(brick);
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
