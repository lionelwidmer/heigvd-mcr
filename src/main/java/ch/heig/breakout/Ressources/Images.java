package ch.heig.breakout.Ressources;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @brief   : Permet de gérer l'image pour le shield de la brique
 */
public class Images {

    public final static BufferedImage shield;

    public final static Paint rays;

    static {
        try {
            shield = ImageIO.read(new File("src/main/java/ressources/shield.png"));
            rays = new TexturePaint(ImageIO.read((new File("src/main/java/ressources/shield.png"))), new Rectangle(0, 0, 80, 30));
        } catch (IOException e) {
            throw new Error(e);
        }
    }
}
