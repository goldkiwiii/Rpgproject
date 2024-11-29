package prototype.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed, ePressed;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();


        if (code == KeyEvent.VK_E){
            ePressed = true;
        }
        if(code == KeyEvent.VK_UP){
            //when UP button pushed
            upPressed = true;
        }
        if(code == KeyEvent.VK_DOWN){
            //when down button pushed
            downPressed = true;
        }
        if(code == KeyEvent.VK_LEFT){
            //when left button pushed
            leftPressed = true;
        }
        if(code == KeyEvent.VK_RIGHT){
            //when right button pushed
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_E){
            ePressed = false;
        }
        if(code == KeyEvent.VK_UP){
            //when UP button not pushed
            upPressed = false;
        }
        if(code == KeyEvent.VK_DOWN){
            //when Down button not pushed
            downPressed = false;
        }
        if(code == KeyEvent.VK_LEFT){
            //when left button not pushed
            leftPressed = false;
        }
        if(code == KeyEvent.VK_RIGHT){
            //when right button not pushed
            rightPressed = false;
        }
    }
}
