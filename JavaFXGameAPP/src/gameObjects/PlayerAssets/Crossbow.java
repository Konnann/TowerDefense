package gameObjects.PlayerAssets;

import game.MouseMoving;
import graphics.Assets;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Crossbow extends PlayerAssets {

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

    public double getY() {
        return y;
    }

    public int getX() {
        return x;
    }
    public void tick(){
        this.boundingBox.setBounds(this.x, this.y, this.crossbowWidth, this.crossbowHeight);

    }

    public void render(Graphics g){
        Graphics2D gr=(Graphics2D)g;
        gr.setRenderingHint(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        int cx = crossbowSprite.getWidth() / 2;
        int cy = crossbowSprite.getHeight() / 2;
        AffineTransform oldAT = gr.getTransform();
        g.translate(cx+x, cy+y);
        gr.rotate(MouseMoving.getImageAngleRad(this.x, this.y));
        g.translate(-cx, -cy);
        gr.drawImage(crossbowSprite, 0, 0, null);
        gr.setTransform(oldAT);

        //drawing bounding boxes for test
        g.setColor(Color.BLUE);
        g.drawRect(this.boundingBox.x, this.boundingBox.y, this.boundingBox.width, this.boundingBox.height);
    }
}
