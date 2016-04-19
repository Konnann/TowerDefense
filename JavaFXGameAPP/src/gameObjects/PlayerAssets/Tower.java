package gameObjects.PlayerAssets;

import entities.BuildingEntity;
import graphics.Assets;
import graphics.HealthBar;

import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Set;

import static javax.swing.text.StyledEditorKit.*;

public class Tower extends PlayerAssets implements BuildingEntity{
    private int health = 1500;

    private int towerWidth = 95;
    private int towerHeight = 191;
    private int towerRange = 600;
    private int healthWidth = towerWidth;
    private BufferedImage towerSprite = Assets.tower;

    private int x = 83;
    private int y = 720 - 595; //145

    public Rectangle boundingBox;
    public Rectangle TowerRange = new Rectangle(this.towerRange,this.towerHeight + towerHeight/2);
    public Rectangle TowerHealthBar = new Rectangle(this.healthWidth,7);

    private HealthBar healthBar;


    //Create a tower
    public Tower(int x, int y) {
        this.x = x;
        this.y = y;
        boundingBox = new Rectangle(this.x, this.y, this.towerWidth, this.towerHeight);
        healthBar = new HealthBar(this.x, this.y - 20, this.TowerHealthBar.width, this.TowerHealthBar.height, this.health);
    }

    public void tick() {

        healthBar.tick(this.x, this.y - 10, this.health);

    }

    public void render(Graphics g) {

        g.drawImage(towerSprite, this.x, this.y, this.towerWidth, this.towerHeight, null);

        //drawing bounding box for test
        this.boundingBox.setBounds(this.x, this.y, this.towerWidth, this.towerHeight);
        g.setColor(Color.red);
        g.drawRect(this.boundingBox.x, this.boundingBox.y, this.boundingBox.width, this.boundingBox.height);

        //drawing bounding box around the first tower as a rectangle, spreading ahead of the tower reaching a distance that's meant to be its range

        this.TowerRange.setBounds(this.x,this.y,this.TowerRange.width,
                this.TowerRange.height);
        g.setColor(Color.yellow);
        g.drawRect(this.TowerRange.x,this.TowerRange.y,this.TowerRange.width,this.TowerRange.height);

        healthBar.render(g);
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

    public int looseHealth() {
        return health;
    }

    public void increaseHealth(int healthIncrease) {
        this.health += healthIncrease;
    }
}
