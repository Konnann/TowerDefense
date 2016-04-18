package gameObjects.Enemy;

import entities.BuildingEntity;
import gameObjects.GameObject;
import gameObjects.PlayerAssets.CastleWall;
import sun.awt.image.ImageWatched;

import java.awt.*;
import java.util.LinkedList;

public abstract class Enemy extends GameObject {

    public Rectangle boundingBox;
    public boolean isAttacking;

    public abstract void tick(LinkedList<BuildingEntity> buildingEntities);
    public abstract void render(Graphics g);

    public int fly(int height, int xPosition){
        return xPosition += height;
    }

    public int takeDamage(int damage, int healthPoints) {

        healthPoints = healthPoints - damage;
        return healthPoints;
    }
    public static void intersect(Enemy enemy, CastleWall wall){
        if(enemy.boundingBox.intersects(wall.boundingBox)){
            enemy.isAttacking = true;
        }
    }

}