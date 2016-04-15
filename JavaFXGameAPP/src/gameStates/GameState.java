package gameStates;

import display.Display;
import gameObjects.Weapons.Crossbow;
import gameObjects.Weapons.Towers;
import gameObjects.castle.CastleWall;
import graphics.Assets;
import gameObjects.Enemy.Tauren;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class GameState extends State {

    private Display display;
    private BufferStrategy bufferStrategy;
    private Graphics graphics;

    private State gameState,menuState;


    private Tauren tauren = new Tauren();
    private CastleWall wall = new CastleWall();
    private Towers tower = new Towers();
    private Crossbow crossbow = new Crossbow();
    private BufferedImage returnInMenuButton,exitButton;

    public GameState(Display display,BufferStrategy bufferStrategy,Graphics graphics) {
        this.display = display;
        this.bufferStrategy = bufferStrategy;
        this.graphics = graphics;
        initialize();

    }

    private void initialize(){
        returnInMenuButton = Assets.smallerButtons.crop(151, 338, 50, 52);
        exitButton=Assets.smallerButtons.crop(435,231,49,49);

    }

    @Override
    public void tick() {
        wall.tick();
        tauren.tick();
        tower.tick();
        crossbow.tick();
    }

    @Override
    public void render() {

        //BufferStrategy and Graphics
        this.bufferStrategy = this.display.getCanvas().getBufferStrategy();
        if(this.bufferStrategy == null){
            this.display.getCanvas().createBufferStrategy(2);
            this.bufferStrategy =this.display.getCanvas().getBufferStrategy();
        }
        this.graphics = this.bufferStrategy.getDrawGraphics();

        //Start drawing
        graphics.drawImage(Assets.gameBackground, 0, 0, 1280, 720, null);
        this.graphics.drawImage(returnInMenuButton, 1160,7, null);
        this.graphics.drawImage(exitButton,1220,10,null);

        wall.render(this.graphics);
        tauren.render(this.graphics);
        tower.render(this.graphics);
        crossbow.render(this.graphics);

        //End drawing

        graphics.dispose();
        this.bufferStrategy.show();
    }


}
