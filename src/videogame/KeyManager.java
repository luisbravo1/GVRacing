/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author GoodVibesOnly
 */
public class KeyManager implements KeyListener {
    
    public boolean up;      // flag to move up the player
    public boolean down;    // flag to move down the player
    public boolean left;    // flag to move left the player
    public boolean right;   // flag to move right the player
    public boolean space;   // flag to space
    public boolean pause;   // flag to pause the game
    public boolean start;   // flag to start the game
    public boolean exit;    // flat to exit the game

    private boolean keys[];  // to store all the flags for every key
    
    public boolean start() {
        return start;
    }
    
    public KeyManager() {
        keys = new boolean[256];
    }
    
    public void setPause(boolean pause) {
        this.pause = pause;
    }
    
    public boolean isPause() {
        return pause;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // set true to every key pressed
        if(e.getKeyCode() != KeyEvent.VK_P) {
            keys[e.getKeyCode()] = true;
        }
        
        /*if(e.getKeyCode() != KeyEvent.VK_S) {
            keys[e.getKeyCode()] = true;
        }*/
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // set false to every key released
        if(e.getKeyCode() == KeyEvent.VK_P) {
            keys[e.getKeyCode()] = !keys[e.getKeyCode()];
        } else {
            keys[e.getKeyCode()] = false;
        }
        
        /*if(e.getKeyCode() == KeyEvent.VK_S) {
            keys[e.getKeyCode()] = !keys[e.getKeyCode()];
        } else {
            keys[e.getKeyCode()] = false;
        }*/
    }
    
    /**
     * to enable or disable moves on every tick
     */
    public void tick() {
        up = keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_DOWN];
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
        space = keys[KeyEvent.VK_SPACE];
        pause = keys[KeyEvent.VK_P];
        start = keys[KeyEvent.VK_S];
        exit = keys[KeyEvent.VK_E];
    }
}
