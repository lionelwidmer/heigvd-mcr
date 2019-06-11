package ch.heig.breakout.Player;

import java.awt.*;

public abstract class PowerUp extends AbstractBar {

    protected AbstractBar barDecorated;

    public PowerUp(AbstractBar bar) {
        super();
        this.barDecorated = bar;
    }

    public int getLength() {
        return barDecorated.getLength();
    }

    public boolean scotch(){
        return barDecorated.scotch();
    }

    public void move(int moveBar) {
        barDecorated.move(moveBar);
    }

    public Ball getBall() {
        return barDecorated.getBall();
    }

    public int getPosX() {
        return barDecorated.getPosX();
    }

    public int getPosY() {
        return barDecorated.getPosY();
    }

    public void manageCollision() {
        barDecorated.manageCollision();
    }

    public int biggerCount() { return barDecorated.biggerCount();}
    public int smallerCount() { return barDecorated.smallerCount();}
    public int scotchCount() { return barDecorated.scotchCount();}

    public boolean tryRemovePowerUp( String powerUpClassName) {
        boolean isRemoved = false;

        if( this.getClass().getName() != powerUpClassName) {
            for (PowerUp nextBar = this; nextBar.getClass().getName() != Bar.class.getName(); nextBar = (PowerUp)nextBar.barDecorated) {
                if (nextBar.barDecorated.getClass().getName() == powerUpClassName) {
                    nextBar.barDecorated = ((PowerUp)nextBar.barDecorated).barDecorated;
                    isRemoved = true;
                }
            }
        }

        //cas pas trouvé return
        return isRemoved;
    }

    public AbstractBar getBarDecorated() {
        return barDecorated;
    }
}
