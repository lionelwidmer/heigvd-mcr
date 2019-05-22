package ch.heig.breakout.Brick;

import ch.heig.breakout.Bonus;

public interface AbstractBrick {
    int getSideProtected();

    void draw();

    Bonus destroy();
}
