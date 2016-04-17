package gameStates;

import display.Display;
import game.MouseMoving;
import gameObjects.PlayerAssets.Crossbow;
import gameObjects.PlayerAssets.Towers;
import gameObjects.PlayerAssets.PlayerAssets;
import gameObjects.PlayerAssets.CastleWall;
import graphics.Assets;
import graphics.SpawnEnemies;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class GameState extends State {

    private Display display;
    private BufferStrategy bufferStrategy;
    private Graphics graphics;

    private State gameState,menuState;

    private PlayerAssets wall = new CastleWall();
    private Towers tower = new Towers();
    private Crossbow crossbow = new Crossbow();
    private SpawnEnemies spawn = new SpawnEnemies();
    private BufferedImage pauseButton,returnInMenuButton,exitButton;
    private PlayerAssets arrow = new PlayerAssets();
    private PlayerAssets firstMagic = new PlayerAssets();
    private PlayerAssets secondMagic = new PlayerAssets();


    public GameState(Display display,BufferStrategy bufferStrategy,Graphics graphics) {
        this.display = display;
        this.bufferStrategy = bufferStrategy;
        this.graphics = graphics;
        this.spawn.addEnemies();

        initialize();

    }

    private void initialize(){
        display.getCanvas().addMouseMotionListener(new MouseMoving());
        pauseButton=Assets.smallerButtons.crop(15,340,49,48);
        returnInMenuButton = Assets.smallerButtons.crop(151, 338, 50, 52);
        exitButton = Assets.smallerButtons.crop(435,231,49,49);

    }

    @Override
    public void tick() {
        this.wall.tick();
        this.spawn.tick();
        this.tower.tick();
        this.crossbow.tick();
        this.arrow.tick();
        this.firstMagic.tick();
        this.secondMagic.tick();

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
        graphics.clearRect(0, 0, 1280, 720);
        graphics.drawImage(Assets.gameBackground, 0, 0, 1280, 720, null);
        this.graphics.drawImage(pauseButton, 1100,9, null);
        this.graphics.drawImage(returnInMenuButton, 1160,7, null);
        this.graphics.drawImage(exitButton,1220,10,null);

        this.wall.render(this.graphics);
        this.spawn.render(this.graphics);
        this.tower.render(this.graphics);
        this.crossbow.render(this.graphics);
        this.arrow.render(this.graphics);
        this.firstMagic.render(this.graphics);
        this.secondMagic.render(this.graphics);

        //End drawing

        this.graphics.dispose();
        this.bufferStrategy.show();
    }


}
