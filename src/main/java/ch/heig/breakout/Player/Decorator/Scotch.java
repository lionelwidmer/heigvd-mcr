package ch.heig.breakout.Player.Decorator;

import ch.heig.breakout.Player.AbstractBar;
import ch.heig.breakout.Player.PowerUp;

/**
 * @brief   : Décorateur scotch pour l'AbstractBrick, permet de
 * scotcher la balle à la bar
 */
public class Scotch extends PowerUp {
    public  Scotch(AbstractBar bar) {
        super(bar);
    }

    public boolean scotch() {
        return true;
    }

    public int scotchCount(){
        return barDecorated.scotchCount() + 1;
    }
}
