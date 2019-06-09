package ch.heig.breakout.Brick;

import ch.heig.breakout.Bonus;

import java.awt.*;

public abstract class AbstractBrick {
    protected int posX;
    protected int posY;
    protected final static int WIDTH = 80;
    protected final static int HEIGHT = 30;

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    protected AbstractBrick(){}

    public AbstractBrick(int x, int y){
        this.posX = x;
        this.posY = y;
    }

    public abstract int getSideProtected();

    public abstract void draw(Graphics g);

    public abstract Bonus destroy();

    public static int getWIDTH(){
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }
}
