package gameObjects.Weapons;

import graphics.Assets;
import graphics.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Plamen on 4/15/2016.
 */
public class Weapons {

    //Arrow Initialization
    private int arrowX = 0;
    private int arrowY = 250 + 186/2;
    private BufferedImage arrowSprite = Assets.Arrow;

    private int arrowWidht = 193 / 2 + 20;
    private int arrowHeight = 20;

    private int arrowVelocity;

    public Rectangle arrowBoundingBox = new Rectangle(this.arrowWidht, this.arrowHeight);

////////////////////////////////////////////////////////////////////////////////////////////////

    //Magic Initialization
    private Graphics graphics;
    private int magicX = 70 + 95/2;
    private int magicY = 715 - 595;

    private SpriteSheet magicSprite = Assets.Magic;
    private int magicWidth = 25;
    private int magicHeight = 25;
    private int attackFrames;
    private int col = 0;
    private int row = 1;
    private BufferedImage currentSprite;

    private int magicVelocity; //the speed of the arrow
    private int damage;  //damage dealt by a single shot
    private int mannaCost; //how much manna it would cost per shot

    private boolean isShooting = false;

    public Rectangle magicBoundingBox = new Rectangle(this.magicWidth, this.magicHeight);


    public Weapons() {
    }

    public void tick() {

        //Setting arrow's bounding box's bounds
        this.arrowBoundingBox.setBounds(arrowX,arrowY,arrowWidht,arrowHeight);

        //Setting magic's bounding box's bounds
        this.magicBoundingBox.setBounds(magicX,magicY,magicWidth,magicHeight);
        //Animate
        //update enemy position
        //enemies stop at end of screen , for testing purposes
    }

    public void render(Graphics g) {

        //Drawing arrow and arrow's bounding box
        g.drawImage(arrowSprite,this.arrowX,this.arrowY,this.arrowWidht,this.arrowHeight,null);
        g.setColor(Color.BLUE);
        g.drawRect(this.arrowBoundingBox.x, this.arrowBoundingBox.y, this.arrowBoundingBox.width, this.arrowBoundingBox.height);

        //Drawing magic projectile and its surrounding bounding box
        this.graphics = g;
        currentSprite = Assets.Magic.crop(this.col * magicWidth, 0, magicWidth, magicHeight);
        g.drawImage(currentSprite, this.magicX, this.magicY, null);
        g.setColor(Color.blue);
        g.drawRect(this.magicBoundingBox.x,this.magicBoundingBox.y,this.magicBoundingBox.width,this.magicBoundingBox.height);

    }
}
