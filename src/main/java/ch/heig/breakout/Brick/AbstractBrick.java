package ch.heig.breakout.Brick;

import ch.heig.breakout.Bonus;
import ch.heig.breakout.Breakout;
import ch.heig.breakout.Player.Ball;

import java.awt.*;

/**
 * @brief   : Classe abstraite AbstractBrick permettant de faire le lien entre
 * les décorateurs de brick et brick
 */
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

        updateLastTouchedSide(ball);

        manageBouncing(ball);
        manageDamages(ball);
    }

    protected void manageBouncing(Ball ball) {

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

        double rand = Math.random();

        if( rand > 0.8) {
            if( rand < 0.81) {
                board.addBonus(new Bonus(posX, posY, Bonus.BIGGER));
            }
            else if( rand < 0.82) {
                board.addBonus(new Bonus(posX, posY, Bonus.SMALLER));
            }
            else {
                board.addBonus(new Bonus(posX, posY, Bonus.SCOTCH));
            }
        }
    }

    public abstract void draw(Graphics g);

    public abstract Rectangle getHitbox();

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }
}
