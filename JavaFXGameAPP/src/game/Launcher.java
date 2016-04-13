package game;

public class Launcher {
    public static void main(String[] args) {
        //ONLY Launch game
        Game game = new Game("Tower Defense", 1280, 720);
        game.start();
    }
}
