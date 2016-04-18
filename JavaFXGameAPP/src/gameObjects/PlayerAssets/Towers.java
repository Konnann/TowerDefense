package gameObjects.PlayerAssets;

import graphics.Assets;

import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Set;

import static javax.swing.text.StyledEditorKit.*;

public class Towers extends Assets{
    private int hitPoints = 250;

    private int towerWidth = 95;
    private int towerHeight = 191;
    private int towerRange = 600;
    private int health = towerWidth;
    private BufferedImage towerSprite = Assets.tower;

    private int x;
    private int y;

    public Rectangle boundingBox = new Rectangle(towerWidth, towerHeight);
    public Rectangle firstTowerRange = new Rectangle(this.towerRange,this.towerHeight + towerHeight/2);
    public Rectangle secondTowerRange = new Rectangle(this.towerRange,this.towerHeight + towerHeight/2);
    public Rectangle firstTowerHealthBar = new Rectangle(this.health,7);
    public Rectangle secondTowerHealthBar = new Rectangle(this.health,7);

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

        //drawing bounding box around the first tower as a rectangle, spreading ahead of the tower reaching a distance that's meant to be its range

        this.firstTowerRange.setBounds(this.x,this.y,this.firstTowerRange.width,
                this.firstTowerRange.height);
        g.setColor(Color.yellow);
        g.drawRect(this.firstTowerRange.x,this.firstTowerRange.y,this.firstTowerRange.width,this.firstTowerRange.height);

        //drawing the health bar of the first tower

        this.firstTowerHealthBar.setBounds(this.x,this.y-10,this.firstTowerHealthBar.width,this.firstTowerHealthBar.height);
        g.setColor(new Color(190,50,50));
        g.fillRect(this.firstTowerHealthBar.x,this.firstTowerHealthBar.y-5,this.towerWidth,this.firstTowerHealthBar.height);

        g.setColor(new Color(50,180,50));
        g.fillRect(this.firstTowerHealthBar.x,this.firstTowerHealthBar.y-5,this.firstTowerHealthBar.width,this.firstTowerHealthBar.height);

        g.setColor(new Color(0,0,0));
        g.drawRect(this.firstTowerHealthBar.x,this.firstTowerHealthBar.y-5,this.firstTowerHealthBar.width,this.firstTowerHealthBar.height);

        //drawing Second Tower

        this.x = 83;
        this.y = 720 - (towerHeight + 27);
        g.drawImage(towerSprite, this.x, this.y, this.towerWidth, this.towerHeight, null);

        //drawing bounding box for test (for Second Tower)

        this.boundingBox.setBounds(this.x, this.y, this.towerWidth, this.towerHeight);
        g.setColor(Color.red);
        g.drawRect(this.boundingBox.x, this.boundingBox.y-5, this.boundingBox.width, this.boundingBox.height);

        //drawing bounding box around the second tower as a  rectangle, spreading ahead of the tower reaching a distance that's meant to be its range

        this.secondTowerRange.setBounds(this.x,725 - (this.towerHeight + 27) - this.towerHeight/2,this.secondTowerRange.width,
                this.secondTowerRange.height);
        g.setColor(Color.yellow);

        g.drawRect(this.secondTowerRange.x,this.secondTowerRange.y,this.secondTowerRange.width,this.secondTowerRange.height);

        //drawing the health bar of the second tower

        this.secondTowerHealthBar.setBounds(this.x,this.y-10,this.secondTowerHealthBar.width,this.secondTowerHealthBar.height);

        g.setColor(new Color(190,50,50));
        g.fillRect(this.secondTowerHealthBar.x,this.secondTowerHealthBar.y-5,this.towerWidth,this.secondTowerHealthBar.height);


        g.setColor(new Color(50,180,50));
        g.fillRect(this.secondTowerHealthBar.x,this.secondTowerHealthBar.y-5,this.secondTowerHealthBar.width,this.secondTowerHealthBar.height);

        g.setColor(new Color(0,0,0));
        g.drawRect(this.secondTowerHealthBar.x,this.secondTowerHealthBar.y-5,this.secondTowerHealthBar.width,this.secondTowerHealthBar.height);

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
