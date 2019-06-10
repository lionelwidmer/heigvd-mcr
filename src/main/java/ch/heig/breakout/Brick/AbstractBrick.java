package ch.heig.breakout.Brick;

import ch.heig.breakout.Bonus;
import ch.heig.breakout.Breakout;
import ch.heig.breakout.Player.Ball;

import java.awt.*;

public abstract class AbstractBrick {
    protected int posX;
    protected int posY;
    protected Rectangle hitbox;
    protected final static int WIDTH = 80;
    protected final static int HEIGHT = 30;


    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    protected AbstractBrick() {
    }

    public AbstractBrick(int x, int y) {
        this.posX = x;
        this.posY = y;
        hitbox = new Rectangle(posX, posY, WIDTH, HEIGHT);
    }

    public void manageCollision(Ball ball) {
        manageBouncing(ball);
        //TODO getinstance() doesn't work
        // manageDamages();
    }

    protected void manageBouncing(Ball ball) {
/*        if (ball.getPosX() - Ball.SIZE / 2 <= posX || ball.getPosX() + Ball.SIZE / 2 >= posX + WIDTH) {
            ball.setVecX(-ball.getVecX());
        } else {
            ball.setVecY(-ball.getVecY());
       }*/
        //if the ball touches the brick left or right side, make it bounce horizontally
        if (ball.intersectX(posX) || ball.intersectX(posX + WIDTH)) ball.setVecX(-ball.getVecX());
            //if the ball touches the brick top or bottom side, make it bounce vertically
        else {
            ball.setVecY(-ball.getVecY());
        }


    }

    protected void manageDamages() {

        Breakout.getInstance().removeBrick(this);
        //TODO decide if bonus is released and release or not accordingly
    }

    public abstract int getSideProtected();

    public abstract void draw(Graphics g);

    public abstract Bonus destroy();

    public abstract Rectangle getHitbox();

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }
}
