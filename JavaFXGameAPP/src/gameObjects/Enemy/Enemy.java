package gameObjects.enemy;

import gameObjects.GameObject;
import gameObjects.castle.CastleWall;

import java.awt.*;

public abstract class Enemy extends GameObject {

    public Rectangle boundingBox;
    public boolean isAttacking;

    public abstract void tick();
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