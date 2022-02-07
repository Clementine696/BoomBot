package main;
import java.awt.event.*;
public class KeyHandler implements KeyListener{

      GamePanel gp;
      public boolean upPressed, downPressed, leftPressed, rightPressed;

      public KeyHandler(GamePanel gp){
            this.gp = gp;
      }

      @Override
      public void keyTyped(KeyEvent e) {
        
      }
      @Override
      public void keyPressed(KeyEvent e) {

            if(gp.gameState==gp.titleState){
                  if(e.getKeyCode()==KeyEvent.VK_X){
                        gp.gameState=gp.playState;
                  }
            }

            // if(gp.gameState==gp.gameOverState){
            //       if(e.getKeyCode()==KeyEvent.VK_W){
            //             gp.gameState=gp.titleState;
            //       }
            // }

            if(gp.gameState==gp.playState){
                  switch(e.getKeyCode()){
                        case KeyEvent.VK_W : upPressed = true;
                              break;
                        case KeyEvent.VK_S : downPressed = true;
                              break;
                        case KeyEvent.VK_A : leftPressed = true;
                              break;
                        case KeyEvent.VK_D : rightPressed = true;
                              break;
                  } 
            }
      } 

      @Override
      public void keyReleased(KeyEvent e) {
            if(gp.gameState==gp.playState){
                  switch(e.getKeyCode()){
                        case KeyEvent.VK_W : upPressed = false;
                              break;
                        case KeyEvent.VK_S : downPressed = false;
                              break;
                        case KeyEvent.VK_A : leftPressed = false;
                              break;
                        case KeyEvent.VK_D : rightPressed = false;
                              break;
                  }
            }
      } 
}
