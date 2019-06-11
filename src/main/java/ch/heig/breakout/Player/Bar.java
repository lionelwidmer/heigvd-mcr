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
        hitbox= new Rectangle(posX,posY,getLength(),HEIGHT);
    }

    /**
     * @brief   : Récupère la taille de la barre
     * @return  : int de sa taille
     */
    public int getLength() {
        return 140;
    }

}
