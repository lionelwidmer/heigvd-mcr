package ch.heig.breakout.Brick;

import ch.heig.breakout.Breakout;

import java.awt.*;

/**
 * @brief   : Classe brick héritant de AbstractBrick permettant de gérer
 * les briques
 */
public class Brick extends AbstractBrick {

    public Brick(Breakout board, int x, int y){
        super(board, x, y);
    }

    public void draw(Graphics g) {
        Color color = new Color(0,50,(210-posY/2));

        g.setColor(color);
        g.fill3DRect(posX,posY, WIDTH, HEIGHT, true);
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
}
