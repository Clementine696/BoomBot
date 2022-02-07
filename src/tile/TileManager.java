package tile;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.awt.*;
import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap();
        RandomBomb();
        RandomPrincess();
    }

    public void getTileImage(){
        try{

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/glass0.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/glass1.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/glass2.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/bomb.png"));
            tile[3].collision = true;
            tile[3].bomb = true;

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/princess.png"));
            tile[4].collision = true;
            tile[4].princess = true;

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(){
        try{
            InputStream is = getClass().getResourceAsStream("/res/maps/map01.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();

                while(col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void RandomBomb(){
        for(int col=0;col<gp.maxWorldCol;col++){
            for(int row=0;row<gp.maxWorldRow;row++){
                if(mapTileNum[col][row] == 0){
                    Random R = new Random();
                    int x = R.nextInt(100);
                    if(x<7){
                        mapTileNum[col][row] = 3;
                    }
                }
            }
        }
        mapTileNum[1][1] = 0;
    }

    public void RandomPrincess(){
        Random R = new Random();
        int x = R.nextInt(gp.maxWorldCol-2)+1;
        int y = R.nextInt(gp.maxWorldRow-2)+1;
        mapTileNum[x][y] = 4;
    }


    public void draw(Graphics2D g2){
        // g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
        // g2.drawImage(tile[1].image, 48, 0, gp.tileSize, gp.tileSize, null);

        int worldCol = 0;
        int worldRow = 0;
        

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
            
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                    g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                }
            
            worldCol++;
            
            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;

            }
        }
    }
}