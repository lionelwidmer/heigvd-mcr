package ch.heig.breakout.Player.Decorator;

import ch.heig.breakout.Player.AbstractBar;
import ch.heig.breakout.Player.PowerUp;

/**
 * @brief   : Décorateur scotch pour l'AbstractBrick, permet d'augmenter
 * la taille de la bar
 */
public class Bigger extends PowerUp {
    public Bigger(AbstractBar bar) {
        super(bar);
    }

    public int getLength() {
        return barDecorated.getLength() + 30;
    }

    public int biggerCount(){
        return barDecorated.biggerCount() + 1;
    }
}
