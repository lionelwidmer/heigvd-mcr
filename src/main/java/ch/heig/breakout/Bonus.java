package ch.heig.breakout;

import ch.heig.breakout.Player.*;
import ch.heig.breakout.Player.Decorator.*;

import java.awt.*;

/**
 * @brief   : Gère les bonus des bircks qui tombent
 */
public class Bonus {

    public static final int BIGGER = 0;
    public static final int SMALLER = 1;
    public static final int SCOTCH = 2;

    private final int pwuId;
    private int posX;
    private int posY;
    private Rectangle hitbox;

    final static int SIZE = 20;
    final static int DOWNWARDSPEED = 5;

    //Constructeur
    public Bonus(int x, int y, int powerUpId) {
        posX = x;
        posY = y;
        this.pwuId = powerUpId;
        hitbox = new Rectangle(posX, posY, SIZE, SIZE);
    }

    /**
     * @brief déplacement du bonus.
     */
    public void move() {
        posY += DOWNWARDSPEED;
        hitbox.translate(0, DOWNWARDSPEED);
    }

    /**
     * @brief   : Dessine le bonus dans la fenêtre
     * @param g : Graphics où est dessiner le donus
     */
    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.drawOval(posX, posY, SIZE, SIZE);
    }

    /**
     * @brief   : Retourne la hitbox du bonus
     * @return  : Rectangle de la hitbox
     */
    public Rectangle getHitbox(){
        return hitbox;
    }

    /**
     * @brief   : Récupère le power up du bonus
     * @return  : PowerUp du bonus
     */
    public int getPowerUpId(){
        return pwuId;
    }
}
