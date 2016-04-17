package gameObjects.PlayerAssets;

import graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Towers extends PlayerAssets {
    private int health = 250;

    private int towerWidth = 95;
    private int towerHeight = 191;
    private BufferedImage towerSprite = Assets.tower;

    private int x;
    private int y;

    public Rectangle boundingBox = new Rectangle(this.towerWidth, this.towerHeight);

    //Create a tower
    public Towers() {
    }

    public void tick() {

    }

    public void render(Graphics g) {
        //drawing First Tower
        this.x = 83;
        this.y = 720 - 595;
        g.drawImage(towerSprite, this.x, this.y, this.towerWidth, this.towerHeight, null);

        //drawing bounding box for test (for First Tower)
        this.boundingBox.setBounds(this.x, this.y, this.towerWidth, this.towerHeight);
        g.setColor(Color.red);
        g.drawRect(this.boundingBox.x, this.boundingBox.y, this.boundingBox.width, this.boundingBox.height);

        //drawing Second Tower
        this.x = 83;
        this.y = 720 - (towerHeight + 27);
        g.drawImage(towerSprite, this.x, this.y, this.towerWidth, this.towerHeight, null);

        //drawing bounding box for test (for Second Tower)
        this.boundingBox.setBounds(this.x, this.y, this.towerWidth, this.towerHeight);
        g.setColor(Color.red);
        g.drawRect(this.boundingBox.x, this.boundingBox.y, this.boundingBox.width, this.boundingBox.height);
        this.x = 0;
        this.y = 0;
    }


    public int looseHealth() {
        return health;
    }

    public void increaseHealth(int healthIncrease) {
        this.health += healthIncrease;
    }
}
