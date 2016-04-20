package gameObjects.PlayerAssets;

import game.Game;
import game.MouseInput;
import game.MouseMoving;
import gameObjects.Projectile.Arrow;
import graphics.Assets;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Crossbow extends PlayerAssets {

    private BufferedImage crossbowSprite = Assets.crossbow;
    private Arrow arrow = new Arrow();


    private int crossbowWidth = crossbowSprite.getWidth();
    private int crossbowHeight = crossbowSprite.getWidth();

    private int x = -crossbowWidth / 2;
    private int y = 260;
    private int yPos = 260;

    public Rectangle boundingBox = new Rectangle(this.crossbowWidth, this.crossbowHeight);

    //Create the crossbow
    public Crossbow() {
    }

    public double getY() {
        return this.y;
    }

    public int getX() {
        return this.x;
    }

    public int getYPos(){
        return this.yPos;
    }

    public void tick() {
        this.boundingBox.setBounds(this.x, this.y, this.crossbowWidth, this.crossbowHeight);
        this.arrow.tick();
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
        gr.translate(-cx, -cy);

        this.yPos = this.calcY((int)MouseMoving.y - 300);

        this.arrow.render(g, this.yPos);

        gr.drawImage(crossbowSprite, 0, this.yPos, null);
        gr.setTransform(oldAT);


    }

    private int calcY(int y){
        int maxTopRange = Game.getHeight() -  575;
        int ret = y;
        if(y < 0 && Math.abs(y) > maxTopRange){
            ret = -maxTopRange;
        }else if(y > 0 && Math.abs(y) > maxTopRange){
            ret = 170;
        }

        return ret;
    }

}
