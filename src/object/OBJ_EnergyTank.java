package object;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;

public class OBJ_EnergyTank extends SuperObject{
    
    GamePanel gp;

    public OBJ_EnergyTank(GamePanel gp) {

        this.gp = gp;
        this.hp = 80;
        this.collision = true;
        name = "Et";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/medkit.png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
