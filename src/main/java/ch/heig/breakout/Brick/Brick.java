package ch.heig.breakout.Brick;

import ch.heig.breakout.Bonus;
import ch.heig.breakout.Breakout;
import ch.heig.breakout.Player.Ball;

import java.awt.*;

public class Brick extends AbstractBrick {

    public Brick(Breakout board, int x, int y){
        super(board, x, y);
    }

    public int getSideProtected() {
        return 0;
    }

    public void draw(Graphics g) {
        Color color = new Color(0,50,(210-posY/2));

        g.setColor(color);
        g.fill3DRect(posX,posY, WIDTH, HEIGHT, true);
    }

    public Bonus destroy() {
        return null;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
}
