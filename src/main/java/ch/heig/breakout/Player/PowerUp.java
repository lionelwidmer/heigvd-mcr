package ch.heig.breakout.Brick.Player;

public abstract class PowerUp extends AbstractBar {
    AbstractBar barDecorated;

    PowerUp(AbstractBar bar) {
        this.barDecorated = bar;
    }
}
