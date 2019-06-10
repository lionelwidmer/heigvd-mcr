package ch.heig.breakout.Player;

import java.awt.*;

public class Bar extends AbstractBar {
    private final int MARGEIN = 85;

    public Bar(int x, int y){
        super();
        posX = (x-getLength())/2;
        posY = y - MARGEIN;
        ball = new Ball(posX, posY-20);
        hitbox= new Rectangle(x,y,getLength(),HEIGHT);
    }

    public int getLength() {
        return 140;
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fill3DRect(posX, posY, getLength(), HEIGHT, true);
    }

    public void move(int moveBar) {
        posX += moveBar;
        hitbox.setLocation(posX,posY);
    }

    public boolean scotch() {
        return false;
    }


}
