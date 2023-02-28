package org.example.object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Door extends Object{

    public Door(){
        name = "Door";

        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/Door.png")));
        }catch (IOException e){
            e.printStackTrace();;
        }
        collision = true;
    }
}
