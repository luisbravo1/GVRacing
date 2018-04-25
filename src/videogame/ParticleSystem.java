/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author hectordeluna
 */
public class ParticleSystem{
    
    private Animation anim;     // To store the Animation
    private ArrayList<Particle> particles;       // To store the Particles
    private int width;
    private int height;
    private int index;
    
    public ParticleSystem(BufferedImage[] sprites, Game game,int width,int height) {
        anim = new Animation(sprites,300);
        particles = new ArrayList<Particle>();
        this.width = width;
        this.height = height;
    }
    
    
    public void SpawnParticle(int x, int y) {
        while (index < particles.size()) {
            if (!particles.get(index).isInUse()) {
                particles.get(index).setX(x - width/4);
                particles.get(index).setY(y - height/4);
                particles.get(index).setInUse(true);
                break;
            }
            index++;
        }
        
        if (index == particles.size()) {
            particles.add(new Particle(x - (width/2),y,width,height,anim));
        }
        
    }
    

    public void tick() {
        Iterator itr = particles.iterator();
        while (itr.hasNext()) {
          
          Particle particle = (Particle) itr.next();
          if (particle.isInUse()) {
            particle.tick();
          }
        }
    }
    
    
    public void render(Graphics g) {
        Iterator itr = particles.iterator();
        while (itr.hasNext()) {
          
          Particle particle = (Particle) itr.next();
          if (particle.isInUse()) {
            particle.render(g);
          }
        }
    }

    
    
}
