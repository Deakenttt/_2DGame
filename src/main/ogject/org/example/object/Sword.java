package org.example.object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Sword extends Object{

    public Sword(){
        name = "Sword";

        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/sword.png")));
        }catch (IOException e){
            e.printStackTrace();;
        }
        collision = false;
    }
}
