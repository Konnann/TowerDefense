package game;
import MenuAndMain.Menu;

public class Game  extends Menu implements Runnable{

    //If "Play" is clicked this code is initialized;
    private Thread thread;
    private boolean isRunning;

    private void initialize(){
        //draw the game scene
    }

    private void update(){
        //put everything that updates/changes here - position/health/etc.

    }

    private void draw(){
        //draw everything after it's updated here

    }


    @Override
    public void run() {

    //First run the initialization, then the loop
        this.initialize();

    //create the constant updating of the game - a.k.a. the loop
        while(isRunning == true){
            update();
            draw();
        }

    }
    public synchronized void start(){

        //Initialized a new thread to start our game on
        thread = new Thread(this);
        thread.start();
        this.isRunning = true;
        run();
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
