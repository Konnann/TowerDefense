package game;

import gameObjects.PlayerAssets.Crossbow;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMoving implements MouseMotionListener {
    private static double imageAngleRad = 0;

    private static double x = 0;
    private static double y = 0;


    public MouseMoving(){

    }
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    //@Override
    //public void mouseMoved(MouseEvent e) {
    //    dx = e.getX() - Crossbow.getX();
    //    dy = e.getY() - Crossbow.getY();
    //    imageAngleRad = Math.atan2(dy, dx);
    //}

    //public static double getImageAngleRad() {
    //    return imageAngleRad;
    //}

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    public static double getImageAngleRad(double crossbowX, double crossbowY){
        double dx = x - crossbowX;
        double dy = y - crossbowY;
        imageAngleRad = Math.atan2(dy, dx);
        return imageAngleRad;
    }
}
