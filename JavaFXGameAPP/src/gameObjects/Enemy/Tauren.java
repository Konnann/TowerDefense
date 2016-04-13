package gameObjects.Enemy;
import graphics.SpriteSheet;
import graphics.ImageLoader;

import java.awt.*;

public class Tauren extends Enemy {

    private int x;                   //position of character on canvas
    private int y;                   //..

    private int health = 250;
    private int damage = 10;
    private int velocity = 3;        //speed
    private int goldWorth = 30;      //The reward when you kill the monster

    private boolean isDead = false;

    private int taurenSpriteHeight = 86;
    private int taurenSpriteWidth = 100;
    private int movingFramecount = 4;
    private int attackingFramecount;
    private int spriteFrameCounter = 1;
    private SpriteSheet spritesheet = new SpriteSheet(ImageLoader.loadImage("Tauren_100x86.png"));

    //As the game progresses we want the enemies to get tougher, in the beginning all parameters can be 0
    public Tauren(int increasedHealth, int increasedDamage, int increasedVelocity, int goldWorth){
        this.health += increasedHealth;
        this.damage += increasedDamage;
        this.velocity += increasedVelocity;
        this.goldWorth += goldWorth;
    }

    public void update(){
        //update enemy position
        this.x -= this.velocity;

        //enemies stop at end of screen , for testing purposes
        if (this.x > 720) {
            this.x = 720;
        }

    }
    public void draw(Graphics g) {
        //g.drawImage(Assets.player1, this.x, this.y, null);

        if(this.spriteFrameCounter % movingFramecount == 0){
            spriteFrameCounter = 1;
        }


    }

}
