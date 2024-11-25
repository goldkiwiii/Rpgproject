package prototype;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();
        //키보드 입력 받아오는 변수

        if(code == KeyEvent.VK_UP){
            //UP 버튼이 눌렸을 때
            upPressed = true;
        }
        if(code == KeyEvent.VK_DOWN){
            //DOWN 버튼이 눌렸을 때
            downPressed = true;
        }
        if(code == KeyEvent.VK_LEFT){
            //LEFT 버튼이 눌렸을 때
            leftPressed = true;
        }
        if(code == KeyEvent.VK_RIGHT){
            //RIGHT 버튼이 눌렸을 때
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        //키보드 입력 받아오는 변수

        if(code == KeyEvent.VK_UP){
            //UP 버튼이 떼어졌을 때
            upPressed = false;
        }
        if(code == KeyEvent.VK_DOWN){
            //DOWN 버튼이 떼어졌을  때
            downPressed = false;
        }
        if(code == KeyEvent.VK_LEFT){
            //LEFT 버튼이 떼어졌을  때
            leftPressed = false;
        }
        if(code == KeyEvent.VK_RIGHT){
            //RIGHT 버튼이 떼어졌을  때
            rightPressed = false;
        }
    }
}
