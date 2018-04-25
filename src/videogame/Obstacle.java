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
    
    @Override
    public void tick() {
        if (getX() < game.getWidth() / 4 + 40) {
            color = 6;
            setWidth(50);
            setY(getY() + (getSpeed() + game.getSpeed()));
            
        } else if (getX() > (game.getWidth() / 2 + game.getWidth()/4 - 50)) {
            color = 5;
            setWidth(100);
            setY(getY() + (game.getSpeed()));

        } else {
            setWidth(50);
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
