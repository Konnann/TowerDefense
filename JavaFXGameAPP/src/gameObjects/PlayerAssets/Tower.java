package gameObjects.PlayerAssets;

import entities.BuildingEntity;
import entities.ProjectileEntity;
import gameObjects.Enemy.Enemy;
import gameObjects.Projectile.MagicAttack;
import graphics.Assets;
import graphics.HealthBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Tower extends PlayerAssets implements BuildingEntity{
    private int health = 1500;
    private double attackSpeed = 1.0;

    private int towerWidth = 95;
    private int towerHeight = 191;
    private int range = 600;
    private int rangeX;
    private int rangeY;
    private int healthWidth = towerWidth;
    private BufferedImage towerSprite = Assets.tower;

    private int x;
    private int y;

    private int projectileX;
    private int projectileY;

    public Rectangle boundingBox;
    public Rectangle towerRange;
    public Rectangle towerHealthBar = new Rectangle(this.healthWidth,7);
    private HealthBar healthBar;

    private ArrayList<ProjectileEntity> projectiles = new ArrayList<ProjectileEntity>();
    private Enemy[][] enemies;
    private Enemy target;

    private boolean targeted;
    private boolean shoot;

    private Timer timer;
    private ActionListener actionListener;




    //Create a tower
    public Tower(int x, int y, Enemy[][] enemies) {
        this.x = x;
        this.y = y;
        this.boundingBox = new Rectangle(this.x, this.y, this.towerWidth, this.towerHeight);
        this.healthBar = new HealthBar(this.x, this.y - 20, this.towerHealthBar.width, this.towerHealthBar.height, this.health);
        this.projectileX = x + 40;
        this.projectileY = y - 3;
        this.enemies = enemies;
        this.targeted = false;
        this.shoot = false;
        this.rangeX = this.x;
        this.rangeY = y - 100;
        this.towerRange = new Rectangle(rangeX, rangeY, range , towerHeight + 200);
        actionListener = e -> shoot = true;
        timer = new Timer((int)(attackSpeed * 1000), actionListener);
        timer.start();
    }

    public void tick(Enemy[][] enemies) {

        //Update enemy list
        this.enemies = enemies;
        if (enemies != null) {
        for (int col = 0; col < 12; col++) {
            for (int row = 0; row < 5; row++) {
                    if (enemies[row][col] != null) {
                    if (enemies[row][col].isAlive() == false) {
                        enemies[row][col] = null;
                    }
                }
            }
        }


        //Target enemy
            if (!targeted) {
            target = acquireTarget();
        }
        //Shoot enemy
            if (target != null && shoot) {
            shoot(target, target.getX(), target.getY());
            shoot = false;
        }
        //check if target is alive
            if (target == null || target.isAlive() == false) {
            targeted = false;
        }


        //Update projectiles
            if (projectiles != null) {
            for (int i = 0; i < projectiles.size(); i++) {
                projectiles.get(i).tick();

                    if (projectiles.get(i).targetIsHit() == true) {
                    projectiles.remove(i);
                }
            }
        }
        }

        //Update Healthbar
        healthBar.tick(this.x, this.y - 10, this.health);
    }



    public void render(Graphics g) {

        g.drawImage(towerSprite, this.x, this.y, this.towerWidth, this.towerHeight, null);

        //drawing bounding box for test

        g.setColor(Color.red);
        g.drawRect(this.boundingBox.x, this.boundingBox.y, this.boundingBox.width, this.boundingBox.height);

        //drawing bounding box around the first tower as a rectangle, spreading ahead of the tower reaching a distance that's meant to be its range


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
    private Enemy acquireTarget(){
        Enemy closest = null;
        float closestDistance = 10_000;

        for (int col = 0; col < 12; col++) {
            for (int row = 0; row < 5; row++) {
                if(enemies[row][col] != null){
                    if(isInRange(enemies[row][col]) && findDistance(enemies[row][col]) < closestDistance){
                        closestDistance = findDistance(enemies[row][col]);
                        closest = enemies[row][col];
                    }
                }
            }
        }
        if (closest != null){
            targeted = true;
        }
        return closest;
    }
    private boolean isInRange(Enemy e){

        if (towerRange.intersects(e.getBoundingBox())) {
            System.out.println("in range");
            return true;
        }
        return false;
    }

    private float findDistance(Enemy e){
        float xDistance = Math.abs(e.getX() - x);
        float yDistance = Math.abs(e.getY() - y);
        return xDistance + yDistance;
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
