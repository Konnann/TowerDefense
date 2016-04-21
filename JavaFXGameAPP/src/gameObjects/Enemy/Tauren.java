package gameObjects.Enemy;
import entities.BuildingEntity;
import entities.EnemyEntity;
import entities.Physics;
import graphics.Animation;
import gameObjects.PlayerAssets.CastleWall;
import graphics.Assets;
import graphics.HealthBar;
import graphics.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Tauren implements Enemy, EnemyEntity {

    private Graphics graphics;
    private int x = 1280;            //position of character on canvas 1280
    private int y;                  //..

    private int health = 200;
    private int damage = 7;
    private int velocity = 3;        //speed

    private int goldWorth = 30;      //The reward when you kill the monster

    private boolean isAlive = true;
    private boolean isAttacking = false;

    private SpriteSheet taurenSprite = Assets.tauren;
    private int walkingHeight = 86;
    private int walkingWidth = 100;
    private int attackingHeight = 95;
    private int attackingWidth = 114;
    private int walkFrames = 4;                //walking frames in spritesheet
    private int attackFrames = 6;
    private int col = 0;                        //Spritesheet column
    private int row = 1;                        //Spritesheet row
    private Animation animation;
    public Rectangle boundingBox = new Rectangle(this.walkingWidth, this.walkingHeight);
    private BufferedImage[] walkingLeft = new BufferedImage[walkFrames];
    private BufferedImage[] attacking = new BufferedImage[attackFrames];
    private Animation attack,walkLeft;

    private int barX = this.x + 8;
    private int barY = this.y - 5;
    private int barWidth = this.walkingWidth - 16;
    private int barHeight = 7;
    private HealthBar healthBar = new HealthBar(this.barX, this.barY, this.barWidth, this.barHeight, this.health);


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
        col = 0;
        for (int i = 0; i < attacking.length; i++) {

            row = 91;

            attacking[i] = Assets.tauren.crop(col,row , attackingWidth, attackingHeight);
            col += 117;
       }
        attack = new Animation(attacking, 7);
        animation = walkLeft;

    }


    @Override
    public void tick(LinkedList<BuildingEntity> buildingEntities){

        if(health <= 0){
            isAlive = false;
        }
        //Check for enemyCollision and attack if true
        for (int i = 0; i < buildingEntities.size(); i++) {
            if (Physics.enemyCollision(this, buildingEntities.get(i))) {
                this.isAttacking = true;
                if (attack.getCurrentFrame() == 2) {
                    buildingEntities.get(i).takeDamage(this.damage);
                }
            }
        }

        //Update bounding box position
        if(!isAttacking) {
            this.boundingBox.setBounds(this.x, this.y, this.walkingWidth, this.walkingHeight);
        }else{
            this.boundingBox.setBounds(this.x, this.y, this.attackingWidth, this.attackingHeight);

        }

        //Update healthbar
        this.healthBar.tick(this.x + 8, this.y - 5, this.health);

        //change animation if attacking
        if(isAttacking){
            animation = attack;
        }else {
            //update enemy position
            animation = walkLeft;
            x -= this.velocity;
        }
        animation.update();

    }

    @Override
    public void render(Graphics graphics)  {
        this.graphics = graphics;
            graphics.drawImage(animation.getSprite(), x, y, null);

        //Test draw bounding boxes
       // graphics.setColor(Color.red);
       // graphics.drawRect(this.boundingBox.x, this.boundingBox.y, this.boundingBox.width, this.boundingBox.height);

        //draw healthbar
        this.healthBar.render(this.graphics);

        this.isAttacking = false;

    }

    public void increaseStats(int health, int damage, int velocity, int goldWorth) {
        this.health += health;
        this.damage += damage;
        this.velocity += velocity;
        this.goldWorth += goldWorth;
    }

    @Override
    public void takeDamage(int damage) {
        this.health -= damage;
    }

    @Override
    public void intersect(Enemy enemy, CastleWall wall) {

    }

    @Override
    public Rectangle getBounds() {
        return boundingBox;
    }


    @Override
    public void isAttacking(boolean isAttacking) {
       this.isAttacking = isAttacking;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public Rectangle getBoundingBox() {
        return this.boundingBox;
    }

    @Override
    public boolean isAlive() {
        return isAlive;
    }
    public int getGoldWorth() {
        return goldWorth;
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
