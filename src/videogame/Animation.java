/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.image.BufferedImage;

/**
 *
 * @author hectordeluna
 */
public class Animation {
    
    private int speed;      // for the speed of every frame
    public int index;      // to get the index of next frame to paint
    private long lastTime;   // to save the previous time of animation
    private long timer;     // to acumulate the time of the animation
    private BufferedImage[] frames;     // to store every image - frame
    private boolean finished;           // to know when a cycle has ended

    public int getFramesLength() {
        return frames.length;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
   
    
    
    
    /**
     * Creating the animation with all the frames and the speed for each
     * @param frames
     * @param speed 
     */
    public Animation(BufferedImage[] frames,int speed) {
        this.frames = frames; // storing frames
        this.speed = speed; // storing speed
        index = 0; // init index
        timer = 0; // init timer
        lastTime = System.currentTimeMillis(); // getting the initial time
    }
   
    /**
     * Getting the current frame to paint
     * @return the BufferedImage to the corresponding frame to paint
     */
    public BufferedImage getCurrentFrame() {
        return frames[index];
    }
    
    /**
     * To update the animation to get the right index of the frame to paint
     */
    public void tick() {
        // acumulating time from the previous tick to this one
        timer += System.currentTimeMillis();
        // updating the lastTime for the next trick
        lastTime = System.currentTimeMillis();
        // check the timer to increase the index
        if (timer > lastTime) {
            index++;
            timer = 0;
            // check index not to get out of the bounds
            if (index >= frames.length) {
                setFinished(true);
                index = 0;
                
            }
        }
    }
    
}
