package gameStates;

import display.Display;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;


public class MouseInput implements MouseListener {


    private Display display;
    private BufferStrategy bufferStrategy;
    private Graphics graphics;

    private int gameWidth=1280;
    private State gameState;

    public MouseInput(State gameState) {

        this.gameState=gameState;
    }

    @Override

    public void mouseClicked(MouseEvent e) {

    }


    public void mousePressed(MouseEvent e) {

      
        System.out.println("Enter");
        StateManager.setState(gameState);

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
