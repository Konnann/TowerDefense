package gameStates;

import display.Display;
import graphics.Assets;
import java.awt.*;
import java.awt.image.BufferStrategy;




public class MenuState extends State{

    private Display display;
    private BufferStrategy bufferStrategy;
    private Graphics g;

    private State gameState;

    private Rectangle playButton=new Rectangle(1280/2,150,100,50);


    public MenuState(Display display,BufferStrategy bufferStrategy,Graphics graphics) {
        this.display=display;
        this.bufferStrategy=bufferStrategy;
        this.g=graphics;
        initialize();

    }

    private void initialize(){
        gameState = new GameState(this.display,this.bufferStrategy,this.g);
        display.getCanvas().addMouseListener(new MouseInput(gameState));


    }


    public void tick() {


    }

    public void render() {
        this.bufferStrategy = this.display.getCanvas().getBufferStrategy();

        if(this.bufferStrategy == null){
            this.display.getCanvas().createBufferStrategy(2);
            this.bufferStrategy =this.display.getCanvas().getBufferStrategy();
        }

        this.g = this.bufferStrategy.getDrawGraphics();

        Graphics2D g2d=(Graphics2D)g;

        Font font=new Font("arial",Font.BOLD,50);
         this.g.drawImage(Assets.menuBackground, 0, 0, 1280, 720, null);

        g2d.setColor(Color.WHITE);
        g2d.draw(playButton);

        this.g.dispose();
        this.bufferStrategy.show();
    }
}
