package gameObjects.Enemy;
import graphics.SpriteSheet;
import graphics.ImageLoader;

public class Tauren extends Enemy {
    public int baseHealth = 250;
    public int baseDamage = 10;
    public int baseSpeed = 10;
    public int taurenSpriteHeight = 86;
    public int taurenSpriteWidth = 100;
    public int movingAnimationFramecount = 4;
    public int attackingAnimationframecount;
    public SpriteSheet spritesheet = new SpriteSheet(ImageLoader.loadImage("Tauren_100x86.png"));


}
