package prototype;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    //screen Setting
    final int originalTileSize = 16; // 16 x 16 real TileSize
    final int scale = 3; // 3 sclae

    final int tileSize = originalTileSize * scale; // 48 x 48 TileSize
    final int maxScreenCol = 16; //width Size
    final int maxScreenRow = 12; //Height Size
    //width x heigth 4:3 Ratio
    final int screenWidth = tileSize * maxScreenCol;//768 px
    final int screenHeight = tileSize * maxScreenRow;//578 px
    //fps

    int FPS = 60;//frame per secconds
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    //set player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;
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

    // start update() -> repatin() -> nextDraw(start)
        //allocated Time is 0.01666sec

        while(gameThread != null){
            currentTime = System.nanoTime();// currentTime bring nanotime
            delta += (currentTime - lastTime) / drawInterval;// f
            lastTime = currentTime;// pre time update
            if (delta >= 1) {//
                update();
                repaint();
                delta--;

            }
            //Why Using thread?
            //1. UPDATE: character's information(ex, x,y) updating
            //2. DRAW: updated map's information repainting
        }
    }
    public void update(){
        //location information update
        if (keyH.upPressed == true){
            playerY -= playerSpeed;
        } else if (keyH.downPressed == true) {
            playerY += playerSpeed;
        }
        else if(keyH.leftPressed == true){
            playerX -= playerSpeed;
        } else if (keyH.rightPressed == true) {
            playerX += playerSpeed;
        }

    }
    public void paintComponent(Graphics g){
        //Graphics Class have many method
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize);

        g2.dispose();
        //for memory eco
    }

}
