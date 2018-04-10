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
 * @author GoodVibesOnly
 */
public class Player extends Item{

    private int direction;
    private int speed;
    private int numPlayer;
    private Game game;
    
    public Player(int x, int y, int width, int height, int numPlayer, Game game) {
        super(x, y, width, height);
        this.game = game;
        this.speed = 1;
        this.numPlayer = numPlayer;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
    
    @Override
    public void tick() {
        // moving player depending on keys <- ->
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
        } else {
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
