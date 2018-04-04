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
    private Game game;
    
    public Player(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
    
    public void moveForward(float angle) {
        x += speed * math.sin(angle);
        y += speed * math.cos(angle);
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
        if (game.getKeyManager().up) {
           setY(getY() - 7);
        }
        if (game.getKeyManager().down) {
           setY(getY() + 7);
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
