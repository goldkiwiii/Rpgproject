package schoolTour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel {
    private MainFrame parent;
    private String mapName;
    private int mapX = 0, mapY = 0;
    private int playerDirectionX = 0, playerDirectionY = 0;

    public GamePanel(MainFrame parent, String mapName) {
        this.parent = parent;
        this.mapName = mapName;

        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPressed(e);
            }
        });
    }

    private void handleKeyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP -> {
                mapY += 50;                     // num can be changed
                playerDirectionY = 1;
            }
            case KeyEvent.VK_DOWN -> {
                mapY -= 50;
                playerDirectionY = 2;
            }
            case KeyEvent.VK_LEFT -> {
                mapX += 50;
                playerDirectionX = 1;
            }
            case KeyEvent.VK_RIGHT -> {
                mapX -= 50;
                playerDirectionX = 2;
            }
        }

        if(mapX < (-1 * parent.getWidth()/2)) {                   // can change types of map based on the location
            String nextMap = MapManager.getNextMap(mapName);
            parent.switchToMap(nextMap);
            mapX = 0;
            mapY = 0;
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 2-9. 맵 배경 그리기
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight()); // 전체 화면 배경

        g.setColor(Color.DARK_GRAY);
        g.fillRect(mapX, mapY, parent.getWidth(), parent.getHeight()); // 맵 크기 및 위치

        // 2-10. 캐릭터 그리기 (중앙 고정)
        g.setColor(Color.RED);
        g.fillOval(parent.getWidth() / 2 - 25, parent.getHeight() / 2 - 25, 50, 50);
    }
}
