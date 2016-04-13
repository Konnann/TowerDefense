package gameStates;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {
    int gameWidth=1280;



    @Override
    public void mouseClicked(MouseEvent e) {

    }


    public void mousePressed(MouseEvent e) {
       // State gameState=new gameState(arguments)
        int mx=e.getX();
        int my=e.getY();

        if (mx>=gameWidth/2+120 && mx<=gameWidth/2+220){
       //     StateManager.setState(gameState);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
