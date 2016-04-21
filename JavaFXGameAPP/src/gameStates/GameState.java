package gameStates;

import display.Display;
import entities.BuildingEntity;
import game.MouseMoving;
import gameObjects.Enemy.Enemy;
import gameObjects.Enemy.Tauren;
import gameObjects.PlayerAssets.*;
import gameObjects.Projectile.Arrow;
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

    private SpawnEnemies spawn;
    private int waveIndex;
    private CastleWall wall;
    private Tower towerOne,towerTwo;
    private Crossbow crossbow;
    private BufferedImage pauseButton,returnInMenuButton,exitButton;

    public LinkedList<BuildingEntity> buildingEntities;

    public GameState(Display display,BufferStrategy bufferStrategy,Graphics graphics) {
        this.display = display;
        this.bufferStrategy = bufferStrategy;
        this.graphics = graphics;

        initialize();

    }

    private void initialize(){
        display.getCanvas().addMouseMotionListener(new MouseMoving());
        pauseButton = Assets.smallerButtons.crop(15,340,49,48);
        returnInMenuButton = Assets.smallerButtons.crop(151, 338, 50, 52);
        exitButton = Assets.smallerButtons.crop(435,231,49,49);

        waveIndex = 1;
        spawn = new SpawnEnemies();
        spawn.addEnemies(waveIndex);

        buildingEntities = new LinkedList<>();
        wall = new CastleWall();
        towerOne = new Tower(83, 105);
        towerTwo = new Tower(83, 500);

        buildingEntities.add(wall);
        buildingEntities.add(towerOne);
        buildingEntities.add(towerTwo);
        crossbow = new Crossbow();

    }

    @Override
    public void tick() {
        if(spawn.allDead() == true){
            waveIndex++;
            spawn.addEnemies(waveIndex);
        }
        if(spawn.allWavesDefeated == false) {
        this.spawn.tick(buildingEntities);
        }

        for (int i = 0; i < buildingEntities.size() ; i++) {
            buildingEntities.get(i).tick(spawn.getEnemies());
        }
        this.crossbow.tick(spawn.getEnemies());

        for (int i = 0; i < buildingEntities.size(); i++) {
            if(buildingEntities.get(i).getHealth() <= 0){
                if(buildingEntities.get(i) instanceof CastleWall){
                    crossbow.isActive = false;
                }
                buildingEntities.remove(i);
            }
        }
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
        this.graphics.drawImage(pauseButton, 1100, 11, null);
        this.graphics.drawImage(returnInMenuButton, 1160, 9, null);
        this.graphics.drawImage(exitButton, 1220, 12, null);

        if(spawn.allWavesDefeated == false) {
        this.spawn.render(this.graphics);
        }else if(spawn.allWavesDefeated == true) {
            graphics.drawImage(Assets.youWin, 325, 77, 650, 154, null);
        }

        for (int i = 0; i < buildingEntities.size() ; i++) {
            buildingEntities.get(i).render(this.graphics);
        }
        this.crossbow.render(this.graphics);

        //End drawing

        this.graphics.dispose();
        this.bufferStrategy.show();
    }

    public LinkedList<BuildingEntity> getBuildingEntities() {
        return buildingEntities;
    }

    private Enemy[][] getEnemies(){
        return spawn.getEnemies();
    }
}
