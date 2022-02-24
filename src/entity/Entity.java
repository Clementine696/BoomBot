package entity;
import java.awt.image.BufferedImage;
import main.GamePanel;
import java.awt.Rectangle;

public class Entity {

    GamePanel gp;

    public int worldX, worldY;
    public int speed;

    public BufferedImage up, down, left, right;
    public String direction;

    public Rectangle solidArea;

    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    
    public Entity(GamePanel gp){
        this.gp = gp;
    }
}
