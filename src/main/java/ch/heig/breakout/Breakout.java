package ch.heig.breakout;

import ch.heig.breakout.Brick.AbstractBrick;
import ch.heig.breakout.Player.AbstractBar;


import javax.swing.*;
import java.util.ArrayList;

public class Breakout {
    JFrame window;
    ArrayList<Bonus> bonuses;
    ArrayList<AbstractBrick> bricks;
    AbstractBar player;
}
