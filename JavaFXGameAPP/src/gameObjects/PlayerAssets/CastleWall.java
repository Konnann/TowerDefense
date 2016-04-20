package gameObjects.PlayerAssets;

import entities.BuildingEntity;
import game.Game;
import gameObjects.Enemy.Enemy;
import graphics.Assets;
import graphics.HealthBar;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CastleWall extends PlayerAssets implements BuildingEntity{

    private int health = 5000;

    private int spriteWidth = 82;
    private int spriteHeight = 575;
    private BufferedImage wallSprite = Assets.castleWall;

    private int x = 0;
    private int y = (Game.getHeight() - spriteHeight);

    public Rectangle boundingBox = new Rectangle(this.x, this.y, this.spriteWidth, this.spriteHeight);

    private int barWidth = 1280;
    private int barHeight = 10;
    private HealthBar healthBar;


    //Create a wall
    public CastleWall() {
        this.healthBar = new HealthBar(0, 0, barWidth, barHeight, health);
    }

    public void tick(Enemy[][] enemies){
        this.boundingBox.setBounds(this.x, this.y, this.spriteWidth, this.spriteHeight);
        this.healthBar.tick(0, 0, health);
    }

    public void render(Graphics g){
        g.drawImage(wallSprite, this.x, this.y, this.spriteWidth, this.spriteHeight, null );
        this.healthBar.render(g);
        //drawing bounding boxes for test
        g.setColor(Color.red);
        g.drawRect(this.boundingBox.x, this.boundingBox.y, this.boundingBox.width, this.boundingBox.height);
    }

    @Override
    public Rectangle getBounds() {

        return boundingBox;
    }

    @Override
    public void takeDamage(int damage) {
        this.health -= damage;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public int getHealth() {
        return health;
    }

    public void increaseHealth(int healthIncrease){

        this.health += healthIncrease;
    }

}

