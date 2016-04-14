package gameObjects.Enemy;

import gameObjects.GameObject;

import java.awt.*;

public abstract class Enemy extends GameObject {
    //I'll put methods here that will be appliable to the enemies, like when they're slowed by attacks or health bars;
    public abstract void tick();
    public abstract void render(Graphics g);

    public void takeDamage(int damage, int healthPoints) {

        healthPoints = healthPoints - damage;
    }
}