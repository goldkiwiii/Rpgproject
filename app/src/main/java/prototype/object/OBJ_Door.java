package prototype.object;

import prototype.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends SuperObject{
    GamePanel gp;
    public OBJ_Door(GamePanel gp){
        this.gp = gp;
        name = "Door";
        collision = true;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/object/door.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
