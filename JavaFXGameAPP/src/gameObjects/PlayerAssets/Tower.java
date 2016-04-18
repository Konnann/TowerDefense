package gameObjects.PlayerAssets;

import entities.BuildingEntity;
import graphics.Assets;

import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Set;

import static javax.swing.text.StyledEditorKit.*;

public class Tower extends PlayerAssets implements BuildingEntity{
    private int hitPoints = 250;

    private int towerWidth = 95;
    private int towerHeight = 191;
    private int towerRange = 600;
    private int health = towerWidth;
    private BufferedImage towerSprite = Assets.tower;

    private int x = 83;
    private int y = 720 - 595; //145

    public Rectangle boundingBox;
    public Rectangle TowerRange = new Rectangle(this.towerRange,this.towerHeight + towerHeight/2);
    public Rectangle TowerHealthBar = new Rectangle(this.health,7);


    //Create a tower
    public Tower(int x, int y) {
        this.x = x;
        this.y = y;
        boundingBox = new Rectangle(this.x, this.y, this.towerWidth, this.towerHeight);

    }

    public void tick() {

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


        this.TowerHealthBar.setBounds(this.x,this.y-10,this.TowerHealthBar.width,this.TowerHealthBar.height);
        g.setColor(new Color(190,50,50));
        g.fillRect(this.TowerHealthBar.x,this.TowerHealthBar.y-5,this.towerWidth,this.TowerHealthBar.height);

        g.setColor(new Color(50,180,50));
        g.fillRect(this.TowerHealthBar.x,this.TowerHealthBar.y-5,this.TowerHealthBar.width,this.TowerHealthBar.height);

        g.setColor(new Color(0,0,0));
        g.drawRect(this.TowerHealthBar.x,this.TowerHealthBar.y-5,this.TowerHealthBar.width,this.TowerHealthBar.height);

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

    public int looseHealth() {
        return health;
    }

    public void increaseHealth(int healthIncrease) {
        this.health += healthIncrease;
    }
}
