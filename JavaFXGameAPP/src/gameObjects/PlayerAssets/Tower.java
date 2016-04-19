package gameObjects.PlayerAssets;

import entities.BuildingEntity;
import gameObjects.Enemy.Enemy;
import gameObjects.Projectile.MagicAttack;
import graphics.Assets;
import graphics.HealthBar;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Tower extends PlayerAssets implements BuildingEntity{
    private int health = 1500;
    private double attackSpeed = 1.0;

    private int towerWidth = 95;
    private int towerHeight = 191;
    private int range = 600;
    private int healthWidth = towerWidth;
    private BufferedImage towerSprite = Assets.tower;

    private int x;
    private int y;

    private int projectileX;
    private int projectileY;

    public Rectangle boundingBox;
    public Rectangle towerRange = new Rectangle(this.range,this.towerHeight + towerHeight/2);
    public Rectangle towerHealthBar = new Rectangle(this.healthWidth,7);
    private HealthBar healthBar;

    private ArrayList<MagicAttack> projectiles = new ArrayList<MagicAttack>();
    private Enemy[][] enemies;




    //Create a tower
    public Tower(int x, int y, Enemy[][] enemies) {
        this.x = x;
        this.y = y;
        boundingBox = new Rectangle(this.x, this.y, this.towerWidth, this.towerHeight);
        healthBar = new HealthBar(this.x, this.y - 20, this.towerHealthBar.width, this.towerHealthBar.height, this.health);
        projectileX = x + 20;
        projectileY = y;
        this.enemies = enemies;

    }

    public void tick() {
        for (int col = 0; col < 1 ; col++) { //12
            for (int row = 0; row < 1 ; row++) { //5
                if(enemies[row][col] != null) {
                    if (towerRange.intersects(enemies[row][col].getBoundingBox())) {
                        shoot(enemies[row][col], enemies[row][col].getX(),enemies[row][col].getY());
                        System.out.println("in range");

                    }
                }
            }
        }
        if(projectiles != null) {
            for (int i = 0; i < projectiles.size(); i++) {
                projectiles.get(i).tick();
            }
        }

        healthBar.tick(this.x, this.y - 10, this.health);
    }


    public void render(Graphics g) {

        g.drawImage(towerSprite, this.x, this.y, this.towerWidth, this.towerHeight, null);

        //drawing bounding box for test
        this.boundingBox.setBounds(this.x, this.y, this.towerWidth, this.towerHeight);
        g.setColor(Color.red);
        g.drawRect(this.boundingBox.x, this.boundingBox.y, this.boundingBox.width, this.boundingBox.height);

        //drawing bounding box around the first tower as a rectangle, spreading ahead of the tower reaching a distance that's meant to be its range

        this.towerRange.setBounds(this.x,this.y,this.towerRange.width,
                this.towerRange.height);
        g.setColor(Color.yellow);
        g.drawRect(this.towerRange.x,this.towerRange.y,this.towerRange.width,this.towerRange.height);

        //draw projectiles
        if(projectiles != null) {
            for (int i = 0; i < projectiles.size(); i++) {
                projectiles.get(i).render(g);
            }
        }
        healthBar.render(g);

    }

    public void shoot(Enemy target, int endX, int endY){
        projectiles.add(new MagicAttack(target, projectileX, projectileY, endX, endY));

    }

    @Override
    public Rectangle getBounds() {
        return boundingBox;
    }

    @Override
    public void takeDamage(int damage) {
        this.health -= damage;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public int getHealth() {
        return health;
    }

    public int looseHealth() {
        return health;
    }

    public void increaseHealth(int healthIncrease) {
        this.health += healthIncrease;
    }
}
