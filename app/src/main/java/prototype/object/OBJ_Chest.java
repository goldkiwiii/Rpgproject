package prototype.object;

import prototype.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chest extends SuperObject{
    GamePanel gp;

    public OBJ_Chest(GamePanel gp){
        this.gp = gp;
        name = "Chest";
        collision = true;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/object/chest.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
