package schoolTour.game2D.entity;


import schoolTour.game2D.main.GamePanel;
import schoolTour.game2D.main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        screenX = gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2);
        screenY = gamePanel.screenHeight  / 2 - (gamePanel.tileSize / 2);

        solidArea = new Rectangle(8, 16, 32, 32);
//        solidArea.x = 8;
//        solidArea.y = 16;
//        solidArea.width = 32;
//        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {

        // from Entity parent class ; location of character/player
        worldX = gamePanel.tileSize * 23;
        worldY = gamePanel.tileSize * 21;
        speed = 5;
        direction = "down";
    }

    public void getPlayerImage() {

        try{
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/boy_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/boy_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/boy_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/boy_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/boy_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/boy_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/boy_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/boy_right_2.png")));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if(keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed) {
            if(keyHandler.upPressed) {
                direction = "up";
                worldY -= speed;
            }
            else if(keyHandler.downPressed) {
                direction = "down";
                worldY += speed;
            }
            else if(keyHandler.leftPressed) {
                direction = "left";
                worldX -= speed;
            }
            else {
                direction = "right";
                worldX += speed;
            }



            spriteCounter++;
            if(spriteCounter >= 10 ) {   // this part can be changed into many different ways
                if(spriteNum == 1) {
                    spriteNum = 2;
                }
                else if(spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2d) {
//        g2d.setColor(Color.WHITE);
//        g2d.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if(spriteNum == 1) image = up1;
                if(spriteNum == 2) image = up2;
                break;
            case "down":
                if(spriteNum == 1) image = down1;
                if(spriteNum == 2) image = down2;
                break;
            case "left":
                if(spriteNum == 1) image = left1;
                if(spriteNum == 2) image = left2;
                break;
            case "right":
                if(spriteNum == 1) image = right1;
                if(spriteNum == 2) image = right2;
                break;
        }
        g2d.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
