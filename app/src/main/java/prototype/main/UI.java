package prototype.main;

import com.sun.tools.jconsole.JConsolePlugin;
import prototype.object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40;
    BufferedImage keyImage;
    Font maruMonica;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public String currentDialogue = "";
    public int commandNum = 0;

    public UI(GamePanel gp){
        this.gp =  gp;
        InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
        try {
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        arial_40 = new Font("Cambria", Font.PLAIN, 40);
        OBJ_Key key = new OBJ_Key(gp);
        keyImage = key.image;
    }
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(maruMonica);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);
        if (gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        //Play State
        if (gp.gameState == gp.playState){
        //Pause State
        }
        if (gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
        if (gp.gameState == gp.dialogueState){
            drawDialogueScreen();

        }
    }
    public void drawTitleScreen(){
        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96f));
        String text = "HGU Adventure";
        int x = getXforCenteredText(text);
        int y = gp.tileSize * 3;
        //Shadow
        g2.setColor(Color.gray);
        g2.drawString(text, x+5, y+5);
        //Draw String title
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);

        //Draw Character
        x = gp.screenWidth/2-gp.tileSize;
        y += gp.tileSize/2;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2 , null);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48f));
        //Draw Menu
        text = "NEW GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize * 3.5;
        g2.drawString(text, x, y);
        if (commandNum == 0){
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "LOAD GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 1){
            g2.drawString(">", x-gp.tileSize, y);
        }
        text = "QUIT";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 2){
            g2.drawString(">", x-gp.tileSize, y);
        }
    }

    private void drawDialogueScreen() {
        //WINDOW
         int x  = gp.tileSize * 2;
         int y = gp.tileSize / 2;
         int width = gp.screenWidth - gp.tileSize * 4;
         int height = gp.tileSize * 4;
         drawSubWindow(x, y, width, height);
    }
    public void drawSubWindow(int x, int y, int width, int height){
        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        x += gp.tileSize;
        y += gp.tileSize;
        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x ,y);
            y+=40;
        }
    }

    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;


        g2.drawString(text, x, y);

    }
    public int getXforCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}
