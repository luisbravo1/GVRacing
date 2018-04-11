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
    public static BufferedImage background; // to store background image
    public static BufferedImage car[];     // to store the cars images

    /**
     * initializing the images of the game
     */
    public static void init() {
        background = ImageLoader.loadImage("/images/track1.jpeg");
        // Assign different cars available
        car = new BufferedImage[5];
        car[0] = ImageLoader.loadImage("/images/car_blue_1.png");
        car[1] = ImageLoader.loadImage("/images/car_green_2.png");
        car[2] = ImageLoader.loadImage("/images/car_red_3.png");
        car[3] = ImageLoader.loadImage("/images/car_yellow_4.png");
        car[4] = ImageLoader.loadImage("/images/motorcycle_black.png");
    }
    
}
