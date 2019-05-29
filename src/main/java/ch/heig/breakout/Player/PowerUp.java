package ch.heig.breakout.Player;

public abstract class PowerUp extends AbstractBar {
    AbstractBar barDecorated;

    public PowerUp(AbstractBar bar) {
        super();
        this.barDecorated = bar;
    }
}
