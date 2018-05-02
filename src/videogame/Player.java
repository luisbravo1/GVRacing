/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @authors team : GoodVibesOnly
 */
public class Player extends Item{

    private int direction;      // to set the direction
    private int speed;          // to set the speed
    private Game game;          // to use game
    private int color;          // to get the type of car
    private int speedY;         // speed Y
    private int speedX;         // speed X
    private int maxSpeed;       // to set speed of player
    
    public Player(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
        this.speed = 1;
        maxSpeed = 5;
        color = -1;
        speedX = 7;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    /**
     * Get speed on X axis
     * @return int
     */
    public int getSpeedX() {
        return speedX;
    }

    /**
     * Set speed on X axis
     * @param speedX 
     */
    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }
    
    /**
     * To get direction
     * @return an <code>int</code> value determining direction
     */
    public int getDirection() {
        return direction;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        
        if (color == -1) {
          setMaxSpeed(5);
          setSpeedY(2);
          setSpeedX(7);
          game.getExplosions().SpawnParticle(getX(), getY());
          game.crash.play();
        } else if (color == 6) {
            setMaxSpeed(9);
            setSpeedX(5);
        } else if (color == 4) {
            setMaxSpeed(17);
            setSpeedX(10);
        } else {
          setMaxSpeed(13);
          setSpeedX(7);
        }
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
    
    
    
    /**
     * To set direction
     * @param direction  
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }
    
    @Override
    public void tick() {
        
        if (getX() < game.getWidth() / 4 + 40 && getColor() < 6 && getColor() > -1) {
            setColor(-1);
            setWidth(50);
            
        } else if (getX() > (game.getWidth() / 2 + game.getWidth()/4 - 50) && getColor() == 6) {
            setColor(-1);
            setWidth(50);

        } else if (getX() > game.getWidth() / 4 + 40 && getX() < (game.getWidth() / 2 + game.getWidth()/4 - 50) && getColor() > 5) {
            setWidth(50);
            setColor(-1);
        }
        
        
        
        // moving player depending on keys <- ->
        if (game.getKeyManager().left) {
          setX(getX() - speedX);
        }
        if (game.getKeyManager().right) {
          setX(getX() + speedX);
        }
        
            // moving player depending on keys <- ->
        if (game.getKeyManager().up) {
             //setY(getY() - 7);
          if (getSpeedY() <= getMaxSpeed()) {
                setSpeedY(getSpeedY() + 1);
           }
        } else if (game.getKeyManager().down) {
           if (getSpeedY() > 0) {
                setSpeedY(getSpeedY() - 2);
           }       
        } else if (getSpeedY() > 0) {
            setSpeedY(getSpeedY() - 1);
        }

        
        
        // reset x position if collision with walls
        if (getX() + 100 >= game.getWidth()) {
            setX(game.getWidth() - 100);
        }
        else if (getX() <= 0) {
            setX(0);
        }
        
        // reset y position if collision with walls
        if (getY() + 100 >= game.getHeight()) {
            setY(game.getHeight() - 100);
        }
        else if (getY() <= 0) {
            setY(0);
        }
    }

    @Override
    public void render(Graphics g) {
        if (getColor() == -1) {
            g.drawImage(Assets.player, getX(), getY(), getWidth(), getHeight(), null);
        } else {
            g.drawImage(Assets.pcar[getColor()], getX(), getY(), 50, 90, null);
        }
    }
}
