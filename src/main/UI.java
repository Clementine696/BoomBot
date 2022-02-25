package main;
import java.awt.Color;
import java.awt.*;
import java.awt.image.BufferedImage;

import object.OBJ_Key;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;

    public UI(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        OBJ_Key key = new OBJ_Key(gp);
        keyImage = key.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }
    
    public void draw(Graphics2D g2) {

        g2.setFont(arial_40);
        g2.setColor(Color.white);
        g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize/2, gp.tileSize/2, null);
        g2.drawString("Life : " + gp.player.hasKey, 50, 50);

        if(messageOn == true) {
            g2.drawString(message, gp.tileSize, gp.tileSize * 5);
            messageCounter++;

            if(messageCounter>120) {
                messageCounter=0;
                messageOn = false;
            }
        }
    }









    public void drawTitleScreen(Graphics2D g2){

        g2.setColor(new Color(10,1,1));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        
        g2.setPaint(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,80));
        String text = "Love of the Robot!";
        g2.drawString(text, 58, gp.screenHeight/4);
        //g2.drawString(text, getXforCenteredText(text), gp.screenHeight/2);


        g2.setFont(g2.getFont().deriveFont(Font.BOLD,40));
        String text2 = "Find your Princess";
        g2.drawString(text2, gp.screenWidth/2-185, gp.screenHeight/2 - 100);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,30));
        String text3 = "Press x to start";
        g2.drawString(text3, gp.screenWidth/2-110, gp.screenHeight/2 + 200);
    }

    public void drawGameOver(Graphics2D g2){

        g2.setColor(new Color(10,1,1));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        
        g2.setPaint(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,80));
        String text = "Game Over";
        g2.drawString(text, gp.screenWidth/2 - 210, gp.screenHeight/2);
        //g2.drawString(text, getXforCenteredText(text), gp.screenHeight/2);

        gp.gameThread = null;

    }

    public void drawFinishScreen(Graphics2D g2){

        g2.setColor(new Color(10,1,1));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        
        g2.setPaint(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,80));
        String text = "Congrats!";
        g2.drawString(text, gp.screenWidth/2 - 190, gp.screenHeight/4);
        //g2.drawString(text, getXforCenteredText(text), gp.screenHeight/2);


        g2.setFont(g2.getFont().deriveFont(Font.BOLD,40));
        String text2 = "You found your princess!";
        g2.drawString(text2, gp.screenWidth/2 - 230, gp.screenHeight/2);
        gp.gameThread = null;
    }



    public int getXforCenteredText (String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}
