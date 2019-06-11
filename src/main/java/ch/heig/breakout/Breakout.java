package ch.heig.breakout;

import ch.heig.breakout.Brick.AbstractBrick;
import ch.heig.breakout.Brick.Brick;
import ch.heig.breakout.Brick.Decorator.Shield;
import ch.heig.breakout.Brick.Decorator.SideProtect;
import ch.heig.breakout.Player.AbstractBar;
import ch.heig.breakout.Player.Ball;
import ch.heig.breakout.Player.Bar;
import ch.heig.breakout.Player.Decorator.Bigger;
import ch.heig.breakout.Player.Decorator.Scotch;
import ch.heig.breakout.Player.Decorator.Smaller;
import ch.heig.breakout.Player.PowerUp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @brief : Génère la fenètre du breakout et des tous les éléments contenue dedans
 */
public class Breakout {

    private final int FPS = 60;
    private final int MARGEIN = 40;
    private final int PREF_WIDTH = 885;
    private final int PREF_HEIGHT = 940;
    private int moveBar = 0;
    private boolean launch = false;
    private boolean isGrip = false;
    private int status = 0;

    private JFrame window = new JFrame("Breakout");

    private static Breakout instance;

    private Collection<Bonus> bonuses = new HashSet<Bonus>();
    private Collection<AbstractBrick> bricks = new HashSet<AbstractBrick>();
    private AbstractBar player = new Bar(PREF_WIDTH, PREF_HEIGHT);

    /**
     * @brief : Classe peremetant de dessiners les éléments dans la fenêtre
     */
    private class Panel extends JPanel {

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (AbstractBrick b : bricks) {
                b.draw(g);
            }

            for(Bonus b: bonuses){
                b.draw(g);
            }

