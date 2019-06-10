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
    protected Breakout board;


    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public AbstractBrick(Breakout board, int x, int y){
        this.posX = x;
        this.posY = y;
        this.board = board;
        hitbox = new Rectangle(posX, posY, WIDTH, HEIGHT);
    }

    public void manageCollision(Ball ball) {
        manageBouncing(ball);
        manageDamages();
    }

    protected void manageBouncing(Ball ball) {
        if(Math.abs(ball.getVecY()) > Math.abs(ball.getVecX())) ball.setVecY(- ball.getVecY());
        else ball.setVecX(- ball.getVecX());
    }

    protected void manageDamages() {
        board.removeBrick(this);
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
