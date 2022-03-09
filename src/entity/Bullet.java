package entity;
import javax.imageio.ImageIO;
import java.io.IOException;
import main.GamePanel;
import java.awt.*;
import java.awt.image.BufferedImage;


public class Bullet extends Entity{

    GamePanel gp;
    Thread bulletThread;

    public Bullet(GamePanel gp, int X, int Y, String Direction){
        super(gp);
        this.gp = gp;
        getBulletImage();
        setDefaultValues();
        worldX = X;
        worldY = Y;
        direction = Direction;
        bulletThread = new Thread();
        bulletThread.start();
    }

    public void setDefaultValues(){
        solidArea = new Rectangle(20, 20, 12, 5);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        speed = 5;
    }

    public void getBulletImage(){
        try{
            up = ImageIO.read(getClass().getResourceAsStream("/res/bullet/b_w.png"));
            down = ImageIO.read(getClass().getResourceAsStream("/res/bullet/b_s.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/res/bullet/b_a.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/res/bullet/b_d.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public boolean update(){
        collisionOn = false;
        gp.cChecker.checkTile(this);
        int objIndex = gp.cChecker.checkObject(this, true);
            hitObject(objIndex);
        if(collisionOn)
            return false;
        else
            return true;
    }

    public void hitObject(int i) {
        if(i != 999) {

            String objectName = gp.obj[i].name;

            switch(objectName) {
                case "Bomb":
                    collisionOn = true;
                    int x = gp.obj[i].worldX / gp.tileSize;
                    int y = gp.obj[i].worldY / gp.tileSize;
                    gp.aSetter.Object_Location[x][y]= 0;
                    gp.obj[i] = null;
                    gp.aSetter.RandomNewbomb();
                break;
                case "Et":
                    collisionOn = true;
                break;
            }
        }
    }

    public void draw(Graphics2D g2){
        // g2.setColor(Color.white);
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch(direction){
            case "up" : image = up; worldY-=speed;
                break;
            case "down" : image = down; worldY+=speed;
                break;
            case "left" : image = left; worldX-=speed;
                break;
            case "right" : image = right; worldX+=speed;
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

}
