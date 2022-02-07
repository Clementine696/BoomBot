package main;
import java.awt.Color;
import java.awt.*;

public class UI {
    GamePanel gp;
    Graphics2D g2;

    public UI(GamePanel gp){
        this.gp = gp;
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
    }



    public int getXforCenteredText (String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}
