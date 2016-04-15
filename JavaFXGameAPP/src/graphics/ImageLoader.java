package graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {
    public static BufferedImage loadImage(String path){
        //load Image from path
        //returns a BufferedImage which we need to be able to crop the sprites
        try {
            return ImageIO.read(new File(path));       //changed the way we load an image
        } catch (IOException e) {
            e.printStackTrace();
        }
        //if nothing is found return null
        System.out.println("not found");
        return null;
    }

}
