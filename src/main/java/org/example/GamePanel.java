package org.example;

import javax.swing.*;
import java.awt.*;
import org.example.entity.Player;
import org.example.tile.TileManager;
import utility.KeyHandler;
import org.example.object.Object;

public class GamePanel extends JPanel implements Runnable{
    //Screen setting
    final int originalTileSize = 16;  // 16*16 tile
    final int scale = 3;
    public int tileSize = originalTileSize * scale;  // 48*48 tile
    public int maxScreenCol = 17; // tile can move Max16 steps horizontal
    public int maxScreenRow = 15; // tile can move Max12 steps vertical
    public int screenWidth = tileSize * maxScreenCol;
    public int screenHeight = tileSize * maxScreenRow;

    //world Map setting
    public final int worldRow = 50;
    public final int worldCol = 50;
    public final int worldHeight = tileSize * worldCol;
    public final int worldWidth = tileSize * worldRow;

    //FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);  // this means itself
    public KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;  // when this executed it automatically call the run() method AND define the gameThread
    public Player player = new Player(this, keyH);
    public CollisionChecker checkCollision = new CollisionChecker(this);
    public Asseter aSetter = new Asseter(this);
    public Object[] Obj = new Object[30];    // A list store all the objects


    public  GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void zoomInOut(int i){
        int oldWorldWith = tileSize * worldCol;
        tileSize += i;
        int newWorldWith = tileSize * worldCol;
        player.speed = (double)newWorldWith / 600;  // new world width increase so the speed decrease
        double ratio = (double) newWorldWith / oldWorldWith;
        System.out.println("tile Size = "+ tileSize);
        System.out.println("new World With = "+ newWorldWith);
        System.out.println("Player world X = "+ player.worldX);

        double newWorldX = player.worldX * ratio;
        double newWorldY = player.worldY * ratio;

        player.worldX = newWorldX;
        player.worldY = newWorldY;

        for(i = 0; i < Obj.length; i++){
            if(Obj[i] != null){
                Obj[i].worldX = Obj[i].worldX * ratio;
                Obj[i].worldY = Obj[i].worldY * ratio;
            }
        }
    }

    public void setUpGame(){
        aSetter.setObject();
    }

    public  void startGameThread(){
        gameThread = new Thread(this);  // pass the current class to the Thread
        gameThread.start();
    }

    @Override
    public void run() {   // create a game loop:  Using Sleep method

        //the unit of drawInterval is nanosecond, because 1000000000 nanosecond = 1 second
        // this means: PC draw the screen/window every drawInterval nanosecond
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;  // the current time(in nanosecond) of drawing the next window


        while (gameThread != null) {

            // 1. Update: update information such as character position
            update();
            // 2. Draw: draw the screen with update information
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0) {    // If the remaining is less than 0, Don't sleep!
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);  // sleep method execute with specific unit: million second
                nextDrawTime += drawInterval;  // in drawInterval nanosecond later will be the next draw time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
//
//    @Override
//    public void run() {   // create a game loop, Using delta method
//
//        double drawInterval = 1000000000/FPS;
//        double delta = 0;
//        long lastTime = System.nanoTime();
//        long currentTime;
//        long timer = 0;
//        int drawCount = 0;
//
//        while (gameThread != null){
//            currentTime = System.nanoTime();
//            delta += (currentTime - lastTime) / drawInterval;
//            timer += (currentTime - lastTime);
//            lastTime = currentTime;   // update the last time to current time, in order to get the NEW current time
//
//            if (delta >= 1){
//                // 1. Update: update information such as character position
//                update();
//                // 2. Draw: draw the screen with update information
//                repaint();
//
//                delta--;
//                drawCount++;
//            }
//            if(timer >= 1000000000){
//                System.out.println("FPS: "+ drawCount);
//                drawCount = 0;
//                timer = 0;
//            }
//        }
//
//    }

    // in java the position of the upper left corner of the display window is x:0, y:0
    public void update(){

        player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        // we draw tile first and then player, otherwise the tile will hide the player
        tileM.draw(g2);

        for(int i = 0; i < Obj.length; i++){
            if(Obj[i] != null){
                Obj[i].draw(g2, this);
            }
        }
        player.draw(g2);
        g2.dispose();
    }
}




