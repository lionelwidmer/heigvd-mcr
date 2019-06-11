package ch.heig.breakout.Player;

import java.awt.*;

/**
 * @brief   : Class Abstraite pour l'objet bar et ses décorateurs
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
    }

    public abstract int getLength();

    /**
     * @brief   : Dessine la bar
     * @param g : Graphics g où elle doit être dessiné
     */
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fill3DRect(getPosX(), getPosY(), getLength(), HEIGHT, true);
    }

    /**
     * @brief           : Déplace la bar
     * @param moveBar   : int du déplacement
     */
    public void move(int moveBar) {
        posX += moveBar;
    }

    /**
     * @brief   : Indique si la balle reste scotcher à la bar
     * @return  : boolean indiquant si la balle est scotché
     *          true    : si la balle est scotché
     *              false   : si la balle n'est pas scotché
     */
    public boolean scotch() {
        return false;
    }

    public Ball getBall() {
        return ball;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public Rectangle getHitbox() {
        return new Rectangle(getPosX(), getPosY(), getLength(), HEIGHT);
    }

    /**
     * @brief   : Gère la colision de la balle et de la bar
     */
    public void manageCollision() {
        int diff = ball.getPosX() - (posX+getLength()/2);
        ball.setVecX((diff / 10) % 10);

        ball.setVecY(-ball.getVecY());
    }

    /**
     * @brief   : indique le nombre de bigger possédé
     * @return  : int du nombre de bigger
     */
    public int biggerCount() { return 0;}

    /**
     * @brief   : indique le nombre de smaller possédé
     * @return  : int du nombre de smaller
     */
    public int smallerCount() { return 0;}

    /**
     * @brief   : indique le nombre de scotch possédé
     * @return  : int du scotch de bigger
     */
    public int scotchCount() { return 0;}


    public boolean tryRemoveDecorator(PowerUp powerUp) { return false;}
}
