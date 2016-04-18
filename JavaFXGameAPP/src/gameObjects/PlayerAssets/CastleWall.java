package gameObjects.PlayerAssets;

import entities.BuildingEntity;
import game.Game;
import graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CastleWall extends PlayerAssets implements BuildingEntity{

    private int health = 500;

    private int spriteWidth = 82;
    private int spriteHeight = 575;
    private BufferedImage wallSprite = Assets.castleWall;

    private int x = 0;
    private int y = (Game.getHeight() - spriteHeight);

    public Rectangle boundingBox = new Rectangle(this.x, this.y, this.spriteWidth, this.spriteHeight);



    //Create a wall
    public CastleWall() {
    }

    public void tick(){
        this.boundingBox.setBounds(this.x, this.y, this.spriteWidth, this.spriteHeight);

    }

    public void render(Graphics g){
        g.drawImage(wallSprite, this.x, this.y, this.spriteWidth, this.spriteHeight, null );

        //drawing bounding boxes for test
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

    public void increaseHealth(int healthIncrease){
        this.health += healthIncrease;
    }
    public void takeDamage(int damage){this.health -= damage;}

}

