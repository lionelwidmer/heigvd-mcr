package ch.heig.breakout;

import ch.heig.breakout.Brick.AbstractBrick;
import ch.heig.breakout.Brick.Brick;
import ch.heig.breakout.Player.AbstractBar;
import ch.heig.breakout.Player.Bar;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Breakout {

    private final int MARGEIN = 20;
    private final int PREF_WIDTH = 845;
    private final int PREF_HEIGHT = 940;

    private JFrame window = new JFrame("Breakout");

    private static Breakout instance;

    private ArrayList<Bonus> bonuses;
    private ArrayList<AbstractBrick> bricks = new ArrayList<AbstractBrick>();
    private AbstractBar player = new Bar(PREF_WIDTH, PREF_HEIGHT);

    private class Panel extends JPanel {

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            for(AbstractBrick b : bricks){
                b.draw(g);
            }
            player.draw(g);
        }
    }

    private JPanel panel = new Panel();

    private Breakout(){
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(new Dimension(PREF_WIDTH, PREF_HEIGHT));

        window.getContentPane().add(panel);

        window.setVisible(true);
        window.setResizable(false);

        for(int i = 0; i < 8; ++i){
            for(int j = 0; j < 10; ++j){
                bricks.add(new Brick(MARGEIN+j*AbstractBrick.getWIDTH(), MARGEIN+i*AbstractBrick.getHEIGHT()));
            }
        }

        window.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent key) {
                switch (key.getKeyCode()){
                    case KeyEvent.VK_LEFT:
                        player.move(-10);
                        break;
                    case KeyEvent.VK_RIGHT:
                        player.move(10);
                        break;
                }
                window.repaint();
            }
        });
    }

    public static Breakout getinstance(){
        if(instance == null) {
            instance = new Breakout();
        }
        return instance;
    }



    public static void main(String... args){
        Breakout breakout = getinstance();
    }
}
