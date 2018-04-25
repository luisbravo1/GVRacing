/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import javax.swing.Timer;

/**
 *
 * @author GoodVibesOnly
 */
public class Game implements Runnable {
    private BufferStrategy bs;      // to have several buffers when displaying
    private Graphics g;             // to paint objects
    private Display display;        // to display in the game
    String title;                   // title of the window
    private int width;              // width of the window
    private int height;             // height of the window
    private int posX;               // to store random posX of cars
    private int posX2;              // to store random posX of cars
    private Thread thread;          // thread to create the game
    private boolean running;        // to set the game
    private boolean started;        // to set the start
    private Player player;          // to use a player 
    private KeyManager keyManager;  // to manage the keyboard
    private ArrayList<Obstacle> obstacles;   // to store obstacles collection
    private ArrayList<Obstacle> background;     // to store background collection
    private ParticleSystem explosions;       // to store explosions
    
    private int speed;
    
    private int BGpos;

    
    /**
     * to create title, width and height and set the game is still not running
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height  to set the height of the window
     */
    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        running = false;
        keyManager = new KeyManager();
        BGpos = 0;
        this.speed = 8;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;     
    }

    public ParticleSystem getExplosions() {
        return explosions;
    }

    public void setExplosions(ParticleSystem explosions) {
        this.explosions = explosions;
    }
    
    

    /**
     * To get the width of the game window
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the height of the game window
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * To know if the game has started
     * @return a <code>boolean</code> true is the game has started
     */
    public boolean isStarted() {
        return started;
    }

    /**
     * To set if the game has started
     * @param started 
     */
    public void setStarted(boolean started) {
        this.started = started;
    }
    
    /**
     * To get KeyManager
     * @return 
     */
    public KeyManager getKeyManager() {
        return keyManager;
    }
    
    /**
     * initializing the display window of the game
     */
    private void init() {
         display = new Display(title, getWidth(), getHeight());  
         Assets.init();
         display.getJframe().addKeyListener(keyManager);
         player = new Player(getWidth()/2, getHeight() - 200, 40, 30, this);
         
        // create the Array collection of cars
        obstacles = new ArrayList<Obstacle>();
        // adding enemies to the collection
        for (int i = 1; i <= 6; i++) {
            posX = ((int) (Math.random() * (getWidth())));
            obstacles.add(new Obstacle(posX, 200*i-900, 50, 90, ((int) (Math.random() * 4) + 1), this));
        }
        
        explosions = new ParticleSystem(Assets.explosion,this,100,100);
 
    }
    
    @Override
    public void run() {
        init();
        // frames per second
        int fps = 60;
        // time for each tick in nano segs
        double timeTick = 1000000000 / fps;
        // initializing delta
        double delta = 0;
        // define now to use inside the loop
        long now;
        // initializing last time to the computer time in nanosecs
        long lastTime = System.nanoTime();
        while (running) {
            // setting the time now to the actual time
            now = System.nanoTime();
            // acumulating to delta the difference between times in timeTick units
            delta += (now - lastTime) / timeTick;
            // updating the last time
            lastTime = now;
            
            // if delta is positive we tick the game
            if (delta >= 1) {
                // ticks only if the player has lives
                if(!getKeyManager().isPause()) {
                    tick();
                }
                else {
                    getKeyManager().tick();
                }
                
                render();
                BGpos += getSpeed();
                if (BGpos > getHeight()) {
                    BGpos = 0;
                }
                delta --;
            }
        }
        stop();
    }
    
    /**
     * To update the game in every tick
     */
    private void tick() {
        
        keyManager.tick();
        
        player.tick();
        
        explosions.tick();
        
        setSpeed(player.getSpeedY());
        // moving the enemies
        Iterator itr = obstacles.iterator();
        while (itr.hasNext()) {
            Obstacle obstacles = (Obstacle) itr.next();
            obstacles.tick();
            if (obstacles.intersects(player)) {
                player.setSpeedY(0);                    
                obstacles.setSpeed(0);
                 if (keyManager.space) {
                    if (player.getColor() == -1) {
                        player.setColor(obstacles.getColor());
                        
                        obstacles.setX(((int) (Math.random() * getWidth()/4 + 100)) + 400);
                        obstacles.setY(-300);
                        obstacles.setSpeed(6);
                        
                     }
                    
                } else if (player.getColor() != -1) {
                     player.setColor(-1); 
                     explosions.SpawnParticle(player.getX(),player.getY());
                 }
                 
               // setSpeed(0);
            }
            // re set positions if getting out of the screen
            if (obstacles.getY() > 780) {
                posX2 = ((int) (Math.random() * getWidth()));
                obstacles.setX(posX2);
                obstacles.setY(-300);
                obstacles.setColor((int) (Math.random() * 4) + 1);
                obstacles.setSpeed(6);
            }
        }


    }
    
    /**
     * To render the game
     */
    private void render() {
        // get the buffer strategy from the display
        bs = display.getCanvas().getBufferStrategy();
        /* if it is null, we define one with 3 buffers to display images of
        the game, if not null, then we display every image of the game but
        after clearing the Rectanlge, getting the graphic object from the 
        buffer strategy element. 
        show the graphic and dispose it to the trash system
        */
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        }
        else
        {
            g = bs.getDrawGraphics();

            g.drawImage(Assets.background, 0, BGpos, width, height, null);
            g.drawImage(Assets.background, 0, BGpos - getHeight(), width, height, null);
            player.render(g);
            // render enemies
            Iterator itr = obstacles.iterator();
            while (itr.hasNext()) {
                Obstacle obstacles = (Obstacle) itr.next();
                obstacles.render(g);
            }
            explosions.render(g);
            
            bs.show();
            g.dispose();
        }
       
    }
    
    /**
     * setting the thread for the game
     */
    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }
    
    /**
     * stopping the thread
     */
    public synchronized void stop() {
        if (running) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }           
        }
    }
}
