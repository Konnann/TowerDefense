package gameObjects.Weapons;

import graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Crossbow extends Weapons{

    private int health = 300;

    private int crossbowWidth = 193;
    private int crossbowHeight = 186;
    private BufferedImage crossbowSprite = Assets.crossbow;

    private int x = - crossbowWidth/2;
    private int y = 260;

    public Rectangle boundingBox = new Rectangle(this.crossbowWidth, this.crossbowHeight);

    //Create the crossbow
    public Crossbow() {
    }

    public void tick(){
        this.boundingBox.setBounds(this.x, this.y, this.crossbowWidth, this.crossbowHeight);

    }

    public void render(Graphics g){
        g.drawImage(crossbowSprite, this.x, this.y, this.crossbowWidth, this.crossbowHeight, null );

        //drawing bounding boxes for test
        g.setColor(Color.BLUE);
        g.drawRect(this.boundingBox.x, this.boundingBox.y, this.boundingBox.width, this.boundingBox.height);
    }
}
