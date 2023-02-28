package org.example.entity;

import org.example.GamePanel;
import utility.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public int screenX;   // this is the player horizontal location on the screen window  fix number;
    public int screenY;   // this is the player vertical location on the screen window    fix number;

    int pickUp = 0;
    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - gp.tileSize/2;
        screenY = gp.screenHeight/2 - gp.tileSize/2;
        solidArea = new Rectangle(8,11,33,27);  // assign the values to solid Area
        solidAreaDefaultX = 8;
        solidAreaDefaultY = 11;

        setDefaultValues();
        getPlayerImage();

    }
    // set some default value for Character
    public void setDefaultValues(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 23;
        speed = (double)gp.worldHeight / 600;
        direction = "down";
    }

    public  void getPlayerImage(){
        try {
            // loading images
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/char/u1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/char/u2.png")));
            up3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/char/u3.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/char/d1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/char/d2.png")));
            down3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/char/d3.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/char/l1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/char/l2.png")));
            left3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/char/l3.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/char/r1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/char/r2.png")));
            right3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/char/r3.png")));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update(){
        if (keyH.upPressed || keyH.downPressed
                || keyH.leftPressed || keyH.rightPressed) {
            if(keyH.upPressed){
                direction = "up";

//                System.out.println(direction+" direction is pressed");
            }
            else if(keyH.downPressed){
                direction = "down";

//                System.out.println(direction+" direction is pressed");
            }
            else if(keyH.leftPressed){
                direction = "left";

//                System.out.println(direction+" direction is pressed");
            }
            else {
                direction = "right";

//                System.out.println(direction+" direction is pressed");
            }

            collisionOn = false;
            gp.checkCollision.checkTile(this);   // check is collision happen on the player
            int objIndex = gp.checkCollision.checkObject(this, true);  // this return the tileNum of the object If a player is reaching
            System.out.println("the objectNum is "+ objIndex + "on the object list");
            pickUpObject(objIndex);

            if (!this.collisionOn){
                switch (direction){
                    case "up" -> {
                        worldY -= speed;       // player world Y
                    }
                    case "down" ->{
                        worldY += speed;
                    }
                    case "left" -> {
                        worldX -= speed;     // player world X
                    }
                    case "right" ->{
                        worldX += speed;
                    }
                }
            }

            // making the animation of the character
            spriteCounter++;
            if (spriteCounter > 5){
                if(spriteNum == 1)
                    spriteNum = 2;
                else if (spriteNum == 2)
                    spriteNum = 3;
                else if(spriteNum == 3)
                    spriteNum = 1;
                spriteCounter = 0;
            }
        }
    }


    public void draw(Graphics2D g2) {
        //g2.setColor(Color.WHITE);
        //g2.fillRect(x,y,gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                if (spriteNum == 3){
                    image =up3;
                }
            }
            case "down" -> {
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                if (spriteNum == 3) {
                    image = down3;
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                if (spriteNum == 3) {
                    image = left3;
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                if (spriteNum == 3) {
                    image = right3;
                }
            }
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);    // gp.tileSize is the height and weight of the display image
    }

    public void pickUpObject(int i){

        if(i != 999){
            String objName = gp.Obj[i].name;
            switch (objName){
                case "Sword" ->{
                    pickUp++;
                    gp.Obj[i] = null;   // delete object on the object list, so it disappear on the map
                    System.out.println("We have "+ pickUp + " Swords");
                }
                case  "Door" ->{
                    if(pickUp > 0){
                        gp.Obj[i].collision = false;
                        pickUp--;
                        System.out.println("We used 1 Swords");
                        System.out.println("We have "+ pickUp + " Swords");
                    }
                }
            }
        }
    }


}
