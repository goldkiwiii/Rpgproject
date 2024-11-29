package prototype.main;

import prototype.entity.NPC_OldMan;
import prototype.object.OBJ_Chest;
import prototype.object.OBJ_Door;
import prototype.object.OBJ_Key;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;

    }
    public void setObject (){
            //set Object's location
        gp.obj[0] = new OBJ_Key(gp);
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 7 * gp.tileSize;

        gp.obj[1] = new OBJ_Door(gp);
        gp.obj[1].worldX = 10 * gp.tileSize;
        gp.obj[1].worldY = 12 * gp.tileSize;

        gp.obj[2] = new OBJ_Chest(gp);
        gp.obj[2].worldX = 9 * gp.tileSize;
        gp.obj[2].worldY = 8 * gp.tileSize;
    }
    public void setNPC(){
        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize * 21;
        gp.npc[0].worldY = gp.tileSize * 21;
    }
}
