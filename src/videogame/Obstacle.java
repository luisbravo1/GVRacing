/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author GoodVibesOnly
 */
public class Obstacle extends Item{

    private int speed;          // to set the speed
    private int color;          // to set car color
    private Game game;          // to use game
    
    public Obstacle(int x, int y, int width, int height, int color, Game game) {
        super(x, y, width, height);
        this.game = game;
        this.speed = 6;
        this.color = color;
        checkColor();
    }
    
    

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * To get the color
     * @return 
     */
    public int getColor() {
        return color;
    }

    /**
     * To set the color
     * @param color 
     */
    public void setColor(int color) {
        this.color = color;
    }
    
    public void respawn() {
        setX(((int) (Math.random() * game.getWidth())));
        setY(-300);
        setColor((int) (Math.random() * 7) + 1);
        setSpeed(6);
        
        checkColor();
        
    }
    
    private void checkColor() {
        if (getX() < game.getWidth() / 4 + 40) {
            color = 6;
            setWidth(50);       
        } else if (getX() > (game.getWidth() / 2 + game.getWidth()/4 - 50)) {
            color = 5;
            setWidth(100);
        } else if (color == 6 || color == 5) {
            setColor((int) (Math.random() * 4) + 1);
            setWidth(50);
        } else {
            setWidth(50);
        }      
    }
    
    @Override
    public void tick() {
        if (color == 5) {
            setY(getY() + (game.getSpeed()));
        } else if (color == 6) {
            setY(getY() + (getSpeed() + game.getSpeed()));
        } else if (color == 7) {
            setY(getY() + (game.getSpeed()));
        } else {
           setY(getY() + (getSpeed() + game.getSpeed()));
        }
       
        // reset x position if collision with walls
        if (getX() + 100 >= game.getWidth()) {
            setX(game.getWidth() - 100);
        }
        else if (getX() <= 0) {
            setX(0);
        }

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.car[color], getX(), getY(), getWidth(), getHeight(), null);
    }
    
    public BufferedImage getSprite() {
        return Assets.car[color];
    }
}
