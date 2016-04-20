package entities;

import java.awt.*;

public interface ProjectileEntity {

    public abstract void initialize();
    public abstract void tick ();
    public abstract void render(Graphics g);

    public Rectangle getBounds();
    public boolean targetIsHit();

}
