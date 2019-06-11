package ch.heig.breakout.Player.Decorator;

import ch.heig.breakout.Player.AbstractBar;
import ch.heig.breakout.Player.PowerUp;

import java.awt.*;

public class Smaller extends PowerUp {

    public Smaller(AbstractBar bar) {
        super(bar);
    }

    public int getLength() {
        return barDecorated.getLength() - 30;
    }

    public int smallerCount(){
        return barDecorated.smallerCount() + 1;
    }

}
