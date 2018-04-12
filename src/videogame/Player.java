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
    private Game game;          // to use game
    
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
        g.drawImage(Assets.car[0], getX(), getY(), getWidth(), getHeight(), null);
    }
}
