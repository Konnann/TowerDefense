package gameStates;

import display.Display;
import entities.BuildingEntity;
import game.MouseMoving;
import gameObjects.PlayerAssets.*;
import graphics.Assets;
import graphics.SpawnEnemies;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class GameState extends State {

    private Display display;
    private BufferStrategy bufferStrategy;
    private Graphics graphics;

    private State gameState,menuState;

    private CastleWall wall = new CastleWall();
    private Tower towerOne = new Tower(83,105);
    private Tower towerTwo = new Tower(83,500);
    private Crossbow crossbow = new Crossbow();
    private Arrow arrow = new Arrow();
    private SpawnEnemies spawn = new SpawnEnemies();
    private BufferedImage pauseButton,returnInMenuButton,exitButton;
    private PlayerAssets firstMagic = new PlayerAssets();
    private PlayerAssets secondMagic = new PlayerAssets();

    public LinkedList<BuildingEntity> buildingEntities = new LinkedList<>();


    public GameState(Display display,BufferStrategy bufferStrategy,Graphics graphics) {
        this.display = display;
        this.bufferStrategy = bufferStrategy;
        this.graphics = graphics;
        this.spawn.addEnemies();

        initialize();

    }

    private void initialize(){
        display.getCanvas().addMouseMotionListener(new MouseMoving());
        pauseButton = Assets.smallerButtons.crop(15,340,49,48);
        returnInMenuButton = Assets.smallerButtons.crop(151, 338, 50, 52);
        exitButton = Assets.smallerButtons.crop(435,231,49,49);
        buildingEntities.add(wall);
        buildingEntities.add(towerOne);
        buildingEntities.add(towerTwo);
        spawn.addEnemies();


    }

    @Override
    public void tick() {

        this.spawn.tick(buildingEntities);
        this.firstMagic.tick();
        this.secondMagic.tick();
        for (int i = 0; i < buildingEntities.size() ; i++) {
            buildingEntities.get(i).tick();
        }
        this.crossbow.tick();
        this.arrow.tick();

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

        this.spawn.render(this.graphics);
        this.firstMagic.render(this.graphics);
        this.secondMagic.render(this.graphics);
        for (int i = 0; i < buildingEntities.size() ; i++) {
            buildingEntities.get(i).render(this.graphics);
        }
        this.crossbow.render(this.graphics);
        this.arrow.render(this.graphics);

        //End drawing

        this.graphics.dispose();
        this.bufferStrategy.show();
    }

    public LinkedList<BuildingEntity> getBuildingEntities() {
        return buildingEntities;
    }

}
