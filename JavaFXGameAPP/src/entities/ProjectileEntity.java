package entities;

import gameObjects.Enemy.Enemy;

import java.awt.*;

public interface ProjectileEntity {

    public abstract void tick (Enemy[][] enemies);
    public abstract void render(Graphics g);

    public Rectangle getBounds();
    public boolean getTargetIsHit();
    public void setTargetIsHit(Boolean targetIsHit);
    public boolean getIsOutsideDisplay();

    public int getDamage();

}
