package game;

import display.Display;
import gameObjects.PlayerAssets.Crossbow;
import gameStates.State;
import gameStates.StateManager;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;


public class MouseInput implements MouseListener {


    public static boolean mousePressed;

    private State gameState,menuState;

    public MouseInput(State gameState,State menuState) {
        this.gameState = gameState;
        this.menuState = menuState;
        this.mousePressed = false;
    }

    @Override

    public void mouseClicked(MouseEvent e) {

    }


    public void mousePressed(MouseEvent e) {
        Crossbow.isDragged = true;
        this.mousePressed = true;

        int mx = e.getX();
        int my = e.getY();

        if (StateManager.getState() != gameState) {

            //Main Menu

            //playButton
            if (mx >= 596 && mx <= 684) {
                if (my >= 250 && my <= 343) {
                    StateManager.setState(gameState);
                }
            }
            //CreditsButton
            if (mx >= 596 && mx <= 681) {
                if (my >= 356 && my <= 441) {
                    System.out.println("Credits");
                }
            }
            //ExitButton
            if (mx >= 594 && mx <= 689) {
                if (my >= 450 && my <= 535) {
                    System.exit(0);
                }
            }

        }
        //In Game Menu


        //Pause Button
        if (StateManager.getState() == gameState) {
            if (mx >= 1100 && mx <= 1149) {
                if (my >= 9 && my <= 57) {
                    //PauseButton
                }
            }
            //MenuButton
            if (StateManager.getState() == gameState) {
                if (mx >= 1160 && mx <= 1210) {
                    if (my >= 7 && my <= 59) {
                        StateManager.setState(menuState);
                    }
                }
                //ExitButton
        if (mx >= 1220 && mx <= 1269) {
            if (my >= 10 && my <= 59) {
                System.exit(0);
            }
        }
    }
}
}

@Override
public void mouseReleased(MouseEvent e) {
        Crossbow.isDragged = false;
        this.mousePressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
