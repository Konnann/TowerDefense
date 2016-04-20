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
    private int numberOfRows;
    public boolean allWavesDefeated = false;



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

    public void addEnemies(int waveIndex) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(spawnEnemies));

            int y = 200;
            int x = 1280;

            String line = reader.readLine();

            while (line != null) {
                if (line.equals("Wave " + waveIndex)) {
                    break;
                } else if (line.equals("All waves defeated")) {
                    allWavesDefeated = true;
                    break;
                }
                line = reader.readLine();
            }

            //String[] wave = reader.readLine().split(" ");
            //int currentWaveIndex = Integer.parseInt(wave[1]);
            if (allWavesDefeated == false) {
                line = reader.readLine();
            char[] c = line.toCharArray();

            this.numberOfColumns = c.length;

            enemies = new Enemy[5][c.length];
                numberOfRows = 0;

                while (line != null) {

                //understands text file and puts enemy objects when it comes across their index letter
                //T = tauren
                for (int row = 0; row < 5; row++) {
                    numberOfRows++;
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
                        if (line.equals("Wave End")) {
                            break;
                }
            }
                    if (line.equals("Wave End")) {
                        break;
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
    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }
   public boolean allDead(){
       for (int row = 0; row < numberOfRows; row++) {
           for (int col = 0; col < numberOfColumns; col++) {
               if(enemies[row][col] != null){
                   return false;
               }
           }
       }
           return true;
    }
}

