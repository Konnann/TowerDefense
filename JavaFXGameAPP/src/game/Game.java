package game;
import display.Display;
import gameStates.GameState;
import gameStates.MenuState;
import gameStates.State;
import gameStates.StateManager;
import graphics.Assets;
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

    private State state = null;
    private State menuState;


    public Game(String name, int width, int height){
        this.name = name;
        this.width = width;
        this.height = height;
        this.display = new Display(this.name, this.width, this.height);
    }

    private void initialize(){
        Assets.init();
        //Initialize BufferStrategy Graphics

        //Initialize MenuState
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
        int fps = 30;
        //1 000 000 000 nanoseconds in a second. Thus we measure time in nanoseconds
        //to be more specific. Maximum allowed time to run the tick() and render() methods
        double timePerTick = 1_000_000_000.0 / fps;
        //How much time we have until we need to call our tick() and render() methods
        double delta = 0;
        //The current time in nanoseconds
        long now;
        //Returns the amount of time in nanoseconds that our computer runs.
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (isRunning) {
            //Sets the now variable to the current time in nanoseconds
            now = System.nanoTime();
            //Amount of time passed divided by the max amount of time allowed.
            delta += (now-lastTime) / timePerTick;
            //Adding to the timer the time passed
            timer += now - lastTime;
            //Setting the lastTime with the values of now time after we have calculated the delta
            lastTime = now;

            //If enough time has passed we need to tick() and render() to achieve 60 fps
            if (delta >= 1) {
                tick();
                render();
                //Reset the delta
                ticks++;
                delta--;
            }

            if (timer >= 1_000_000_000) {
                System.out.println("Ticks and Frames: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }

        //Calls the stop method to ensure everything has been stopped

        this.stop();
    }

    public synchronized void start(){

     if (isRunning){
         return;
     }
        this.isRunning = true;
        thread = new Thread(this);
        thread.start();


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
