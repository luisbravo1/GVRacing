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
 * @author hectordeluna
 */
public class Particle extends Item {
    
    private Animation anim;     // To store the Animation
    private boolean inUse;         // To check if the particle is in use
    private int animLength;     // Length of animation

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }
    
    public Particle(int x, int y, int width, int height, Animation anim) {
        super(x,y,width,height);
        this.anim = anim;
        animLength = anim.getFramesLength();
        this.inUse = false;
    }
    

    @Override
    public void tick() {
        
        // check index not to get out of the bounds
        if (anim.isFinished()) {
            
            setX(0);
            setY(0);
            setInUse(false);
            anim.setFinished(false);
        } else {
            this.anim.tick();
        }
    }

    @Override
    public void render(Graphics g) {
       if (isInUse())
       g.drawImage(anim.getCurrentFrame(),getX(),getY(),getWidth(),getHeight(), null);
    }
    
    
}
