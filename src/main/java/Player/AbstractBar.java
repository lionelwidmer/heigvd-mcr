package Player;

public abstract class AbstractBar {

    double posY;
    double posX;
    Ball ball;

    abstract int getLength();

    abstract boolean shoot();

    abstract void draw();

    abstract void move();

    abstract boolean scotch();
}
