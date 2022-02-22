package entity;
import javax.imageio.ImageIO;
import java.io.IOException;
import main.GamePanel;
import java.awt.*;
import java.awt.image.BufferedImage;


public class Bullet extends Entity{

    GamePanel gp;

    public Bullet(GamePanel gp){
        super(gp);
        this.gp = gp;
        setDefaultValues();
        getBulletImage();
    }

    public void setDefaultValues(){
        speed = 1;
        direction = "down";
        worldX = gp.tileSize * 3;
        worldY = gp.tileSize * 3;
    }

    public void getBulletImage(){
        try{
            up = ImageIO.read(getClass().getResourceAsStream("/res/bullet/b_w.jpg"));
            down = ImageIO.read(getClass().getResourceAsStream("/res/bullet/b_s.jpg"));
            left = ImageIO.read(getClass().getResourceAsStream("/res/bullet/b_a.jpg"));
            right = ImageIO.read(getClass().getResourceAsStream("/res/bullet/b_d.jpg"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        // g2.setColor(Color.white);
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;
        setAction();

        switch(direction){
            case "up" : image = up;
                break;
            case "down" : image = down;
                break;
            case "left" : image = left;
                break;
            case "right" : image = right;
                break;
        }

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
           worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
           worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
           worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
    }

    public void setAction(){
        worldX += speed;
    }

}
