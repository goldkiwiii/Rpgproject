package prototype;

import prototype.entity.Player;
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
    final int screenWidth = tileSize * maxScreenCol;//768 px
    final int screenHeight = tileSize * maxScreenRow;//578 px
    //fps

    int FPS = 60;//frame per secconds
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);

    //set player's default position
    String Mapname = "DomitoryRoom";

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);//when you use Double Buffer VG more efficient
        this.addKeyListener(keyH);
        this.setFocusable(true);//GamePanel can be "focused" to receive key input
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

    // start update() -> repatin() -> nextDraw(start)
        //allocated Time is 0.01666sec

        while(gameThread != null){
            currentTime = System.nanoTime();// currentTime bring nanotime

            delta += (currentTime - lastTime) / drawInterval;// f
            timer += (currentTime - lastTime);
            lastTime = currentTime;// pre time update
            if (delta >= 1) {//
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
            //1. UPDATE: character's information(ex, x,y) updating
            //2. DRAW: updated map's information repainting
        }
    }
    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g){
        //Graphics Class have many method
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);
        player.draw(g2);

        g2.setColor(Color.green);
        g2.setFont(new Font("Arial", Font.PLAIN, 15));
        g2.drawString("FPS: " + FPS, 10, 25);
        g2.drawString("Map name: " + Mapname, 10, 10);
        g2.dispose();
        //for memory eco
    }

}
