package object;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;

public class OBJ_Bomb extends SuperObject{

    GamePanel gp;

    public OBJ_Bomb(GamePanel gp) {

        this.gp = gp;
        name = "Bomb";
        this.hp = 0;
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/bomb.png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        }catch(IOException e){
            e.printStackTrace();
        }

        collision = true;
    }
}
