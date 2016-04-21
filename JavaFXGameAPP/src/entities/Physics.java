package entities;

import java.awt.*;

public class Physics {
    public static boolean enemyCollision(EnemyEntity enemyEntity, BuildingEntity buildingEntity) {//EnemyEntity enemyEntity, LinkedList<BuildingEntity> buildingEntity) {
            if (enemyEntity.getBounds().intersects(buildingEntity.getBounds())) {
                return true;
            }
        return false;
    }

    public static boolean collidesWith(Rectangle boundingBox1, Rectangle boundingBox2) {
        if (boundingBox1.intersects(boundingBox2)) {
            return true;
        }
        return false;
    }
}

