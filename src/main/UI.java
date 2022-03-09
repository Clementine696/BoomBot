package main;
import java.awt.Color;
import java.awt.*;
import java.awt.image.BufferedImage;

import object.OBJ_Bomb;

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
        OBJ_Bomb key = new OBJ_Bomb(gp);
        keyImage = key.image;
    }

    public void showMessage(String text) {
        messageCounter=0;
        message = text;
        messageOn = true;
    }
    
    public void drawHp(Graphics2D g2) {
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,20));
        g2.setColor(Color.white);
        for(int i = 0; i < gp.obj.length; i++) {
            if(gp.obj[i]!=null){
                if(gp.obj[i].hp > 0){

                    int screenX = gp.obj[i].worldX - gp.player.worldX + gp.player.screenX;
                    int screenY = gp.obj[i].worldY - gp.player.worldY + gp.player.screenY;
            
                    if( gp.obj[i].worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
                        gp.obj[i].worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                        gp.obj[i].worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                        gp.obj[i].worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                            g2.drawString("hp : " + gp.obj[i].hp, screenX - 15, screenY);
                        }
                }
            }
        }
    }

    public void draw(Graphics2D g2) {

        g2.setFont(arial_40);
        g2.setColor(Color.white);
        //g2.drawImage(keyImage, gp.tileSize*4, gp.tileSize/2, gp.tileSize/2, gp.tileSize/2, null);
        g2.drawString("Life : " + gp.player.Health, 210, 40);

        if(messageOn == true) {
            g2.drawString(message, 380, 40);
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
