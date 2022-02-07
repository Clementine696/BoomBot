package main;
import java.awt.Color;
import java.awt.*;

public class Minimap{

    GamePanel gp;

    public Minimap(GamePanel gp){
        this.gp = gp;
    }

    public void draw(Graphics2D g2){
        
        //g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        g2.setPaint(Color.blue);
        g2.fillRect(0, 0, gp.maxWorldCol*2, gp.maxWorldRow*2);

        int x = gp.player.worldX/gp.tileSize*2;
        int y = gp.player.worldY/gp.tileSize*2;

        g2.setPaint(Color.RED);
        g2.fillRect(x, y, 4, 4);



    }

}
