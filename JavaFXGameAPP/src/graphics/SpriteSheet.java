package graphics;
import java.awt.image.BufferedImage;


public class SpriteSheet {

    //Gets Image
    private BufferedImage image;

    public SpriteSheet(BufferedImage image){
        this.image = image;
    }
    //Crops part of image - for animation purposes
    public BufferedImage crop(int x, int y, int width, int height){
        return this.image.getSubimage(x, y, width, height);
    }
}