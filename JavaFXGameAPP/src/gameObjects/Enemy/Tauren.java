package gameObjects.Enemy;
import gameObjects.PlayerAssets.PlayerAssets;
import graphics.Animation;
import gameObjects.PlayerAssets.CastleWall;
import graphics.Assets;
import graphics.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

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
    private int walkingHeight = 86;
    private int walkingWidth = 100;
    private int attackingHeight = 100;
    private int attackingWidth = 115;
    private int walkFrames = 4;                //walking frames in spritesheet
    private int attackFrames = 6;
    private int col = 0;                        //Spritesheet column
    private int row = 1;                        //Spritesheet row
    private Animation animation;
    public Rectangle boundingBox = new Rectangle(this.walkingWidth, this.walkingHeight);
    private BufferedImage[] walkingLeft = new BufferedImage[walkFrames];
    private BufferedImage[] attacking = new BufferedImage[attackFrames];
    private Animation walkLeft = new Animation(walkingLeft, 5);
    private Animation attack = new Animation(attacking, 5);

    public Tauren(int x, int y){
        this.y = y;
        this.x = x;
        initialize();
    }

    public void initialize(){
        for (int i = 0; i < walkingLeft.length ; i++) {
            row = 0;
            walkingLeft[i] = Assets.tauren.crop(col, row, walkingWidth, walkingHeight);
            col += walkingWidth;
        }
        for (int i = 0; i < attacking.length; i++) {
            col = 0;
            row += walkingHeight;
            attacking[i] = Assets.tauren.crop(col, row, walkingWidth, walkingHeight);
        }

        animation = walkLeft;

    }


    @Override
    public void tick(){

        //Update bounding box position
        if(isAttacking == false) {
            this.boundingBox.setBounds(this.x, this.y, this.walkingWidth, this.walkingHeight);
        }else{
            this.boundingBox.setBounds(this.x, this.y, this.attackingWidth, this.attackingHeight);
        }
        if(isAttacking){
            animation = attack;
        }
        animation.update();


//




        if(isAttacking){
            //animation = attack

        }else {
            //update enemy position
            x -= this.velocity;
        }

        //enemies stop at end of screen , for testing purposes
        if (x <= 82) {
            x = 82;
        }

    }
    @Override
    public void render(Graphics g)  {
        this.graphics = g;
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
