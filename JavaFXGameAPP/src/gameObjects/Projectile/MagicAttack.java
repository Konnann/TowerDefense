package gameObjects.Projectile;

import entities.ProjectileEntity;
import gameObjects.Enemy.Enemy;
import graphics.Animation;
import graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MagicAttack implements ProjectileEntity {

    private Enemy target;

    private double x,y;

    private int spriteSide;

    private int damage;
    private double xVelocity;
    private double yVelocity;
    private double attackSpeed;

    private BufferedImage[] sprites;
    private Animation animation;

    private Rectangle boundingBox;

    private boolean targetIsHit;

    //create a projectile
    public MagicAttack (Enemy target,int startX, int startY){
        this.x = startX;
        this.y = startY;

        this.damage = 50;
        this.xVelocity = 0;
        this.yVelocity = 0;
        this.attackSpeed = 20;

        spriteSide = 25;

        sprites = new BufferedImage[2];
        for (int i = 0; i < sprites.length ; i++) {
            sprites[i] = Assets.Magic.crop(0, 0, this.spriteSide, spriteSide);
        }
        this.animation = new Animation(sprites, 5);
        this.boundingBox = new Rectangle((int)this.x, (int)this.y, spriteSide, spriteSide);

        this.target = target;
        targetIsHit = false;

        calculateDirection();

    }


    @Override
    public void tick(Enemy[][] enemies) {
        calculateDirection();
        if(target != null) {
            if (this.boundingBox.intersects(target.getBoundingBox())) {
                this.targetIsHit = true;
                target.takeDamage(damage);
            }
        }
        this.x += xVelocity * attackSpeed;
        this.y += yVelocity * attackSpeed;

        this.boundingBox = new Rectangle((int)this.x, (int)this.y, spriteSide, spriteSide);
        this.animation.update();

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.drawRect(boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);


        g.drawImage(animation.getSprite(),(int)x, (int)y, null);
    }



    private void calculateDirection(){
        double totalAllowedMovement = 1.0;
        double xDistanceFromTarget = Math.abs(target.getX() - x);
        double yDistanceFromTarget = Math.abs(target.getY() - y);
        double totalDistanceFromTarget = xDistanceFromTarget + yDistanceFromTarget;
        double xPercentOfMovement = xDistanceFromTarget / totalDistanceFromTarget;
        this.xVelocity = xPercentOfMovement;
        this.yVelocity = totalAllowedMovement - xPercentOfMovement;

        if(target.getX() < x){
            xVelocity *= -1;
        }
        if(target.getY() < y){
            yVelocity *= -1;
        }
    }
    @Override
    public Rectangle getBounds() {
        return boundingBox;
    }

    @Override
    public boolean getTargetIsHit() {
        return targetIsHit;
    }

    @Override
    public void setTargetIsHit(Boolean targetIsHit) {
        this.targetIsHit = targetIsHit;
    }

    public Enemy getTarget(){
        return target;
    }

    @Override
    public int getDamage() {
        return damage;
    }
}
