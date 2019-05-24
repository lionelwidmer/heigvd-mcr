package ch.heig.breakout.Player;

public abstract class PowerUp extends AbstractBar {
    AbstractBar barDecorated;

    PowerUp(AbstractBar bar) {
        this.barDecorated = bar;
    }
}
