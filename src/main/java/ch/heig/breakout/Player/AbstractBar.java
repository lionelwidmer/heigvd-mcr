package ch.heig.breakout.Player;

import java.awt.*;

/**
 * @brief   :
 */
public abstract class AbstractBar {

    protected final int HEIGHT = 20;
    protected int posY;
    protected int posX;
    protected Rectangle hitbox;
    Ball ball;

    protected AbstractBar() {
    }

    public AbstractBar(int x, int y) {
        this.posX = x;
        this.posY = y;
        hitbox = new Rectangle(x, y, getLength(), HEIGHT);
    }

    public abstract int getLength();

    public abstract void draw(Graphics g);

    public abstract void move(int moveBar);

    public abstract boolean scotch();

    public Ball getBall() {
        return ball;
    }

    public int getPosX() {
        return posX;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void manageCollision() {
        int diff = ball.getPosX() - (posX+getLength()/2);
            ball.setVecX((diff / 10) % 10);

            ball.setVecY(-ball.getVecY());
    }
}
