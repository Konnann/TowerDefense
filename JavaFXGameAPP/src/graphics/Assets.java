package graphics;

import java.awt.image.BufferedImage;
import java.io.File;


public class Assets {
    //Quicker access to images
    public static BufferedImage gameBackground;
    public static BufferedImage menuBackground;
    public static BufferedImage castleWall;
    public static SpriteSheet tauren;
    public static BufferedImage tower;
    public static BufferedImage crossbow;

    public static void init(){
        //we load images by putting the name of the folder as a string, then File.separator, then the name of the image
        gameBackground = ImageLoader.loadImage("resources" + File.separator + "gameBackground.png");
        menuBackground = ImageLoader.loadImage("resources" + File.separator + "background.jpg");
        castleWall = ImageLoader.loadImage("resources" + File.separator + "CastleWall_82x575.png");
        tauren = new SpriteSheet(ImageLoader.loadImage("resources" + File.separator + "Tauren_100x86.png"));
        tower = ImageLoader.loadImage("resources" + File.separator + "Tower_86x256.png");
        crossbow = ImageLoader.loadImage("resources" + File.separator + "Crossbow_164 x158.png");
    }


}
