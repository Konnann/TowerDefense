package gameObjects.enemy;
import gameObjects.castle.CastleWall;
import graphics.Assets;
import graphics.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tauren extends Enemy {
    private Graphics graphics;
    private int x = 1280;            //position of character on canvas
    private int y = 500;            //..

    private int health = 250;
    private int damage = 10;
    private int velocity = 5;        //speed
    private int goldWorth = 30;      //The reward when you kill the monster

    private boolean isDead = false;
    private boolean isAttacking = false;

    private SpriteSheet taurenSprite = Assets.tauren;
    private int spriteHeight = 86;
    private int spriteWidth = 100;
    private int walkFrames = 4;                 //walking frames in spritesheet
    private int attackFrames;
    private int col = 0;                        //Spritesheet column
    private int row = 1;                        //Spritesheet row
    private BufferedImage currentSprite;

    private Rectangle boundingBox = new Rectangle(this.spriteWidth, this.spriteHeight);


    public Tauren(){
    }


    @Override
    public void tick(){

        //Update bounding box position
        this.boundingBox.setBounds(this.x, this.y, this.spriteWidth, this.spriteHeight);

        //Animate
        this.col++;
        this.col %= walkFrames;

        //update enemy position
        this.x -= this.velocity;

        //enemies stop at end of screen , for testing purposes
        if (this.x <= 82) {
            this.x = 82;
        }

    }
    @Override
    public void render(Graphics g)  {
        this.graphics = g;
        currentSprite = Assets.tauren.crop(this.col * spriteWidth, 0, spriteWidth, spriteHeight);
        g.drawImage(currentSprite, this.x, this.y, null);

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
}
