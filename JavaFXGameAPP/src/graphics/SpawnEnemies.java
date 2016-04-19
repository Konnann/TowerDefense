package graphics;


import entities.BuildingEntity;
import entities.Physics;
import gameObjects.Enemy.Enemy;
import gameObjects.Enemy.Tauren;

import java.awt.*;
import java.io.*;
import java.util.LinkedList;

// Reads a text file with the enemy spawn sequence, the rows must always be 5
//Will fix the crappy animation
public class SpawnEnemies {

    private File spawnEnemies = Assets.spawnEnemies;

    private Enemy[][] enemies;
    private int numberOfColumns;


    public SpawnEnemies() {

    }

    public void tick(LinkedList<BuildingEntity> buildingEntities){
        //Initialize the tick method for all the enemies in the array
        for (int col = 0; col < numberOfColumns ; col++) {
            for (int row = 0; row < 5; row++) {
                if (enemies[row][col] != null) {
                    enemies[row][col].tick(buildingEntities);
                }
            }

        }


    }

    public void render(Graphics g){

        //Initialize the render method for all the enemies in the array
        for (int col = 0; col < numberOfColumns; col++) {
            for (int row = 0; row < 5; row++) {
                if (enemies[row][col] != null) {
                    enemies[row][col].render(g);
                }
            }
        }
    }

    public void addEnemies() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(spawnEnemies));

            String[] wave = reader.readLine().split(" ");
            int waveIndex = Integer.parseInt(wave[1]);

            String line = reader.readLine();

            char[] c = line.toCharArray();
            int y = 200;
            int x = 1280;
            this.numberOfColumns = c.length;

            enemies = new Enemy[5][c.length];

            while(line != null){

                //understands text file and puts enemy objects when it comes across their index letter
                //T = tauren
                for (int row = 0; row < 5; row++) {
                    for (int col = 0; col < c.length; col++) {
                        if (c[col] == 'T') {
                            enemies[row][col] = new Tauren(x, y);
                        }
                        x += 300;        //set horizontal distance between sprites
                    }
                    y += 100;             //set vertical distance between sprites
                    x = 1280;
                    line = reader.readLine();
                    if (line != null) {
                        c = line.toCharArray();
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Enemy[][] getEnemies(){
        return enemies;
    }
}

