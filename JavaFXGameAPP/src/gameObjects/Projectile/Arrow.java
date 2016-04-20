package gameObjects.Projectile;

import game.MouseMoving;
import gameObjects.PlayerAssets.PlayerAssets;
import graphics.Assets;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Arrow extends PlayerAssets {

    private int arrowWidth = 200 / 2;
    private int arrowHeight = 20;
    public boolean isShot = false;

    private int damage = 5;

    private int arrowX = -10;
    private int arrowY = 250 + 160 / 2 - 5;
    private BufferedImage arrowSprite = Assets.Arrow;
    private int arrowVelocity;

    public Rectangle arrowBoundingBox = new Rectangle(this.arrowWidth, this.arrowHeight);

    public void tick() {
        //Setting arrow's bounding box's bounds

        this.arrowBoundingBox.setBounds(arrowX, arrowY, arrowWidth, arrowHeight);
        if(isShot){
            System.out.println("strelq wwe");
        }

    }

    public void render(Graphics g, int y) {
      Graphics2D gr = (Graphics2D) g;

      gr.setRenderingHint(
              RenderingHints.KEY_RENDERING,
              RenderingHints.VALUE_RENDER_QUALITY);

        AffineTransform oldAT = gr.getTransform();

      gr.drawImage(arrowSprite, 90, y + 65, null);          //@TODO @var y hardoded // FIXME: 20.4.2016
      /*
      int bx = 0;
      int by = arrowSprite.getHeight() / 2;
      AffineTransform oldATB = gr.getTransform();
      gr.translate(bx + this.arrowX, by + this.arrowY);
      gr.rotate(MouseMoving.getImageAngleRad(this.arrowX, this.arrowY));
      gr.translate(-bx, -by);
      g.setColor(Color.BLUE);
      g.drawRect(bx-15, by - 5, this.arrowBoundingBox.width, this.arrowBoundingBox.height);/
      gr.setTransform(oldAT);*/
    }
}
