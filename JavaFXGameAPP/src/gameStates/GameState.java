package gameStates;

import display.Display;
import graphics.Assets;
import gameObjects.Enemy.Tauren;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameState extends State {

    private Display display;
    private BufferStrategy bufferStrategy;
    private Graphics graphics;

    private Tauren tauren = new Tauren(0, 0, 0, 0);

    public GameState(Display display,BufferStrategy bufferStrategy,Graphics graphics) {
        this.display = display;
        this.bufferStrategy = bufferStrategy;
        this.graphics = graphics;

    }

    @Override
    public void tick() {
        tauren.tick();

    }

    @Override
    public void render() {
        Graphics g = this.graphics;
        g.drawImage(Assets.gameBackground, 0, 0, 1280, 720, null);
        tauren.render(this.graphics);
        g.dispose();
        this.bufferStrategy.show();
    }


}
