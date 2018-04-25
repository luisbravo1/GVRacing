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
    public boolean w;       // flag to move up the player
    public boolean a;       // flag to move left the player
    public boolean s;       // flag to move down the player
    public boolean d;       // flag to move right the player
    public boolean space;    // flag to space
    public boolean pause;   // flag to pause the game

    private boolean keys[];  // to store all the flags for every key
    
    public boolean isPause() {
        return pause;
    }
    
    public KeyManager() {
        keys = new boolean[256];
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // set true to every key pressed
        if(e.getKeyCode() != KeyEvent.VK_P ) {
            keys[e.getKeyCode()] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // set false to every key released
        if(e.getKeyCode() == KeyEvent.VK_P) {
            keys[e.getKeyCode()] = !keys[e.getKeyCode()];
        } else {
            keys[e.getKeyCode()] = false;
        }
    }
    
    /**
     * to enable or disable moves on every tick
     */
    public void tick() {
        up = keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_DOWN];
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
        w = keys[KeyEvent.VK_W];
        a = keys[KeyEvent.VK_A];
        s = keys[KeyEvent.VK_S];
        d = keys[KeyEvent.VK_D];
        space = keys[KeyEvent.VK_SPACE];
        pause = keys[KeyEvent.VK_P];
    }
}
