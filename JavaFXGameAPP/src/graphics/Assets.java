package graphics;

import java.awt.image.BufferedImage;


public class Assets {
    //Quicker access to images
    public static BufferedImage gameBackground;
    public static BufferedImage menuBackground;
    public static SpriteSheet tauren;

    public static void init(){
        gameBackground = ImageLoader.loadImage("/gameBackground.png");
        menuBackground = ImageLoader.loadImage("/background.jpg");
        tauren = new SpriteSheet(ImageLoader.loadImage("/Tauren_100x86.png"));
    }


}
