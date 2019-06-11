package ch.heig.breakout.Player;

import java.awt.*;

/**
 * @brief   : Classe gérant la balle du jeu
 */
public class Ball {
    private int posX;
    private int posY;
    private int vecX = 0;
    private int vecY = -10;
    private Rectangle hitbox;
    public static final int SIZE = 20;

    //Constructeur
    public Ball(int posX, int posY) {
        this.posX = posX + 60;
        this.posY = posY;
        hitbox = new Rectangle(posX, posY, SIZE, SIZE);
    }

    /**
     * @brief   : Dessine la balle
     * @param g : Graphics où la balle est dessiné
     */
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(posX, posY, SIZE, SIZE);
    }

    /**
     * @brief       : Déplace la balle selon son déplacement
     * @param moveX : int du déplacement dans l'axe des X
     * @param moveY : int du déplacement dans l'axe des Y
     */
    public void move(int moveX, int moveY) {
        posX += moveX;
        posY += moveY;
        hitbox.setLocation(posX,posY);
    }

    /**
     * @brief           : Déplace la balle dans l'axe des X
     * @param moveBall  : int du déplacement
     */
    public void grip(int moveBall) {
        posX += moveBall;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getVecX() {
        return vecX;
    }

    public int getVecY() {
        return vecY;
    }

    public void setVecX(int vecX) {
        this.vecX = vecX;
    }

    public void setVecY(int vecY) {
        this.vecY = vecY;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

}
