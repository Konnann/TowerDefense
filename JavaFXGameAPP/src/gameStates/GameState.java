package gameStates;

import display.Display;
import gameObjects.castle.CastleWall;
import gameObjects.enemy.Tauren;
import graphics.Assets;
import graphics.SpawnEnemies;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.File;

public class GameState extends State {

    private Display display;
    private BufferStrategy bufferStrategy;
    private Graphics graphics;
    private CastleWall wall = new CastleWall();
    private SpawnEnemies spawn = new SpawnEnemies();

    public GameState(Display display,BufferStrategy bufferStrategy,Graphics graphics) {
        this.display = display;
        this.bufferStrategy = bufferStrategy;
        this.graphics = graphics;
        this.spawn.addEnemies();
    }

    @Override
    public void tick() {
        this.wall.tick();
        this.spawn.tick();


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

        //START DRAWING
        this.graphics.drawImage(Assets.gameBackground,0, 0, null);
        this.wall.render(this.graphics);
        this.spawn.render(this.graphics);
        //END DRAWING

        this.graphics.dispose();
        this.bufferStrategy.show();
    }


}
