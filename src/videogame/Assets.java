/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.image.BufferedImage;

/**
 *
 * @author GoodVibesOnly
 */
public class Assets {
    public static BufferedImage backgrounds[]; // to store background image
    public static BufferedImage car[];     // to store the cars images
    public static BufferedImage player; // to store the player image
    public static BufferedImage explosion[]; // to store explosion
    public static BufferedImage tree;       // to store skrt mark

    /**
     * initializing the images of the game
     */
    public static void init() {
        backgrounds = new BufferedImage[4];
        backgrounds[0] = ImageLoader.loadImage("/images/track.png");
        backgrounds[1] = ImageLoader.loadImage("/images/Fondos/Fondo2.png");
        backgrounds[2] = ImageLoader.loadImage("/images/Fondos/Fondo3.png");
        backgrounds[3] = ImageLoader.loadImage("/images/Fondos/Fondo4.png");
        // Assign different cars available
        car = new BufferedImage[7];
        car[0] = ImageLoader.loadImage("/images/car_blue_1.png");
        car[1] = ImageLoader.loadImage("/images/car_green_2.png");
        car[2] = ImageLoader.loadImage("/images/car_red_3.png");
        car[3] = ImageLoader.loadImage("/images/car_yellow_4.png");
        car[4] = ImageLoader.loadImage("/images/motorcycle_black.png");
        car[5] = ImageLoader.loadImage("/images/tree_small.png");
        car[6] = ImageLoader.loadImage("/images/hullSmall (1).png");
        player = ImageLoader.loadImage("/images/character_black_red.png");
        
        explosion = new BufferedImage[5];
        explosion[0] = ImageLoader.loadImage("/images/Explosion/explosion1.png");
        explosion[1] = ImageLoader.loadImage("/images/Explosion/explosion2.png");
        explosion[2] = ImageLoader.loadImage("/images/Explosion/explosion3.png");
        explosion[3] = ImageLoader.loadImage("/images/Explosion/explosion4.png");
        explosion[4] = ImageLoader.loadImage("/images/Explosion/explosion5.png");
        
        tree = ImageLoader.loadImage("/images/tree_small.png");
    }
    
}
