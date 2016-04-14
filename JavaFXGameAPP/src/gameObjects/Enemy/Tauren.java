package gameObjects.Enemy;
import graphics.Assets;
import graphics.SpriteSheet;
import graphics.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tauren extends Enemy {
    private Graphics graphics;
    private int x;                   //position of character on canvas
    private int y;                   //..

    private int health = 250;
    private int damage = 10;
    private int velocity = 3;        //speed
    private int goldWorth = 30;      //The reward when you kill the monster

    private boolean isDead = false;

    private SpriteSheet taurenSprite = Assets.tauren;
    private int spriteHeight = 86;
    private int spriteWidth = 100;
    private int walkFrames = 4;                 //walking frames in spritesheet
    private int attackFrames;
    private int col = 0;                        //Spritesheet column
    private int row = 1;                        //Spritesheet row
    private BufferedImage currentSprite;


    //As the game progresses we want the enemies to get tougher, in the beginning all parameters can be 0
    public Tauren(int increasedHealth, int increasedDamage, int increasedVelocity, int goldWorth){
        this.health += increasedHealth;
        this.damage += increasedDamage;
        this.velocity += increasedVelocity;
        this.goldWorth += goldWorth;
    }
    @Override
    public void tick(){
        //Animate
        this.col++;
        this.col %= walkFrames;
        //for debugging
        System.out.println("tauren tick");

        //update enemy position
        //this.x -= this.velocity;

        //enemies stop at end of screen , for testing purposes
        //if (this.x > 720) {
        //    this.x = 720;
        //}

    }
    @Override
    public void render(Graphics g) {
        this.graphics = g;
        currentSprite = Assets.tauren.crop(this.col * spriteWidth, 0, spriteWidth, spriteHeight);
        g.drawImage(currentSprite, this.x, this.y, null);
        System.out.println("tauren update");

    }

}
