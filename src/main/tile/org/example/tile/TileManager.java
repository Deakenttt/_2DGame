package org.example.tile;

import org.example.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;  // this creates a list of element type: Tile,  the name of the List is call tile
    public int[][] MapTileNum;  // integer matrix, 2D map

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];   // defined tile, 10 Tile element in the List
        MapTileNum = new int[gp.worldRow][gp.worldCol];
        getTileImage();
        loadMap("/map/map.txt");
    }

    public void getTileImage() {
        try {
//            // load the images  ( tile such as Wall, Lava, Hole, and other object)
//            tile[0] = new Tile();
//            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/hole.png")));
//            tile[1] = new Tile();
//            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/lava.png")));
//
//            tile[2] = new Tile();
//            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png")));
//            tile[2].collision = true;
//
//            tile[3] = new Tile();
//            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/ground.png")));

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/light.png")));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/plant.png")));
            tile[1].exist = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall3.png")));
            tile[2].collision = true;
            tile[2].exist = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/floor.png")));



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // make a map with simply object number
    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            System.out.println(br.readLine());
            for (int row = 0; row < gp.worldRow; row++) {
                System.out.println("Reach here------------>" + row);
                String line = br.readLine();  // read a single line
                String[] numbers = line.split(" ");      // assign String type element in a List called numbers
                for (int col = 0; col < gp.worldCol; col++) {
                    int objectNum = Integer.parseInt(numbers[col]);
                    MapTileNum[row][col] = objectNum;
                }
            }
            br.close();
        } catch (Exception e) {
        }
        System.out.println("=======the number Map is: ");
        for (int i = 0; i < gp.worldRow; i++) {
            for (int j = 0; j < gp.worldCol; j++) {
                System.out.print(MapTileNum[i][j]);
            }
            System.out.println();
        }
    }

    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;

        for (row = 0; row < gp.worldRow; row++) {
            for (col = 0; col < gp.worldCol; col++) {
                int num = MapTileNum[row][col];

                int worldX = col * gp.tileSize;
                int worldY = row * gp.tileSize;
                double screenX = worldX - gp.player.worldX + gp.player.screenX;
                double screenY = worldY - gp.player.worldY + gp.player.screenY;

                // we draw the World map as the character move, we don't draw the while map first
                if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
                        && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                    g2.drawImage(tile[num].image, (int)screenX, (int)screenY, gp.tileSize, gp.tileSize, null);
                }
            }

        }
    }
}