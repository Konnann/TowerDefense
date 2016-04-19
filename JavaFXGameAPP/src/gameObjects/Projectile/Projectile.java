package gameObjects.Projectile;

import gameObjects.Enemy.Enemy;
import gameObjects.GameObject;

import java.awt.*;

public interface Projectile{

    public abstract void initialize();
    public abstract void tick ();
    public abstract void render(Graphics g);

    public int getStartX();
    public int getStartY();
    public Rectangle getBoundingBox();

}
