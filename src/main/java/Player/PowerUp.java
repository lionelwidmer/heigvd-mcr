package Player;

import Brick.AbstractBrick;

public abstract class PowerUp extends AbstractBar {
    AbstractBar barDecorated;

    PowerUp(AbstractBar bar) {
        this.barDecorated = bar;
    }
}
