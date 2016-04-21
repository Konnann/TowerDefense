package game;

import gameObjects.PlayerAssets.Crossbow;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMoving implements MouseMotionListener {
    private static double imageAngleRad = 0;

    public static double x = 0;
    public static double y = 0;

    public static boolean isDragged = false;


    public MouseMoving(){

    }
    @Override
    public void mouseDragged(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();

        Crossbow.isDragged = true;
    }


    public void mouseMoved(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
    }

    public static double getImageAngleRad(double crossbowX, double crossbowY){
        double dx = x - crossbowX;
        double dy = y - crossbowY;
        imageAngleRad = Math.atan2(dy, dx);
        return dy;
    }
}
