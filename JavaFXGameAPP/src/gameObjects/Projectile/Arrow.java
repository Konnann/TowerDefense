package gameObjects.Projectile;

import entities.ProjectileEntity;
import game.MouseMoving;
import gameObjects.PlayerAssets.PlayerAssets;
import graphics.Assets;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Arrow extends PlayerAssets implements ProjectileEntity {

    private int arrowWidth;
    private int arrowHeight;

    private int damage;

    private int arrowX = -10;
    private int arrowY;
            //= 250 + 160 / 2 - 5;
    private BufferedImage arrowSprite = Assets.Arrow;
    private int arrowVelocity;

    public Rectangle arrowBoundingBox = new Rectangle(this.arrowWidth, this.arrowHeight);

    public Arrow(int y){
        this.arrowWidth = 100;
        this.arrowHeight = 24;

        this.arrowVelocity = 30;
        this.damage = 5;

        this.arrowY = y;
        this.arrowX = 0;

        arrowBoundingBox = new Rectangle(this.arrowX, this.arrowY, this.arrowWidth, this.arrowHeight);

        initialize();
    }
    @Override
    public void initialize() {

    }

    public void tick() {
        this.arrowX  += arrowVelocity;
        //updating arrow's bounding box
        this.arrowBoundingBox.setBounds(arrowX, arrowY, arrowWidth, arrowHeight);



    }

    public void render(Graphics g) {
        g.drawImage(Assets.Arrow,arrowX, arrowY, arrowWidth, arrowHeight, null);


     // Graphics2D gr = (Graphics2D) g;
//
     // gr.setRenderingHint(
     //         RenderingHints.KEY_RENDERING,
     //         RenderingHints.VALUE_RENDER_QUALITY);
//
     //   AffineTransform oldAT = gr.getTransform();
//
     //// gr.drawImage(arrowSprite, 90, y + 65, null);          //@TODO @var y hardcoded // FIXME: 20.4.2016
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

    @Override
    public Rectangle getBounds() {
        return null;
    }

    @Override
    public boolean targetIsHit() {
        return false;
    }
}
