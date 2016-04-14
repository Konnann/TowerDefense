package gameStates;

import display.Display;
import game.Game;
import graphics.Assets;
import graphics.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;


public class MenuState extends State{

    private Display display;
    private BufferStrategy bufferStrategy;
    private Graphics graphics;

    private State gameState;

    private Rectangle playButton=new Rectangle(1280/2,150,100,50);


    public MenuState(Display display,BufferStrategy bufferStrategy,Graphics graphics) {
        this.display=display;
        this.bufferStrategy=bufferStrategy;
        this.graphics=graphics;
        initialize();

    }

    private void initialize(){
        gameState=new GameState(this.display,this.bufferStrategy,this.graphics);
        display.getCanvas().addMouseListener(new MouseInput(gameState));


    }


    public void tick() {


    }

    public void render() {
        Graphics g=this.graphics;
        Graphics2D g2d=(Graphics2D)g;

        Font font=new Font("arial",Font.BOLD,50);
         g.drawImage(Assets.menuBackground, 0, 0, 1280, 720, null);

        g2d.setColor(Color.WHITE);
        g2d.draw(playButton);

        g.dispose();
        this.bufferStrategy.show();
    }
}
