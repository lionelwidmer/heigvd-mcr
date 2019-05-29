package ch.heig.breakout.Player;

import java.awt.*;

public abstract class AbstractBar {

    protected final int HEIGHT = 20;
    protected int posY;
    protected int posX;
    Ball ball;

    protected AbstractBar(){}

    public AbstractBar(int x, int y){
        this.posX = x;
        this.posY = y;
    }

    public abstract int getLength();

    public abstract boolean shoot();

    public abstract void draw(Graphics g);

    public abstract void move(int dist);

    public abstract boolean scotch();
}
