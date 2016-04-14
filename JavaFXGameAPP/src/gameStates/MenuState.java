package gameStates;

import display.Display;
import game.Game;
import graphics.Assets;
import graphics.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;


public class MenuState extends State{

    private Display display;
    private BufferStrategy bufferStrategy;
    private Graphics graphics;

    private Rectangle playButton=new Rectangle(1280/2,150,100,50);


    public MenuState(Display display,BufferStrategy bufferStrategy,Graphics graphics) {
        this.display=display;
        this.bufferStrategy=bufferStrategy;
        this.graphics=graphics;

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
