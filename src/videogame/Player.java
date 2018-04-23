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
    private BufferedImage Car;       // to have a car 
    private int speedY;         // speed Y
    
    public Player(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
        this.speed = 1;
    }
    
   

    /**
     * To get direction
     * @return an <code>int</code> value determining direction
     */
    public int getDirection() {
        return direction;
    }

    public BufferedImage getCar() {
        return Car;
    }

    public void setCar(BufferedImage Car) {
        this.Car = Car;
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
        
        
        
        
        // moving player depending on keys <- ->
        if (game.getKeyManager().left) {
          setX(getX() - 7);
        }
        if (game.getKeyManager().right) {
          setX(getX() + 7);
        }
        
        if (getCar() == null) {
            // moving player depending on keys <- ->
           if (game.getKeyManager().up) {
             setY(getY() - 7);
           }
           if (game.getKeyManager().down) {
             setY(getY() + 7);
           }

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
        if (getCar() == null) {
            g.drawImage(Assets.player, getX(), getY(), getWidth(), getHeight(), null);
        } else {
            g.drawImage(Car, getX(), getY(), 50, 90, null);
        }
    }
}
