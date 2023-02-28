package org.example.entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public double worldX, worldY;   // Player horizontal and vertical position on the world Map;
    public double speed;

    public int score;

    // BufferedImage: it describes an image with an accessible buffer of image data
    public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea;  // for the player
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
}
