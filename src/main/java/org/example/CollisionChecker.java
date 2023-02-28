package org.example;

import org.example.entity.Entity;

public class CollisionChecker {
    GamePanel gp;
    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }
    public void checkTile(Entity entity){
        double entityLeftWorldX = entity.worldX + entity.solidArea.x;  // that's the Left side horizontal/x value for the rectangle
        double entityRightWorldY = entity.worldY + entity.solidArea.y; // that's the top side vertical/y value for the rectangle
        double entityLeftWorldY = entityRightWorldY + entity.solidArea.height;  // that's the bottom side vertical/y value for the rectangle
        double entityRightWorldX = entityLeftWorldX + entity.solidArea.width; // that's the right side horizontal/x value for the rectangle


        int entityTopWorldRow = (int)((entityRightWorldY) / gp.tileSize);
        int entityBottomWorldRow = (int)((entityLeftWorldY + 10) / gp.tileSize) ;
        int entityLeftWorldCol = (int)((entityLeftWorldX) / gp.tileSize);
        int entityRightWorldCol = (int)((entityRightWorldX) / gp.tileSize);
        int tileNum1, tileNum2;

        switch (entity.direction){
            case "up" -> {
                int centityLeftWorldCol = (int)((entityLeftWorldX + entity.speed) / gp.tileSize);
                int centityRightWorldCol = (int)((entityRightWorldX - entity.speed) / gp.tileSize);
                tileNum1 = gp.tileM.MapTileNum[entityTopWorldRow][centityLeftWorldCol];  // that's means which tile subject
                tileNum2 = gp.tileM.MapTileNum[entityTopWorldRow][centityRightWorldCol];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
//                System.out.println("collision of Left point tile in front is -->"+
//                        gp.tileM.tile[tileNum1].collision +
//                        "; collision of Right point tile in front is --> " +
//                        gp.tileM.tile[tileNum2].collision);
            }
            case "down" -> {
                int centityLeftWorldCol = (int)((entityLeftWorldX + entity.speed) / gp.tileSize);
                int centityRightWorldCol = (int)((entityRightWorldX - entity.speed) / gp.tileSize);
                tileNum1 = gp.tileM.MapTileNum[entityBottomWorldRow][centityLeftWorldCol];  // that's means which tile object
                tileNum2 = gp.tileM.MapTileNum[entityBottomWorldRow][centityRightWorldCol];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
//                System.out.println("collision of Left point tile in front is -->"+
//                        gp.tileM.tile[tileNum1].collision +
//                        "; collision of Right point tile in front is --> " +
//                        gp.tileM.tile[tileNum2].collision);
            }
            case "left" -> {
                int centityBottomWorldRow = (int)((entityLeftWorldY ) / gp.tileSize);  // Modified the vertical position then player can move left or right after hit the wall
                int centityTopWorldRow = (int)((entityRightWorldY + entity.speed) / gp.tileSize);
                tileNum1 = gp.tileM.MapTileNum[centityTopWorldRow][entityLeftWorldCol];  // that's means which tile object
                tileNum2 = gp.tileM.MapTileNum[centityBottomWorldRow][entityLeftWorldCol];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
//                System.out.println("collision of top point tile in front is -->"+
//                        gp.tileM.tile[tileNum1].collision +
//                        "; collision of bottom point tile in front is --> " +
//                        gp.tileM.tile[tileNum2].collision);
            }
                case "right" -> {
                    int centityBottomWorldRow = (int)((entityLeftWorldY + entity.speed) / gp.tileSize);
                    int centityTopWorldRow = (int)((entityRightWorldY + entity.speed) / gp.tileSize);
                    tileNum1 = gp.tileM.MapTileNum[centityTopWorldRow][entityRightWorldCol];  // that's means which tile object
                    tileNum2 = gp.tileM.MapTileNum[centityBottomWorldRow][entityRightWorldCol];
                    if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                        entity.collisionOn = true;
                    }
//                    System.out.println("collision of top point tile in front is -->"+
//                            gp.tileM.tile[tileNum1].collision +
//                            "; collision of bottom point tile in front is --> " +
//                            gp.tileM.tile[tileNum2].collision);
            }
        }
    }
    public int checkObject(Entity entity, boolean player){
        int index = 999;
        for(int i = 0; i < gp.Obj.length; i++){
            if(gp.Obj[i] != null){
                // get entity solid Area position
                entity.solidArea.x = (int)entity.worldX + entity.solidArea.x;  // that's the Left side horizontal/x value for the rectangle
                entity.solidArea.y = (int)entity.worldY + entity.solidArea.y;  // that's the top side vertical/y value for the rectangle
                // get object solid Area
                gp.Obj[i].solidArea.x = (int)gp.Obj[i].worldX + gp.Obj[i].solidArea.x;
                gp.Obj[i].solidArea.y = (int)gp.Obj[i].worldY + gp.Obj[i].solidArea.y;
                switch (entity.direction) {
                    case "up" -> {
                        entity.solidArea.y -= entity.speed;
                        // the intersects method change are there overlap between entity solid Area and Object solid area
                        if (entity.solidArea.intersects(gp.Obj[i].solidArea))
                        {
                            if (gp.Obj[i].collision)
                                entity.collisionOn = true;
                            if (player) {
                                index = i;   // this means which object player is reaching
                                System.out.println("======the objectNum is "+ i + "on the object list=======");
                            }

                        }
                    }
                    case "down" -> {
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gp.Obj[i].solidArea))
                        {
                            if (gp.Obj[i].collision)
                                entity.collisionOn = true;
                            if (player) {
                                index = i;   // this means which object player is reaching
                                System.out.println("======the objectNum is "+ i + "on the object list=======");
                            }
                        }
                    }
                    case "left" -> {
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gp.Obj[i].solidArea))
                        {
                            if (gp.Obj[i].collision)
                                entity.collisionOn = true;
                            if (player) {
                                index = i;   // this means which object player is reaching
                                System.out.println("======the objectNum is "+ i + "on the object list=======");
                            }
                        }
                    }
                    case "right" -> {
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gp.Obj[i].solidArea))
                        {
                            if (gp.Obj[i].collision)
                                entity.collisionOn = true;
                            if (player) {
                                index = i;   // this means which object player is reaching
                                System.out.println("======the objectNum is "+ i + "on the object list=======");
                            }
                        }
                    }

                }
                // set the Object solid Area x and y back to default position
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.Obj[i].solidArea.x = gp.Obj[i].solidDefaultX;
                gp.Obj[i].solidArea.y = gp.Obj[i].solidDefaultY;
            }
        }
        return index;
    }
}
