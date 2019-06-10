package ch.heig.breakout.Player;

import java.awt.*;

public class Ball {
    private int posX;
    private int posY;
    private int vecX = 5;
    private int vecY = -10;
    private Rectangle hitbox;
    public static final int SIZE = 20;

    public Ball(int posX, int posY) {
        this.posX = posX + 60;
        this.posY = posY;
        hitbox = new Rectangle(posX, posY, SIZE, SIZE);
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(posX, posY, SIZE, SIZE);
    }

    public void move(int moveX, int moveY) {
        posX += moveX;
        posY += moveY;
        hitbox.setLocation(posX,posY);
    }

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

    public boolean intersectX(int x){
        return posX + SIZE >= x && posX - SIZE <= x;
    }

    public boolean intersectY(int y) {
        return posY + SIZE >= y && posY - SIZE <= y;
    }
    public Rectangle getHitbox() {
        return hitbox;
    }

}
