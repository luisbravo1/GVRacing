/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
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
    private boolean win;            // to check if the player has won the game
    private boolean lose;           // to check if the time has run out
    private Player player;          // to use a player 
    private KeyManager keyManager;  // to manage the keyboard
    private ArrayList<Obstacle> obstacles;   // to store obstacles collection
    private ArrayList<Obstacle> background;  // to store background collection
    private ArrayList<Obstacle> menu;        // to store menu collection
    private ParticleSystem explosions;       // to store explosions

   // private Cinematic cinematic;          // Cinematic object

    public SoundClip crash;        // to store crash sounds
    public SoundClip music;        // to store music
    public SoundClip door;         // to store door sound
    public SoundClip ambience;     // to store ambience sound

    
    private int backgroundselec;    // to select a background
    
    private int speed;              // the global speed
    
    private int BGpos;              // to know the position of the background
    
    private int distance;           // to keep track of distance
    private long timer;              // to keep track of time
    private long startOfGame;       // save the time the game started
    private long goal;              // max time in seconds
    
    private int level;              // level of the game
    
    private Font customFont;        // Custom font
    

    
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
        win = false;
        lose = false;
        keyManager = new KeyManager();
        BGpos = 0;
        this.speed = 8;

        level = 0;
        Files.loadFile(this);
        distance = 2500 - (level * 200);
        goal = 60 - (level * 5);

        timer = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        startOfGame = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());

        crash = new SoundClip("/sound/crash.wav");
        music = new SoundClip("/sound/offlimits.wav");
        door = new SoundClip("/sound/door.wav");
        ambience = new SoundClip("/sound/ambience.wav");
        backgroundselec = 1;

    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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
         backgroundselec = (int) (Math.random() * 4);
         display = new Display(title, getWidth(), getHeight());  
         Assets.init();
         display.getJframe().addKeyListener(keyManager);
         player = new Player(getWidth()/2, getHeight() - 200, 40, 30, this);
         
        // create the Array collection of cars
        obstacles = new ArrayList<Obstacle>();
        // adding cars to the collection
        for (int i = 1; i <= 6; i++) {
            posX = ((int) (Math.random() * (getWidth())));
            obstacles.add(new Obstacle(posX, 200*i-900, 50, 90, ((int) (Math.random() * 4) + 1), this));
        }
        
        explosions = new ParticleSystem(Assets.explosion,this,100,100);

        music.setLooping(true);
        music.play();
        ambience.setLooping(true);
        ambience.play();
        
        
       // cinematic = new Cinematic(Assets.intro,1);
        
/* no jala
        try {
            //create the font to use. Specify the size!
            InputStream myStream = new BufferedInputStream(new FileInputStream("/images/car_blue_1.png"));

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, myStream));
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, myStream).deriveFont(12f);
        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }
        */
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
                // cinematic.isFinished()
                if(getKeyManager().isPause()) {
                    tick();
                    
                    BGpos += getSpeed();
                    distance -= getSpeed() / 6;
                    if (BGpos > getHeight()) {
                        BGpos = 0;
                    }
                }
                else {
                    getKeyManager().tick();
                    //cinematic.startCinematic();
                }
                
                render();
                        

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
        
        timer = goal - (TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) - startOfGame);
        
        if (timer <= 0) {
            timer = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
            startOfGame = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
            goal = 60 - (level * 5);
            distance = 2500 + (level * 200);
        }
        
        if (distance <= 0) {
            setLevel(getLevel() + 1);
            Files.saveFile(this);
            distance = 2500 + (level * 200);
            timer = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
            startOfGame = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
            goal = 60 - (level * 5);
        }
        

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
                        door.play();
                        player.setColor(obstacles.getColor());
                        

                        obstacles.respawn();
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
                obstacles.respawn();
            }
        }
        if (distance <= 0) {
            win = true;
        }
        if (timer <= 0) {
            lose = true;
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
            
            g.drawImage(Assets.backgrounds[level], 0, BGpos, width, height, null);
            g.drawImage(Assets.backgrounds[level], 0, BGpos - getHeight(), width, height, null);
            player.render(g);
            
            
            
            // render enemies
            Iterator itr = obstacles.iterator();
            while (itr.hasNext()) {
                Obstacle obstacles = (Obstacle) itr.next();
                obstacles.render(g);
            }
            explosions.render(g);
            // cinematic.render(g);
            //g.setFont(customFont);
            g.setFont(new Font("Verdana", Font.PLAIN, 14));
            g.setColor(Color.BLACK);
            g.drawString("Time left: " + timer, width/2 - 100, 50);
            g.drawString("Distance: " + distance + "m", width/2 + 20, 50);
            if (!getKeyManager().isPause() && !started) {
                g.drawImage(Assets.menu[4], 0, 0, width, height, null); 
            }
            //NO SE QUITA A LA PRIMERA PRESIONADA
            /*if (!getKeyManager().start) {
                if (!started) {
                    g.drawImage(Assets.menu[1], 0, 0, width, height, null);
                    started = false;
                    //getKeyManager().setPause(false);
                }
            }*/
            if (win) {
                g.drawImage(Assets.menu[5], 0, 0, width, height, null);
            }
            if (lose) {
                g.drawImage(Assets.menu[3], 0, 0, width, height, null);
            }
            bs.show();
            g.dispose();
            
            // To exit game
            if (getKeyManager().exit) {
                exit(0);
            }
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
