package gameObjects.Projectile;

import entities.Physics;
import entities.ProjectileEntity;
import gameObjects.Enemy.Enemy;
import gameObjects.PlayerAssets.PlayerAssets;
import graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Arrow extends PlayerAssets implements ProjectileEntity {

    private int width;
    private int height;

    private int damage;

    private int x;
    private int y;
    private int velocity;

    private BufferedImage arrowSprite;

    public Rectangle boundingBox;

    //Enemy[][] enemies;

    private boolean targetIsHit;

    //Create arrow
    public Arrow(int y){
        this.width = 100;
        this.height = 24;

        this.velocity = 30;
        this.damage = 5;

        this.y = y;
        this.x = 0;

        this.boundingBox = new Rectangle(this.x, this.y, this.width, this.height);
        this.arrowSprite = Assets.Arrow;

        this.targetIsHit = false;
    }

    public void tick(Enemy[][] enemies) {
        this.x += velocity;

        //updating arrow's bounding box
        this.boundingBox.setBounds(x, y, width, height);

        //Check for enemyCollision and attack if true
        if (enemies != null) {
            for (int i = 0; i < enemies.length; i++) {
                for (int j = 0; j < enemies[i].length; j++) {
                    if (enemies[i][j] != null) {
                        if (this.boundingBox.intersects(enemies[i][j].getBoundingBox())) {
                            this.targetIsHit = true;
                            enemies[i][j].takeDamage(this.damage);
                        }
                    }
                }
            }
        }


    }

    public void render(Graphics g) {
        g.drawImage(Assets.Arrow, x, y, width, height, null);

       // g.setColor(Color.blue);
       // g.drawRect(this.boundingBox.x, this.boundingBox.y, this.boundingBox.width, this.boundingBox.height);

    }

    @Override
    public Rectangle getBounds() {
        return this.boundingBox;
    }

    @Override
    public boolean getTargetIsHit() {
        return targetIsHit;
    }

    @Override
    public void setTargetIsHit(Boolean targetIsHit) {
        this.targetIsHit = targetIsHit;
    }

    @Override
    public int getDamage() {
        return damage;
    }


}
