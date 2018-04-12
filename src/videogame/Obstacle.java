/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;

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
        this.speed = 1;
        this.color = color;
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
        setY(getY() + 8);
       
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
}
