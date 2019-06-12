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

    /**
     * @brief       : Gère la colision entre la brique et la balle
     * @param ball  : Ball entrant en colision avec la brique
     */
    public void manageCollision(Ball ball) {

        updateLastTouchedSide(ball);

        manageBouncing(ball);
        manageDamages();
    }

    /**
     * @brief       : Gère le rebond de la balle
     * @param ball  : Balle qui doit rebondire
     */
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

    /**
     * @brief       : Test le contact avec la balle
     * @param ball  : Ball entrant en contact avec la brique
     */
    private void updateLastTouchedSide(Ball ball){

        Rectangle intersection = hitbox.intersection(ball.getHitbox());

        Side leftRightCandidate = (intersection.x == posX) ? Side.left : Side.right;
        Side topBottomCandidate = (intersection.y == posY) ? Side.top : Side.bottom;

        //if the vertical bar x coordinate was crossed after the horizontal y coordinate
        if(Math.abs(ball.getVecY()) * intersection.width < Math.abs(ball.getVecX()) * intersection.height)
            //the touched side is a vertical side
            lastTouchedSide = leftRightCandidate;
        //otherwise a horizontal one
        else lastTouchedSide = topBottomCandidate;

    }

    /**
     * @brief       : Gère la destruction de la brique et le laché de bonus
     */
    protected void manageDamages() {
        board.removeBrick(this);

        double rand = Math.random();

        if( rand > 0.86) {
            if( rand < 0.92) {
                board.addBonus(new Bonus(posX, posY, Bonus.BIGGER));
            } else if( rand < 0.98) {

                board.addBonus(new Bonus(posX, posY, Bonus.SMALLER));
            }
            else {
                board.addBonus(new Bonus(posX, posY, Bonus.SCOTCH));
            }
        }
    }

    /**
     * @brief   : dessine la brique
     * @param g : graphique ou est dessiné la brique
     */
    public abstract void draw(Graphics g);

    public abstract Rectangle getHitbox();

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }
}
