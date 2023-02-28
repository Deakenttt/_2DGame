package org.example.object;

import org.example.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Object {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public double worldX, worldY;

    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);  // the solid Area of the object
    public int solidDefaultX = 0;
    public int solidDefaultY = 0;


    public Object(){

    }

    public void draw(Graphics2D g2, GamePanel gp){
        double screenX = worldX - gp.player.worldX + gp.player.screenX;
        double screenY = worldY - gp.player.worldY + gp.player.screenY;


        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
                && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            g2.drawImage(image, (int)screenX, (int)screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}
