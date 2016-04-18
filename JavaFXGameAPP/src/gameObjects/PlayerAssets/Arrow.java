package gameObjects.PlayerAssets;

import game.MouseMoving;
import graphics.Assets;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Arrow extends PlayerAssets {

    private int arrowWidht = 200 / 2;
    private int arrowHeight = 20;

    private int arrowX = -10;
    private int arrowY = 250 + 160 / 2 - 5;
    private BufferedImage arrowSprite = Assets.Arrow;
    private int arrowVelocity;

    public Rectangle arrowBoundingBox = new Rectangle(this.arrowWidht, this.arrowHeight);

    public void tick() {
        //Setting arrow's bounding box's bounds
        this.arrowBoundingBox.setBounds(arrowX, arrowY, arrowWidht, arrowHeight);
    }

    public void render(Graphics g) {
        Graphics2D gr = (Graphics2D) g;
        gr.setRenderingHint(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        int cx = 0;
        int cy = arrowSprite.getHeight() / 2;
        AffineTransform oldAT = gr.getTransform();
        gr.translate(cx + this.arrowX, cy + this.arrowY);
        gr.rotate(MouseMoving.getImageAngleRad(this.arrowX, this.arrowY));
        gr.translate(-cx, -cy);
        gr.drawImage(arrowSprite, 0, 0, null);
        gr.setTransform(oldAT);

        //draw arrow bounding box and move it
        int bx = 0;
        int by = arrowSprite.getHeight() / 2;
        AffineTransform oldATB = gr.getTransform();
        gr.translate(bx + this.arrowX, by + this.arrowY);
        gr.rotate(MouseMoving.getImageAngleRad(this.arrowX, this.arrowY));
        gr.translate(-bx, -by);
        g.setColor(Color.BLUE);
        g.drawRect(bx-15, by - 5, this.arrowBoundingBox.width, this.arrowBoundingBox.height);
        gr.setTransform(oldAT);
    }
}
