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

    protected enum Side {left, right, top, bottom, none};
    protected Side lastTouchedSide = Side.none;

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
        //Warning: don't call the following method at any point in code
        updateLastTouchedSide(ball);

        manageBouncing(ball);
        manageDamages(ball);
    }

    protected void manageBouncing(Ball ball) {
        /*
        //if the ball horizontal speed is bigger than its vertical speed,
        //then it means it touched the vertical borders of the brick first;
        //so in this case we make it bounce horizontally
        if(Math.abs(ball.getVecY()) > Math.abs(ball.getVecX())) ball.setVecY(- ball.getVecY());
        //otherwise, we make it bounce vertically
        else ball.setVecX(- ball.getVecX());
        */

        switch (lastTouchedSide){
            case left: ball.setVecX(- ball.getVecX());
            break;
            case right: ball.setVecX((- ball.getVecX()));
            break;
            case top: ball.setVecY(- ball.getVecY());
            break;
            case bottom: ball.setVecY(- ball.getVecY());
            break;
            default:
        }


    }

    /*
//Warning: don't call this method at any point in code (it makes assumptions that could be false sometimes)
    private void updateLastTouchedSide(Ball ball){
        Rectangle intersection = hitbox.intersection(ball.getHitbox());
        Side leftRightCandidate = (intersection.x == posX) ? Side.left : Side.right;
        Side topBottomCandidate = (intersection.y == posY) ? Side.top : Side.bottom;
        if(intersection.width * Math.abs(ball.getVecY()) < ball.getVecX() * Math.abs(intersection.height))
            lastTouchedSide = leftRightCandidate;
        else lastTouchedSide = topBottomCandidate;
    }
    */

    private void updateLastTouchedSide(Ball ball){

        Rectangle intersection = hitbox.intersection(ball.getHitbox());

        Side leftRightCandidate = (intersection.x == posX) ? Side.left : Side.right;
        Side topBottomCandidate = (intersection.y == posY) ? Side.top : Side.bottom;

        switch(leftRightCandidate){
            case left:
                if(intersection.x + intersection.width - ball.getVecX() >= posX){
                    lastTouchedSide = topBottomCandidate;
                } else {
                    lastTouchedSide = Side.left;
                }
                break;
            case right:
                if(intersection.x - ball.getVecX() <= posX + getWIDTH()){
                    lastTouchedSide = topBottomCandidate;
                } else {
                    lastTouchedSide = Side.right;
                }
        }


    }

    protected void manageDamages(Ball ball) {
        board.removeBrick(this);
        //TODO put a real payload into the bonus (not a null ref)
        if(Math.random() > 0.8) board.addBonus(new Bonus(posX, posY, null));
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
