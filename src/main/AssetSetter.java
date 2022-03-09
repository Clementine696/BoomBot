package main;
import object.OBJ_EnergyTank;
import object.OBJ_Bomb;
import java.util.Random;

public class AssetSetter {
    
    GamePanel gp;
    int count = 0;
    public int Object_Location[][];

    public AssetSetter(GamePanel gp){
        this.gp = gp;
        Object_Location = new int[gp.maxWorldCol][gp.maxWorldRow];
    }

    public void setObject(){

        // gp.obj[0] = new OBJ_Key(gp);
        // gp.obj[0].worldX = 23 * gp.tileSize;
        // gp.obj[0].worldY = 7 * gp.tileSize;
        
        // gp.obj[1] = new OBJ_Key(gp);
        // gp.obj[1].worldX = 23 * gp.tileSize;
        // gp.obj[1].worldY = 40 * gp.tileSize;

        RandomBomb();
        RandomEt();
        // System.out.println(count);
        // System.out.println(gp.obj.length);

    }

    public void RandomBomb(){
        int bomb_rate = gp.bomb_rate;
        Random R = new Random();
        // int bomb_rate = ;
        for(int col=1;col<gp.maxWorldCol-1;col++){
            for(int row=1;row<gp.maxWorldRow-1;row++){
                int x = R.nextInt(100);
                if(x<bomb_rate){
                    gp.obj[count] = new OBJ_Bomb(gp);
                    gp.obj[count].worldX = col * gp.tileSize;
                    gp.obj[count].worldY = row * gp.tileSize;
                    Object_Location[col][row] = 1;
                    count++;
                }
            }
        }
    }

    public void RandomNewbomb(){
        Random R = new Random();
        while(true){
            int x =  R.nextInt(gp.maxWorldCol-1) + 1;
            int y =  R.nextInt(gp.maxWorldRow-1) + 1;
            if(Object_Location[x][y] == 0){
                gp.obj[count] = new OBJ_Bomb(gp);
                gp.obj[count].worldX = x * gp.tileSize;
                gp.obj[count].worldY = y * gp.tileSize;
                System.out.println("new Bomb at " + x + " " + y);
                count++;
                break;
            }
        }
    }

    public void RandomEt(){
        Random R = new Random();
        while(true){
            int x =  R.nextInt(gp.maxWorldCol-1) + 1;
            int y =  R.nextInt(gp.maxWorldRow-1) + 1;
            if(Object_Location[x][y] == 0){
                gp.obj[count] = new OBJ_EnergyTank(gp);
                gp.obj[count].worldX = x * gp.tileSize;
                gp.obj[count].worldY = y * gp.tileSize;
                System.out.println("new Et at " + x + " " + y);
                count++;
                gp.et_count++;
                break;
            }
        }
    }

}
