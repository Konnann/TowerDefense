package game;
import display.Display;
import gameStates.MenuState;
import gameStates.State;
import gameStates.State.STATE;
import gameStates.StateManager;
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

    private State state =null;
    private State gameState,menuState;


    public Game(String name, int width, int height){
        this.name = name;
        this.width = width;
        this.height = height;
        this.display = new Display(this.name, this.width, this.height);
    }


    public  int getWidth() {
        return width;
    }

    private void initialize(){
        //Initialize BufferStrategy Graphics
        this.bufferStrategy = this.display.getCanvas().getBufferStrategy();

        if(this.bufferStrategy == null){
            this.display.getCanvas().createBufferStrategy(2);
            this.bufferStrategy =this.display.getCanvas().getBufferStrategy();
        }

        this.graphics = this.bufferStrategy.getDrawGraphics();
        //Initialize States
        menuState=new MenuState(this.display,this.bufferStrategy,this.graphics);

    }

    private void tick(){
        if(StateManager.getState()== null) {
            state = menuState;
            StateManager.setState(state);
        }
        StateManager.getState().tick();
    }

    private void render(){
            StateManager.getState().render();



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
