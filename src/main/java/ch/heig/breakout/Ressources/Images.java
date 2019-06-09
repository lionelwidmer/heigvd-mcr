package ch.heig.breakout.Ressources;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Images {

    public final static BufferedImage shield;

    static {
        try {
            shield = ImageIO.read(new File("shield.png"));
        } catch (IOException e) {
            throw new Error(e);
        }
    }


}
