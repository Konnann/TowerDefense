package entities;

import java.util.LinkedList;

public class Physics {
    public static boolean collision(EnemyEntity enemyEntity, BuildingEntity buildingEntity) {//EnemyEntity enemyEntity, LinkedList<BuildingEntity> buildingEntity) {

       // for (int i = 0; i < buildingEntity.size(); i++) {
            if (enemyEntity.getBounds().intersects(buildingEntity.getBounds())) {
                return true;
            }
       // }
        return false;
    }
}

