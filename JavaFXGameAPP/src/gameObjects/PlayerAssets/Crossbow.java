package gameObjects.PlayerAssets;

import game.MouseMoving;
import graphics.Assets;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Crossbow extends PlayerAssets {

    private int health = 300;

    private BufferedImage crossbowSprite = Assets.crossbow;
    private int crossbowWidth = crossbowSprite.getWidth();
    private int crossbowHeight = crossbowSprite.getWidth();

    private int x = -crossbowWidth / 2;
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

    public void tick() {
        this.boundingBox.setBounds(this.x, this.y, this.crossbowWidth, this.crossbowHeight);
    }

    public void render(Graphics g) {
        Graphics2D gr = (Graphics2D) g;
        gr.setRenderingHint(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        int cx = crossbowSprite.getWidth() / 2 - 20;
        int cy = crossbowSprite.getHeight() / 2;
        AffineTransform oldAT = gr.getTransform();
        gr.translate(cx + this.x, cy + this.y);
        gr.rotate(MouseMoving.getImageAngleRad(this.x + 55, this.y + 55));
        gr.translate(-cx, -cy);
        gr.drawImage(crossbowSprite, 0, 0, null);
        gr.setTransform(oldAT);

        //drawing bounding boxes for test
        gr.setColor(Color.BLUE);
        gr.drawRect(this.boundingBox.x, this.boundingBox.y, this.boundingBox.width, this.boundingBox.height);
    }
}
