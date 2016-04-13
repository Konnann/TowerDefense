package game;
import display.Display;
import gameStates.State.STATE;
import graphics.ImageLoader;

import java.awt.*;
import java.awt.image.BufferStrategy;


public class Game implements Runnable{
    private String name;
    private int width, height;

    private Thread thread;
    private boolean isRunning;

    private Display display;
    private BufferStrategy bufferStrategy;
    private Graphics graphics;

    //variable to change states with

    private STATE state = STATE.GAME;


    public Game(String name, int width, int height){
        this.name = name;
        this.width = width;
        this.height = height;

    }




    private void initialize(){
        //draw the game scene
        this.display = new Display(this.name, this.width, this.height);

    }

    private void tick(){
        if (state == STATE.GAME){
            //Gameplay - put everything that updates/changes ingame here - position/health/etc.

        }else if(state == STATE.MENU){
            //write menu code here
        }

    }

    private void render(){
        this.bufferStrategy = this.display.getCanvas().getBufferStrategy();

        if(this.bufferStrategy == null){
            this.display.getCanvas().createBufferStrategy(2);
            this.bufferStrategy =this.display.getCanvas().getBufferStrategy();
        }

        this.graphics = this.bufferStrategy.getDrawGraphics();

        if(state == STATE.GAME) {
            //Gameplay - draw everything after it's updated here
            this.graphics.drawImage(ImageLoader.loadImage("/gameBackground.png"), 0, 0, 1280, 720, null);
        }else if(state == STATE.MENU){
            //write menu code here
        }

        this.graphics.dispose();
        this.bufferStrategy.show();
    }


    @Override
    public void run() {

    //First run the initialization, then the loop
        this.initialize();

    //create the constant updating of the game - a.k.a. the loop
        while(isRunning == true){
            tick();
            render();
        }
        this.stop();

    }
    public synchronized void start(){

        //Initialized a new thread to start our game on
        thread = new Thread(this);
        thread.start();
        this.isRunning = true;
        this.run();
    }
    public synchronized void stop(){
        //Joins our thread with the original one, or in other words, stops it
        try {
            thread.join();
            this.isRunning = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
