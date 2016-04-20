package gameObjects.Projectile;

import entities.ProjectileEntity;
import gameObjects.Enemy.Enemy;
import graphics.Animation;
import graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MagicAttack implements ProjectileEntity {

    private Enemy target;

    private double x = 0;
    private double y = 0;
    private int endX = 0;
    private int endY = 0;

    private int spriteSide = 25;

    private int damage = 50;
    private double xVelocity = 0;
    private double yVelocity = 0;
    private double attackSpeed = 20;

    private BufferedImage[] sprites = new BufferedImage[2];
    private Animation animation;

    private Rectangle boundingBox;

    private boolean targetIsHit = false;

    //create a projectile
    public MagicAttack (Enemy target,int startX, int startY, int endX, int endY){
        this.x = startX;
        this.y = startY;
        this.endX = endX;
        this.endY = endY;
        this.target = target;
        calculateDirection();
        initialize();
    }

    @Override
    public void initialize() {
        for (int i = 0; i < sprites.length ; i++) {
            sprites[i] = Assets.Magic.crop(0, 0, this.spriteSide, spriteSide);
        }
        this.animation = new Animation(sprites, 5);
        this.boundingBox = new Rectangle((int)this.x, (int)this.y, spriteSide, spriteSide);

    }

    @Override
    public void tick() {
       //endX = target.getX();
       //endY = target.getY();
        calculateDirection();
        if(target != null) {
            if (this.boundingBox.intersects(target.getBoundingBox())) {
                this.targetIsHit = true;
                target.takeDamage(damage);
            }
        }
        this.x += xVelocity * attackSpeed;
        this.y += yVelocity * attackSpeed;

        this.boundingBox = new Rectangle((int)this.x, (int)this.y, spriteSide, spriteSide);
        this.animation.update();

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.drawRect(boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);


        g.drawImage(animation.getSprite(),(int)x, (int)y, null);
    }



    private void calculateDirection(){
        double totalAllowedMovement = 1.0;
        double xDistanceFromTarget = Math.abs(target.getX() - x);
        double yDistanceFromTarget = Math.abs(target.getY() - y);
        double totalDistanceFromTarget = xDistanceFromTarget + yDistanceFromTarget;
        double xPercentOfMovement = xDistanceFromTarget / totalDistanceFromTarget;
        this.xVelocity = xPercentOfMovement;
        this.yVelocity = totalAllowedMovement - xPercentOfMovement;

        if(target.getX() < x){
            xVelocity *= -1;
        }
        if(target.getY() < y){
            yVelocity *= -1;
        }
    }
    @Override
    public Rectangle getBounds() {
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
    public boolean targetIsHit() {
        return targetIsHit;
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
