package gameObjects.Enemy;
import game.Animation;
import gameObjects.castle.CastleWall;
import graphics.Assets;
import graphics.SpriteSheet;
import javax.swing.Timer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Date;

public class Tauren extends Enemy {
    private Graphics graphics;
    private int x = 1280;            //position of character on canvas 1280
    private int y;            //..

    private int health = 250;
    private int damage = 10;
    private double velocity = 0.3;        //speed
    private int goldWorth = 30;      //The reward when you kill the monster

    private boolean isDead = false;
    private boolean isAttacking = false;

    private SpriteSheet taurenSprite = Assets.tauren;
    private int spriteHeight = 86;
    private int spriteWidth = 100;
    private int walkFrames = 4;                //walking frames in spritesheet
    private int attackFrames;
    private int col = 0;                        //Spritesheet column
    private int row = 1;                        //Spritesheet row
    private BufferedImage currentSprite;
    private int frameCount=0;
    private int frameDelay=1;
    private int currentFrame=0;
    private int totalFrames=4;
    private Animation animation;
    public Rectangle boundingBox = new Rectangle(this.spriteWidth, this.spriteHeight);
    private BufferedImage[] walkingLeft=new BufferedImage[4];

    public Tauren(int x, int y){
        this.y = y;
        this.x = x;
        initialize();
    }

    public void initialize(){

        walkingLeft[3]=Assets.tauren.crop(7,0,80,86);
        walkingLeft[2]=Assets.tauren.crop(104,0,86,86);
        walkingLeft[1]=Assets.tauren.crop(199,0,98,86);
        walkingLeft[0]=Assets.tauren.crop(312,0,8,86);

            Animation walkLeft = new Animation(walkingLeft, 5);
            animation=walkLeft;

    }


    @Override
    public void tick(){

        //Update bounding box position
        this.boundingBox.setBounds(this.x, this.y, this.spriteWidth, this.spriteHeight);
        animation.update();
        //Animate
       // this.col++;
//




       // if(isAttacking){
            //to get attack animation sprites
            //row = 2;

       // }else {
            //update enemy position

            x -= this.velocity;
        //}

        //enemies stop at end of screen , for testing purposes
        if (x <= 82) {
            x = 82;
        }

    }
    @Override
    public void render(Graphics g)  {
        this.graphics = g;
        //this.col++;
       // this.col%=walkFrames;
       // currentSprite = Assets.tauren.crop(this.col * spriteWidth, 0, spriteWidth, spriteHeight);


            g.drawImage(animation.getSprite(), x, y, null);


        if(isAttacking){

        }
        //Test draw bounding boxes
        g.setColor(Color.red);
        g.drawRect(this.boundingBox.x, this.boundingBox.y, this.boundingBox.width, this.boundingBox.height);


    }


    public void increaseStats(int health, int damage, int velocity, int goldWorth) {
        this.health += health;
        this.damage += damage;
        this.velocity += velocity;
        this.goldWorth += goldWorth;
    }
    public boolean intersects (CastleWall wall){

        if(this.boundingBox.intersects(wall.boundingBox)){
            return true;
        }
        return false;
    }

   // public void setAttacking(boolean isAttacking) {
   //     this.isAttacking = isAttacking;
   // }

}
