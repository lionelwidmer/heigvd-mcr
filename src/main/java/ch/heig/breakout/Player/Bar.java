package ch.heig.breakout.Player;

import java.awt.*;

/**
 * @brief   : Classe Bar permettant de gérer la bare
 */
public class Bar extends AbstractBar {
    private final int MARGEIN = 85;

    //Constructeur
    public Bar(int x, int y){
        super();
        posX = (x-getLength())/2;
        posY = y - MARGEIN;
        ball = new Ball(posX, posY-20);
        hitbox= new Rectangle(x,y,getLength(),HEIGHT);
    }

    /**
     * @brief   : Récupère la taille de la barre
     * @return  : int de sa taille
     */
    public int getLength() {
        return 140;
    }

    /**
     * @brief   : Dessine la bar
     * @param g : Graphics g où elle doit être dessiné
     */
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fill3DRect(posX, posY, getLength(), HEIGHT, true);
    }


    /**
     * @brief           : Déplace la bar
     * @param moveBar   : int du déplacement
     */
    public void move(int moveBar) {
        posX += moveBar;
        hitbox.setLocation(posX,posY);
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


}
