package org.example;

import org.example.object.Door;
import org.example.object.Object;
import org.example.object.Sword;

public class Asseter {
    GamePanel gp;
    int[][] objectsMap;
    public Asseter(GamePanel gp){
        this.gp = gp;
    }
    public void setObject(){
         int row = 1;
         int col = 1;
         objectsMap = new int[gp.worldRow][gp.worldCol];  // contain all 0's
//        gp.Obj[0] = new Door();
//        row = 16; col = 13;  // the location of the Door on World Map is Row:16 and Col:13
//        gp.Obj[0].worldX = (col - 1) * gp.tileSize;
//        gp.Obj[0].worldY = (row - 1) * gp.tileSize;
//
//        gp.Obj[1] = new Door();
//        row = 17; col = 20;
//        gp.Obj[1].worldX = (col - 1) * gp.tileSize;
//        gp.Obj[1].worldY = (row - 1) * gp.tileSize;
//
//        gp.Obj[2] = new Door();
//        row = 34; col = 20;
//        gp.Obj[2].worldX = (col - 1) * gp.tileSize;
//        gp.Obj[2].worldY = (row - 1) * gp.tileSize;
//
//        gp.Obj[3] = new Door();
//        row = 2; col = 2;
//        gp.Obj[3].worldX = (col - 1) * gp.tileSize;
//        gp.Obj[3].worldY = (row - 1) * gp.tileSize;
//
//        gp.Obj[4] = new Sword();
//        row = 16; col = 15;
//        gp.Obj[4].worldX = (col - 1) * gp.tileSize;
//        gp.Obj[4].worldY = (row - 1) * gp.tileSize;
//
//        gp.Obj[5] = new Sword();
//        row = 20; col = 25;
//        gp.Obj[5].worldX = (col - 1) * gp.tileSize;
//        gp.Obj[5].worldY = (row - 1) * gp.tileSize;

        // Randomly assign Objects on the map
        gp.Obj[0] = new Door();
        gp.Obj[1] = new Door();
        gp.Obj[2] = new Door();
        gp.Obj[3] = new Door();
        gp.Obj[4] = new Sword();
        gp.Obj[5] = new Sword();


        for (int i=0; i < gp.Obj.length; i++){
            if(gp.Obj[i] != null){
                int tileNum;
                do {
                    row = getRandomNumber(1, 40);
                    col = getRandomNumber(1, 40);
                    tileNum = gp.tileM.MapTileNum[row][col];
                } while (gp.tileM.tile[tileNum].exist || objectsMap[row][col] != 0);  // there isn't anything existing on the object position
                        gp.Obj[i].worldX = (col ) * gp.tileSize;
                        gp.Obj[i].worldY = (row ) * gp.tileSize;
                        objectsMap[row][col] = 1;
            }
        }
    }
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
