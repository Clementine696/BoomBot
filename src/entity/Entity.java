package entity;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class Entity {
    public int worldX, worldY;
    public int speed;

    public BufferedImage up, down, left, right;
    public String direction;

    public Rectangle solidArea;
    public boolean collisionOn = false;
    
}
