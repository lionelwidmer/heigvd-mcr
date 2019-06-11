package ch.heig.breakout;

import ch.heig.breakout.Player.*;

import java.awt.*;

public class Bonus {
    private PowerUp pwu;
    private int posX;
    private int posY;
    private Rectangle hitbox;

    final static int SIZE = 20;
    final static int DOWNWARDSPEED = 5;

    public Bonus(int x, int y, PowerUp powerUp) {
        posX = x;
        posY = y;
        this.pwu = powerUp;
        hitbox = new Rectangle(posX, posY, SIZE, SIZE);
    }

    public void move() {
        posY += DOWNWARDSPEED;
        hitbox.translate(0, DOWNWARDSPEED);
    }

    public void draw(Graphics g) {
        g.drawOval(posX, posY, SIZE, SIZE);
    }

    public Rectangle getHitbox(){
        return hitbox;
    }

    public PowerUp getPowerUp(){
        return pwu;
    }
}
