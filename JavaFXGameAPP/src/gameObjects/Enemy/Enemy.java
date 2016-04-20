package gameObjects.Enemy;

import entities.BuildingEntity;
import gameObjects.GameObject;
import gameObjects.PlayerAssets.CastleWall;
import sun.awt.image.ImageWatched;

import java.awt.*;
import java.util.LinkedList;

public interface Enemy {

    public abstract void tick(LinkedList<BuildingEntity> buildingEntities);
    public abstract void render(Graphics g);


    public void takeDamage(int damage);
    public void intersect(Enemy enemy, CastleWall wall);
    public void isAttacking(boolean isAttacking);

    public int getX();
    public int getY();
    public Rectangle getBoundingBox();
    public boolean isAlive();



}