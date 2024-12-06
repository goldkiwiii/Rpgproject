package prototype.main;

import prototype.entity.Entity;
import prototype.entity.Player;
import prototype.object.OBJ_Chest;
import prototype.object.SuperObject;
import prototype.tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    //screen Setting
    final int originalTileSize = 16; // 16 x 16 real TileSize
    final int scale = 3; // 3 sclae

    public final int tileSize = originalTileSize * scale; // 48 x 48 TileSize
    public final int maxScreenCol = 16; //width Size
    public final int maxScreenRow = 12; //Height Size
    //width x heigth 4:3 Ratio
    public final int screenWidth = tileSize * maxScreenCol;//768 px
    public final int screenHeight = tileSize * maxScreenRow;//578 px

    //world setting
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol; //
    public final int worldHeight = tileSize * maxWorldRow;


    //fps

    int FPS = 60;//frame per secconds
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    public UI ui = new UI(this);
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10];
    public Entity npc[] = new Entity[10];
    //GameState
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;


    //set player's default position
    String Mapname = "DomitoryRoom";

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);//when you use Double Buffer VG more efficient
        this.addKeyListener(keyH);
        this.setFocusable(true);//GamePanel can be "focused" to receive key input
    }

    public void setUpGame() {
        aSetter.setObject();
        aSetter.setNPC();
        gameState = titleState;
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;// 0.0166sec
        double delta = 0; // currentFrmae, lastFrame delta value
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        // start update() -> repaint() -> nextDraw(start)
        //allocated Time is 0.01666sec

        while(gameThread != null){
            currentTime = System.nanoTime();// currentTime bring nanotime

            delta += (currentTime - lastTime) / drawInterval;// f
            timer += (currentTime - lastTime);
            lastTime = currentTime;// pre time update
            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000){
                drawCount = 0;
                timer = 0;
            }
            //Why Using thread?
            //1. UPDATE: character's location information(ex, x,y) updating
            //2. DRAW: updated map's information repainting
        }
    }
    public void update() {


            if (gameState == playState) {
            //PLAYER
            player.update();
            //NPC
            for (int i =0; i< npc.length; i++){
                if (npc[i] != null){
                    npc[i].update();
                }
            }
        }
        if (gameState == pauseState){

        }
    }
    public void paintComponent(Graphics g){
        //Graphics Class have many method
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        if (gameState == titleState){
            ui.draw(g2);
        }
        else {
            tileM.draw(g2);
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null){
                    obj[i].draw(g2, this);
                }
            }
            //NPC
            for (int i = 0 ;i < obj.length; i++){
                if (npc[i] != null){
                    npc[i].draw(g2);
                }
            }
            //Player
            player.draw(g2);
            //UI
            ui.draw(g2);
        }
        //Tile




        g2.dispose();
        //for memory eco
    }

}
