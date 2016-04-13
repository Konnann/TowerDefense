package gameObjects.Enemy;

import gameObjects.GameObject;

public abstract class Enemy extends GameObject {
    //I'll put methods here that will be appliable to the enemies, like when they're slowed by attacks or health bars;


    public void takeDamage(int damage, int healthPoints) {

        healthPoints = healthPoints - damage;
    }
}