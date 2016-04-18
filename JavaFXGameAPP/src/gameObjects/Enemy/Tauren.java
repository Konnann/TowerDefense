package gameObjects.Enemy;
import entities.BuildingEntity;
import entities.EnemyEntity;
import entities.Physics;
import gameObjects.PlayerAssets.PlayerAssets;
import graphics.Animation;
import gameObjects.PlayerAssets.CastleWall;
import graphics.Assets;
import graphics.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Tauren extends Enemy implements EnemyEntity {
    private Graphics graphics;
    private int x = 1280;            //position of character on canvas 1280
    private int y;            //..

    private int health = 250;
    private int damage = 10;
    private int velocity = 3;        //speed
    private int goldWorth = 30;      //The reward when you kill the monster

    private boolean isDead = false;
    private boolean isAttacking = false;

    private SpriteSheet taurenSprite = Assets.tauren;
    private int walkingHeight = 86;
    private int walkingWidth = 100;
    private int attackingHeight = 95;
    private int attackingWidth = 100;
    private int walkFrames = 4;                //walking frames in spritesheet
    private int attackFrames = 6;
    private int col = 0;                        //Spritesheet column
    private int row = 1;                        //Spritesheet row
    private Animation animation;
    public Rectangle boundingBox = new Rectangle(this.walkingWidth, this.walkingHeight);
    private BufferedImage[] walkingLeft = new BufferedImage[walkFrames];
    private BufferedImage[] attacking = new BufferedImage[attackFrames];
    private Animation attack,walkLeft;


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
        walkLeft = new Animation(walkingLeft, 7);
        col=0;
        for (int i = 0; i < attacking.length; i++) {

            row =91;

            attacking[i] = Assets.tauren.crop(col,row , attackingWidth, attackingHeight);
            col+=117;
       }
        attack = new Animation(attacking, 7);
        animation = walkLeft;

    }


    @Override
    public void tick(LinkedList<BuildingEntity> buildingEntities){
        //Check for collision
        for (int i = 0; i < buildingEntities.size(); i++) {
            if (Physics.collision(this, buildingEntities.get(i))) {
                this.isAttacking = true;
                System.out.println(buildingEntities.get(i));
            }
        }

        //Update bounding box position
        if(!isAttacking) {
            this.boundingBox.setBounds(this.x, this.y, this.walkingWidth, this.walkingHeight);
        }else{
            this.boundingBox.setBounds(this.x, this.y, this.attackingWidth, this.attackingHeight);

        }
        if(isAttacking){
            animation = attack;
        }else {
            //update enemy position
            x -= this.velocity;
        }

    animation.update();


    }
    @Override
    public void render(Graphics g)  {
        this.graphics = g;
            g.drawImage(animation.getSprite(), x, y, null);

        //System.out.println("Draw" + x + " " + y);
        //Test draw bounding boxes
        g.setColor(Color.red);
        g.drawRect(this.boundingBox.x, this.boundingBox.y, this.boundingBox.width, this.boundingBox.height);


    }

    @Override
    public Rectangle getBounds() {
        return boundingBox;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
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
