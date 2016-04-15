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


    private State gameState;

    public MouseInput(State gameState) {

        this.gameState=gameState;
    }

    @Override

    public void mouseClicked(MouseEvent e) {

    }


    public void mousePressed(MouseEvent e) {
        int mx=e.getX();
        int my=e.getY();

        if(mx>=596&&mx<=684) {
            if(my>=250&&my<=343) {
                StateManager.setState(gameState);
            }
        }
        if(mx>=596&&mx<=681) {
            if(my>=356&&my<=441) {
                System.out.println("Credits");
            }
        }
        if(mx>=594&&mx<=689) {
            if(my>=450&&my<=535) {
                System.exit(0);
            }
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
