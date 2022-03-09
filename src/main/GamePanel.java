package main;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import entity.Player;
import object.SuperObject;
import entity.Bullet;
import tile.TileManager;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    
    // SCREEN SETTINGS
    final int originalTileSize = 16; //16x16tile
    public int scale = 2;

    public final int bomb_rate = 4;
    public int et_count = 0;
    public final int max_et = 3;

    public int tileSize = originalTileSize * scale; //48x48tile
    public int maxScreenCol = 25;
    public int maxScreenRow = 20;
    public int screenWidth = tileSize * maxScreenCol; //768pixel
    public int screenHeight = tileSize * maxScreenRow; //576pixel

    // WORLD SETTINGS
    public final int maxWorldCol = 100;
    public final int maxWorldRow = 80;
    // public final int worldWidth = tileSize * maxScreenCol;
    // public final int worldHeight = tileSize * maxWorldRow;

    // FPS
    int FPS = 60;

    public int gameState = 0;
    public final int titleState = 0;
    public final int playState = 1;
    public final int gameOverState = 2;
    public final int finishState = 3;

    long firingTimer;
    int firingDelay = 600;
    public long healingTimer;
    public int healingDelay = 600;

    //SYSTEM
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;

    //ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[500];
    public Bullet bullets[] = new Bullet[10];

    public Minimap minimap = new Minimap(this);

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void SetupGame() {

        aSetter.setObject();
        //playMusic(0);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    // public void run(){
        
    //     double drawInterval = 1000000000/FPS; //0.016666 seconds
    //     double nextDrawTime = System.nanoTime() + drawInterval;

    //     while(gameThread != null){
    //         System.out.println("Running");

    //         update();
    //         repaint();
            

    //         try {
    //             double remainingTime = nextDrawTime - System.nanoTime();
    //             remainingTime /= 1000000;
    //             if(remainingTime < 0){
    //                 remainingTime = 0;
    //             }
    //             Thread.sleep((long) remainingTime);
    //             nextDrawTime += drawInterval;
    //         } catch (InterruptedException e) {
    //             e.printStackTrace();
    //         }
    //     }
    // }
    
    public void run(){
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        //int drawCount = 0;
        int delayCount = 0;
        int delayTimer = 20;


        while(gameThread != null){
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if(delta >= 1){
                update();
                repaint();
                delta--;
                //drawCount++;
            }
            if(timer >= 1000000000){
                // System.out.println(drawCount);
                // drawCount = 0;
                delayCount++;
                if(delayCount>=delayTimer){
                    if(et_count<max_et)
                    {
                        aSetter.RandomEt();
                        delayCount = 0;
                    }
                }
                timer = 0;
            }
        }
    }
    public void createBullet(int X, int Y, String Direction){
        long elapsed = (System.nanoTime() - firingTimer) / 1000000;
        if(elapsed > firingDelay){
            for(int i=0; i<bullets.length; i++)
            {
                if(bullets[i]==null){
                    bullets[i] = new Bullet(this,X,Y,Direction);
                    firingTimer = System.nanoTime();
                    break;
                }
            }
        }
    }

    public void update(){
        if(gameState==playState){
            player.update();
            for(int i=0;i<bullets.length;i++)
            {
                if(bullets[i]!=null){
                    if(bullets[i].update()==false){
                        bullets[i] = null;
                    }
                }
            }
        }
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if(gameState==titleState){
            ui.drawTitleScreen(g2);
        }
        else if(gameState==playState){
            // TILE
            tileM.draw(g2);

            //OBJECT
            for(int i = 0; i < obj.length; i++){
                if(obj[i] != null){
                    obj[i].draw(g2, this);
                }
            }

            // PLAYER
            player.draw(g2);
            
            for(int i=0; i<bullets.length; i++){
                if(bullets[i] != null){
                    bullets[i].draw(g2);
                }
            }
            
            minimap.draw(g2);
            ui.draw(g2);
            ui.drawHp(g2);
            
            g2.dispose();
        }
        else if(gameState==gameOverState){
            ui.drawGameOver(g2);
        }
        else if(gameState==finishState){
            ui.drawFinishScreen(g2);
        }
        
    }

    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic(){
        music.stop();
    }

    public void playSE(int i){
        se.setFile(i);
        se.play();
    }
}
