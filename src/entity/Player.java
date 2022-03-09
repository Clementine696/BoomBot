package entity;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity{
    
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public int Health = 100;

    public Player(GamePanel gp, KeyHandler keyH){
        super(gp);
        
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - gp.tileSize/2;
        screenY = gp.screenHeight/2 - gp.tileSize/2;

        solidArea = new Rectangle(20, 20, 12, 5);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

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
        up = setup("robot_w");
        down = setup("robot_s");
        left = setup("robot_a");
        right = setup("robot_d");
    }

    public BufferedImage setup(String imageName){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/player/" + imageName +".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
        return image;
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

            // CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

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
        if(keyH.spacePressed == true){
            gp.createBullet(worldX, worldY, direction);
        }
    }

    public void pickUpObject(int i) {
        if(i != 999) {

            String objectName = gp.obj[i].name;

            switch(objectName) {
                case "Bomb":
                    //gp.playSE(1);
                    Health-=5;
                    if(Health<=0)
                    {
                        gp.gameState = gp.gameOverState;
                        break;
                    }
                    int x = gp.obj[i].worldX / gp.tileSize;
                    int y = gp.obj[i].worldY / gp.tileSize;
                    gp.aSetter.Object_Location[x][y]= 0;
                    gp.obj[i] = null;
                    gp.ui.showMessage("-5");
                    gp.aSetter.RandomNewbomb();
                break;

                // long firingTimer;
                // int firingDelay = 600;
                case "Et":
                    if(Health<100){
                        long elapsed = (System.nanoTime() - gp.healingTimer) / 1000000;
                        if(elapsed > gp.healingDelay){
                            gp.healingTimer = System.nanoTime();
                            Health+=5;
                            gp.ui.showMessage("+5");
                            gp.obj[i].hp -= 5;
                            //System.out.println("Remaining hp of Et is " + gp.obj[i].hp);
                            if(gp.obj[i].hp == 0){
                                int X = gp.obj[i].worldX / gp.tileSize;
                                int Y = gp.obj[i].worldY / gp.tileSize;
                                gp.aSetter.Object_Location[X][Y]= 0;
                                gp.obj[i] = null;
                                gp.et_count--;
                            }
                        }
                    }
                break;
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
        g2.drawImage(image, screenX, screenY, null);
    }
}
