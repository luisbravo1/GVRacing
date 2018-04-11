/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @authors team : GoodVibesOnly
 */
public class Player extends Item{

    private int direction;      // to set the direction
    private int speed;          // to set the speed
    private int numPlayer;      // to set the player number
    private Game game;          // to use game
    
    public Player(int x, int y, int width, int height, int numPlayer, Game game) {
        super(x, y, width, height);
        this.game = game;
        this.speed = 1;
        this.numPlayer = numPlayer;
    }

    /**
     * To get direction
     * @return an <code>int</code> value determining direction
     */
    public int getDirection() {
        return direction;
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
        // and depending on what player number
        if (numPlayer == 1) {
            if (game.getKeyManager().left) {
              setX(getX() - 7);
            }
            if (game.getKeyManager().right) {
              setX(getX() + 7);
            }
            if (game.getKeyManager().up) {
               setY(getY() - 7);
            }
            if (game.getKeyManager().down) {
               setY(getY() + 7);
            }
        } else if (numPlayer == 2) {
            if (game.getKeyManager().a) {
              setX(getX() - 7);
            }
            if (game.getKeyManager().d) {
              setX(getX() + 7);
            }
            if (game.getKeyManager().w) {
               setY(getY() - 7);
            }
            if (game.getKeyManager().s) {
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
        if (numPlayer == 1) {
            g.drawImage(Assets.car[0], getX(), getY(), getWidth(), getHeight(), null);
        } else {
            g.drawImage(Assets.car[2], getX(), getY(), getWidth(), getHeight(), null);
        }
    }
}
