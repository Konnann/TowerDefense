package gameObjects.enemy;
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

    public Rectangle boundingBox = new Rectangle(this.spriteWidth, this.spriteHeight);


    public Tauren(int x, int y){
        this.y = y;
        this.x = x;
    }


    @Override
    public void tick(){

        //Update bounding box position
        this.boundingBox.setBounds(this.x, this.y, this.spriteWidth, this.spriteHeight);

        //Animate
        this.col++;
        this.col %= walkFrames;

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
        System.out.printf("%d %d\n", x, y);

    }
    @Override
    public void render(Graphics g)  {
        this.graphics = g;

            currentSprite = Assets.tauren.crop(col * spriteWidth, 0, spriteWidth, spriteHeight);
            g.drawImage(currentSprite, x, y, null);

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
