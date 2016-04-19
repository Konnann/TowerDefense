package gameObjects.Projectile;

import gameObjects.Enemy.Enemy;
import graphics.Animation;
import graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MagicAttack implements Projectile {

    private Enemy target;

    private int startX = 0;
    private int startY = 0;
    private int currentX = 0;
    private int currentY = 0;
    private int endX = 0;
    private int endY = 0;

    private int spriteSide = 25;

    private int damage = 2;
    private int velocity = 2;
    private double attackSpeed = 0.6;

    private BufferedImage[] sprites = new BufferedImage[2];
    private Animation animation;

    private Rectangle boundingBox;

    //create a projectile
    public MagicAttack (Enemy target,int startX, int startY, int endX, int endY){
        this.startX = startX;
        this.startY = startY;
        this.currentX = startX;
        this.currentX = startY;
        this.endX = endX;
        this.endY = endY;
        this.target = target;
        initialize();
    }

    @Override
    public void initialize() {
        for (int i = 0; i < sprites.length ; i++) {
            sprites[i] = Assets.Magic.crop(0, 0, this.spriteSide, spriteSide);
        }
        this.animation = new Animation(sprites, 5);
    }

    @Override
    public void tick() {
        this.animation.update();
        this.boundingBox = new Rectangle(this.currentX, this.currentY, spriteSide, spriteSide);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.drawRect(boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);


        g.drawImage(animation.getSprite(),currentX, currentY, null);
    }





    @Override
    public int getStartX() {
        return startX;
    }
    @Override
    public int getStartY() {
        return startY;
    }
    @Override
    public Rectangle getBoundingBox() {
        return boundingBox;
    }
    public Enemy getTarget(){
        return target;
    }
    public  int getEndX(){
        return endX;
    }
    public int getEndY(){
        return endY;
    }


    // //Magic Initialization
   // private Graphics graphics;
   // private int firstMagicX = 70 + 95 / 2;
   // private int firstMagicY = 705 - 595;
   // private int secondMagicX = 70 + 95 / 2;
   // private int secondMagicY = 690 - 191;
//
   // private SpriteSheet magicSprite = Assets.Magic;
   // private int magicWidth = 25;
   // private int magicHeight = 25;
   // private int attackFrames;
   // private int col = 0;
   // private int row = 1;
   // private BufferedImage currentSprite;
//
   // private int magicVelocity; //the speed of the arrow
   // private int damage;  //damage dealt by a single shot
   // private int mannaCost; //how much manna it would cost per shot
//
   // private boolean isShooting = false;
//
   // public Rectangle firstMagicBoundingBox = new Rectangle(this.magicWidth, this.magicHeight);
   // public Rectangle secondMagicBoundingBox = new Rectangle(this.magicWidth,this.magicHeight);
//
//
   // public void tick() {
//
   //     //Setting magic's bounding box's bounds
   //     this.firstMagicBoundingBox.setBounds(firstMagicX,firstMagicY,magicWidth,magicHeight);
   //     this.secondMagicBoundingBox.setBounds(secondMagicX,secondMagicY,magicWidth,magicHeight);
   // }
//
   // public void render(Graphics g) {
   //     //Drawing magic projectile and its surrounding bounding box
   //     this.graphics = g;
   //     currentSprite = Assets.Magic.crop(this.col * magicWidth, 0, magicWidth, magicHeight);
   //     g.drawImage(currentSprite, this.firstMagicX, this.firstMagicY, null);
   //     g.drawImage(currentSprite, this.secondMagicX, this.secondMagicY, null);
   //     g.setColor(Color.blue);
   //     g.drawRect(this.firstMagicBoundingBox.x,this.firstMagicBoundingBox.y,this.firstMagicBoundingBox.width,this.firstMagicBoundingBox.height);
   //     g.drawRect(this.secondMagicBoundingBox.x,this.secondMagicBoundingBox.y,this.secondMagicBoundingBox.width,this.secondMagicBoundingBox.height);
   // }
}