            player.draw(g);
            player.getBall().draw(g);
        }
    }

    private JPanel panel = new Panel();

    //Constructeur
    private Breakout() {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(new Dimension(PREF_WIDTH, PREF_HEIGHT));

        window.getContentPane().add(panel);

        window.setVisible(true);
        window.setResizable(false);

        final int nbrOfBrickRows = 8;
        final int nbrOfBrickColumns = 10;

        for(int i = 0; i < nbrOfBrickRows - 1; ++i){
            for(int j = 0; j < nbrOfBrickColumns; ++j){
                Brick b = new Brick(this, MARGEIN+j*AbstractBrick.getWIDTH(), MARGEIN+i*AbstractBrick.getHEIGHT());
                if(i == j || i == nbrOfBrickColumns - 1 - j) bricks.add(new Shield(b));
                else bricks.add(b);
            }
        }
        for(int j = 0; j < nbrOfBrickColumns; ++j){
            Brick b = new Brick(this, MARGEIN+j*AbstractBrick.getWIDTH(),
                    MARGEIN + (nbrOfBrickRows - 1) * AbstractBrick.getHEIGHT());
            bricks.add(new SideProtect(b));
        }

        window.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent key) {
                switch (key.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        moveBar = -8;
                        break;
                    case KeyEvent.VK_RIGHT:
                        moveBar = 8;
                        break;
                    case KeyEvent.VK_SPACE:
                        launch = true;
                        isGrip = player.scotch();
                        break;
                }
            }

            public void keyReleased(KeyEvent key) {
                switch (key.getKeyCode()) {
                    case KeyEvent.VK_LEFT: if(moveBar < 0) moveBar = 0;
                    break;
                    case KeyEvent.VK_RIGHT: if(moveBar > 0) moveBar = 0;
                    break;
                }
            }
        });
        play();
    }

    /**
     * @brief   : Récupère l'instance de breakout
     * @return  : Breakout actuelle
     */
    public static Breakout getInstance() {
        if (instance == null) {
            instance = new Breakout();
        }
        return instance;
    }

    /**
     * @brief : Faut tourner le jeux et appel le repaint
     */
    void play() {

        long timestamp;
        long timeout = 0;

        while (status == 0) {
            timestamp = System.currentTimeMillis();
            try {
                TimeUnit.MILLISECONDS.sleep(timeout);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            computeMove();
            window.repaint();
            timeout = Math.max(0, timeout + (1000/FPS) - (System.currentTimeMillis() - timestamp));
        }

    }

    /**
     * @brief   : Calcule les mouvements de la bar, de la balle et des bonus
     */
    private void computeMove() {
        int moveBarX = noCrossX(player.getPosX(), moveBar, player.getLength());

        //Bar
        if (moveBar != 0)
            player.move(moveBarX);

        //Ball
        if (launch && !isGrip) {
            detectCollision();
            int moveX = noCrossX(player.getBall().getPosX(), player.getBall().getVecX(), Ball.SIZE);
            int moveY = noCrossY(player.getBall().getPosY(), player.getBall().getVecY(), Ball.SIZE);
            player.getBall().move(moveX, moveY);
            ballInBorder(player.getBall());
        } else {
            player.getBall().grip(moveBarX);
        }

        //move bonuses
        for(Bonus b: bonuses) b.move();

    }

    /**
     * @brief       : Détecte si la balle entre en contact avec une bordure.
     *      Si c'est un bord elle rebondie mais si c'est le bas change le status de la partie.
     * @param ball  : Ball don on veut savoir la position (largeur)
     */
    private void ballInBorder(Ball ball) {
        if (ball.getPosX() + Ball.SIZE == PREF_WIDTH || ball.getPosX() == 0) ball.setVecX(- ball.getVecX());

        if (ball.getPosY() + Ball.SIZE == PREF_HEIGHT) {
            status = -1;
        }

        if (ball.getPosY() == 0) {
            ball.setVecY(-ball.getVecY());
        }
    }

    /**
     * @brief       : Empèche un élément de dépasser les bord de l'axe des X
     * @param posX  : int Position X de l'objet
     * @param vectX : int de son vecteur de déplacement en X
     * @param size  : int de sa taille
     * @return      : int de sa nouvelle position
     */
    private int noCrossX(int posX, int vectX, int size) {
        int x = posX + size + vectX;

        if (x > PREF_WIDTH) {
            return PREF_WIDTH - posX - size;
        }

        if (x - size < 0) {
            return - posX;
        }

        return vectX;
    }

    /**
     * @brief       : Empèche un élément de traverser les bords en l'axe des Y
     * @param posY  : int de sa position sur l'axr des Y
     * @param vectY : int de son vecteur de déplacemet en Y
     * @param size  : int de la taille de l'élément (hauteur)
     * @return      :
     */
    private int noCrossY(int posY, int vectY, int size) {
        int y = posY + vectY + size;

        if (y > PREF_HEIGHT) {
            return PREF_HEIGHT - posY - size;
        }

        if (y - size < 0) {
            return - posY;
        }

        return vectY;
    }

    /**
     * @brief   : Détecte les colision entre la balle, la bar et les briques
     */
    private void detectCollision() {
        Ball ball = player.getBall();


        //detect bar collision
        if (ball.getHitbox().intersects(player.getHitbox())) {
            player.manageCollision();
        } else {
            //detect brick collision
            for (AbstractBrick brick : bricks)
                if (ball.getHitbox().intersects(brick.getHitbox())) {
                    brick.manageCollision(player.getBall());
                    break;
                }
        }

        //detect bonus collision with the bottom of board
        Iterator<Bonus> it = bonuses.iterator();
        while(it.hasNext()){
            Bonus b = it.next();
            if (b.getHitbox().intersects(player.getHitbox())){
                //TODO decorateur pas tout à fait opérationnel (probème)
                int biggerCount = player.biggerCount();
                switch( b.getPowerUpId()){
                    case Bonus.BIGGER:
                        if ( biggerCount < 3)
                            player = new Bigger(player);
                        break;
                    case Bonus.SMALLER:
                        if( biggerCount > 0) {
                            //dans ce cas si tryRemoveDecorator échoue
                            // c'est parce qu'il faut enlever le premier decorateur
                            if ( !player.tryRemoveDecorator(new Bigger(player)))
                                //cast possible ca il y a au moins 1 bigger
                                player = ((PowerUp) player).getBarDecorated();
                        }
                        else if ( player.smallerCount() < 3)
                            player = new Smaller(player);
                        break;
                    case Bonus.SCOTCH:
                        if ( player.scotchCount() < 1)
                        player = new Scotch(player);
                        break;
                    default:
                        break;
                }
                it.remove();
            } else if (b.getHitbox().y + b.getHitbox().height > PREF_HEIGHT) {
                it.remove();
            }
        }


    }

    /**
     * @brief       : Ajoute un bonus
     * @param bonus : Bonus à ajouter
     */
    public void addBonus(Bonus bonus) {
        bonuses.add(bonus);
    }

    /**
     * @brief       : Retire un bonus
     * @param bonus : Bonus à retirer
     */
    public void removeBonus(Bonus bonus) {
        bonuses.remove(bonus);
    }

    /**
     * @brief       : Ajoute une brique
     * @param brick : AbstractBrick à retirer
     */
    public void addBrick(AbstractBrick brick) {
        bricks.add(brick);
    }

    /**
     * @brief       : Retire une brique
     * @param brick : AbstractBrick à retirer
     */
    public void removeBrick(AbstractBrick brick) {
        bricks.remove(brick);
    }


    public static void main(String... args) {

        getInstance();
    }
}
