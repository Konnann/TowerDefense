package gameStates;

import display.Display;
import game.MouseInput;
import graphics.Assets;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;


public class MenuState extends State{

    private Display display;
    private BufferStrategy bufferStrategy;
    private Graphics g;

    private State gameState,menuState;
    private BufferedImage playButton,creditsButton,exitButton;

    public MenuState(Display display,BufferStrategy bufferStrategy,Graphics graphics) {
        this.display=display;
        this.bufferStrategy=bufferStrategy;
        this.g=graphics;
        initialize();

    }

    private void initialize(){
        gameState = new GameState(this.display,this.bufferStrategy,this.g);
        display.getCanvas().addMouseListener(new MouseInput(gameState,menuState));
        playButton = Assets.Buttons.crop(28, 17, 88, 93);
        creditsButton=Assets.Buttons.crop(272,316,85,85);
        exitButton=Assets.Buttons.crop(775,415,93,85);

    }


    public void tick() {


    }
@Override
    public void render() {
        this.bufferStrategy = this.display.getCanvas().getBufferStrategy();

        if(this.bufferStrategy == null){
            this.display.getCanvas().createBufferStrategy(2);
            this.bufferStrategy =this.display.getCanvas().getBufferStrategy();
        }

        this.g = this.bufferStrategy.getDrawGraphics();

        g.clearRect(0, 0, 1280, 720);
        this.g.drawImage(Assets.menuBackground, 0, 0, 1280, 720, null);




        this.g.drawImage(playButton, 596,250, null);
        this.g.drawImage(creditsButton,596,356,null);
        this.g.drawImage(exitButton,594,450,null);

        this.bufferStrategy.show();
        this.g.dispose();
    }
}
