package entity;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
    
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - gp.tileSize/2;
        screenY = gp.screenHeight/2 - gp.tileSize/2;

        solidArea = new Rectangle(17, 13, 1, 1);
        //solidArea = new Rectangle(gp.tileSize/18*gp.scale, gp.tileSize/9*gp.scale, gp.tileSize*gp.scale*2/9, (gp.tileSize/3*2)/3*gp.scale); //8 16 32 32 
        setDefaultValues(); 
        getPlayerImage();
    }
    public void setDefaultValues(){
        worldX = gp.tileSize * 1;
        worldY = gp.tileSize * 1;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){
        try{
            up = ImageIO.read(getClass().getResourceAsStream("/res/player/robot_w.png"));
            down = ImageIO.read(getClass().getResourceAsStream("/res/player/robot_s.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/res/player/robot_a.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/res/player/robot_d.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true)
        {
            if(keyH.upPressed == true){
                direction = "up";
            }
            else if(keyH.downPressed == true){
                direction = "down";
            }
            else if(keyH.leftPressed == true){
                direction = "left";
            }
            else if(keyH.rightPressed == true){
                direction = "right";
            }
    
            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);
    
            // IF COLLISIO IS FALSE, PLAYER CAN MOVE
            if(collisionOn == false){
                switch(direction){
                    case "up" : worldY -= speed;
                        break;
                    case "down" : worldY += speed;
                        break;
                    case "left" : worldX -= speed;
                        break;
                    case "right" : worldX += speed;
                        break;
                }
            }


        }
        
    }

    public void draw(Graphics2D g2){
        // g2.setColor(Color.white);
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

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
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
