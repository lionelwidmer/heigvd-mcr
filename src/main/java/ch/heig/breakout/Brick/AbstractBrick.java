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

    protected AbstractBrick() {
    }

    public AbstractBrick(Breakout board, int x, int y){
        this.posX = x;
        this.posY = y;
        this.board = board;
        hitbox = new Rectangle(posX, posY, WIDTH, HEIGHT);
    }

    public boolean intersectBall(Ball ball){
        return (intersectLeftSide(ball) || intersectRightSide(ball)) &&
                (intersectTopSide(ball) || intersectBottomSide(ball));
    }

    private boolean intersectLeftSide(Ball ball){
        return ball.intersectX(posX);
    }

    private boolean intersectRightSide(Ball ball){
        return ball.intersectX(posX + WIDTH);
    }

    private boolean intersectTopSide(Ball ball){
        return ball.intersectY(posY);
    }

    private boolean intersectBottomSide(Ball ball) {
        return ball.intersectY(posY + HEIGHT);
    }

    public void manageCollision(Ball ball) {
        manageBouncing(ball);
        //TODO getinstance() doesn't work
        // -> maybe we could use board instead (reference attribute to the Breakout object we belong to)
        // manageDamages();
    }

    protected void manageBouncing(Ball ball) {

/*        if (ball.getPosX() - Ball.SIZE / 2 <= posX || ball.getPosX() + Ball.SIZE / 2 >= posX + WIDTH) {
            ball.setVecX(-ball.getVecX());
        } else {
            ball.setVecY(-ball.getVecY());
       }*/
        //if the ball touches the brick left or right side, make it bounce horizontally
        if (intersectLeftSide(ball) || intersectRightSide(ball)) ball.setVecX(-ball.getVecX());
            //if the ball touches the brick top or bottom side, make it bounce vertically
        else {
            ball.setVecY(-ball.getVecY());
        }


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
