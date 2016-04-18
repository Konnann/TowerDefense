package entities;

import java.awt.*;

public interface BuildingEntity {
    public void tick();
    public void render(Graphics g);
    public Rectangle getBounds();

    public double getX();
    public double getY();

}
