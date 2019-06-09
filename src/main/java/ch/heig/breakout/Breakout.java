package ch.heig.breakout;

import ch.heig.breakout.Brick.AbstractBrick;
import ch.heig.breakout.Brick.Brick;
import ch.heig.breakout.Player.AbstractBar;
import ch.heig.breakout.Player.Ball;
import ch.heig.breakout.Player.Bar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Breakout {

    private final int MARGEIN = 40;
    private final int PREF_WIDTH = 885;
    private final int PREF_HEIGHT = 940;
    private int moveBar = 0;
    private boolean launch = false;
    private boolean isGrip = false;
    private int status = 0;

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
            player.getBall().draw(g);
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
                        moveBar = -6;
                        break;
                    case KeyEvent.VK_RIGHT:
                        moveBar = 6;
                        break;
                    case KeyEvent.VK_SPACE:
                        launch = true;
                        isGrip = player.scotch();
                        break;
                }
            }

            public void keyReleased(KeyEvent key){
                switch (key.getKeyCode()){
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_RIGHT:
                        moveBar = 0;
                        break;
                }
            }
        });
        play();
    }

    public static Breakout getInstance(){
        if(instance == null) {
            instance = new Breakout();
        }
        return instance;
    }

    void play(){
        while(status == 0){
            try {
                TimeUnit.MILLISECONDS.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            computeMove();
            window.repaint();
        }
        instance = new Breakout();
    }

    private void computeMove(){
        int moveBarX = noCrossX(player.getPosX(), moveBar, player.getLength());

        //Bar
        if(moveBar != 0)
            player.move(moveBarX);

        //Ball
        if(launch && !isGrip) {
            int moveX = noCrossX(player.getBall().getPosX(), player.getBall().getVecX(), Ball.SIZE);
            int moveY = noCrossY(player.getBall().getPosY(), player.getBall().getVecY(), Ball.SIZE);
            player.getBall().move(moveX, moveY);
            ballInBorder(player.getBall());
        } else {
            player.getBall().grip(moveBarX);
        }
    }

    private void ballInBorder(Ball ball){
        if(ball.getPosX()+Ball.SIZE == PREF_WIDTH || ball.getPosX() == 0){
            ball.setVecX(-ball.getVecX());
        }

        if(ball.getPosY()+Ball.SIZE == PREF_HEIGHT){
            status = -1;
        }

        if(ball.getPosY() == 0){
            ball.setVecY(-ball.getVecY());
        }
    }

    private int noCrossX(int posX, int vectX, int size){
        int x = posX+size+vectX;

        if(x > PREF_WIDTH){
            return PREF_WIDTH-posX-size;
        }

        if(x-size < 0){
            return posX;
        }

        return vectX;
    }

    private int noCrossY(int posY, int vectY, int size){
        int y = posY + vectY + size;

        if(y > PREF_HEIGHT){
            return PREF_HEIGHT-posY-size;
        }

        if(y-size < 0){
            return posY;
        }

        return vectY;
    }

    public static void main(String... args){

        Breakout breakout = getInstance();
    }
}
