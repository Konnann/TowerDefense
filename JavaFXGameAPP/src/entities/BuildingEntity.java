package entities;

import gameObjects.Enemy.Enemy;

import java.awt.*;

public interface BuildingEntity {
    public void tick(Enemy[][] enemies);
    public void render(Graphics g);
    public Rectangle getBounds();
    public void takeDamage(int damage);

    public double getX();
    public double getY();
    public int getHealth();
}
