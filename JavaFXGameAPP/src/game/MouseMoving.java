package game;

import gameObjects.PlayerAssets.Crossbow;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMoving implements MouseMotionListener {
    private static double imageAngleRad ;



    public MouseMoving(){

    }
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        double dx = e.getX() - Crossbow.getX();
        double dy = e.getY() - Crossbow.getY();
        imageAngleRad = Math.atan2(dy, dx);
    }
    public static double getImageAngleRad(){
        return imageAngleRad;
    }
}
