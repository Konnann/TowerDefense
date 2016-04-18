package gameObjects.PlayerAssets;

import entities.BuildingEntity;
import graphics.Assets;
import graphics.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerAssets {
    //implements BuildingEntity
    public Rectangle boundingBox;

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
    private int firstMagicX = 70 + 95/2;
    private int firstMagicY = 705 - 595;
    private int secondMagicX = 70 + 95/2;
    private int secondMagicY = 690 - 191;

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

    public Rectangle firstMagicBoundingBox = new Rectangle(this.magicWidth, this.magicHeight);
    public Rectangle secondMagicBoundingBox = new Rectangle(this.magicWidth,this.magicHeight);

    public PlayerAssets() {
    }

    public void tick() {

        //Setting arrow's bounding box's bounds
        this.arrowBoundingBox.setBounds(arrowX,arrowY,arrowWidht,arrowHeight);

        //Setting magic's bounding box's bounds
        this.firstMagicBoundingBox.setBounds(firstMagicX,firstMagicY,magicWidth,magicHeight);
        this.secondMagicBoundingBox.setBounds(secondMagicX,secondMagicY,magicWidth,magicHeight);
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
        g.drawImage(currentSprite, this.firstMagicX, this.firstMagicY, null);
        g.drawImage(currentSprite, this.secondMagicX, this.secondMagicY, null);
        g.setColor(Color.blue);
        g.drawRect(this.firstMagicBoundingBox.x,this.firstMagicBoundingBox.y,this.firstMagicBoundingBox.width,this.firstMagicBoundingBox.height);
        g.drawRect(this.secondMagicBoundingBox.x,this.secondMagicBoundingBox.y,this.secondMagicBoundingBox.width,this.secondMagicBoundingBox.height);

    }

}
