package ch.heig.breakout.Player.Decorator;

import ch.heig.breakout.Player.AbstractBar;
import ch.heig.breakout.Player.PowerUp;

/**
 * @brief   : Décorateur smaller de AbstractBrick rend plus petit la bar
 */
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
