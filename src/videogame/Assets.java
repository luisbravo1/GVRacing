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
    public static BufferedImage pcar[];     // to store the cars images
    public static BufferedImage player; // to store the player image
    public static BufferedImage explosion[]; // to store explosion
    public static BufferedImage tree;       // to store skrt mark
    public static BufferedImage menu[];         // to store the menu images

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
        car = new BufferedImage[8];
        car[0] = ImageLoader.loadImage("/images/dblue.png");
        car[1] = ImageLoader.loadImage("/images/dgreen.png");
        car[2] = ImageLoader.loadImage("/images/dred.png");
        car[3] = ImageLoader.loadImage("/images/dyellow.png");
        car[4] = ImageLoader.loadImage("/images/dblack.png");
        car[5] = ImageLoader.loadImage("/images/tree_small.png");
        car[6] = ImageLoader.loadImage("/images/hullSmall (1).png");
        car[7] = ImageLoader.loadImage("/images/oil.png");
        // cars facing up
        pcar = new BufferedImage[8];
        pcar[0] = ImageLoader.loadImage("/images/car_blue_1.png");
        pcar[1] = ImageLoader.loadImage("/images/car_green_2.png");
        pcar[2] = ImageLoader.loadImage("/images/car_red_3.png");
        pcar[3] = ImageLoader.loadImage("/images/car_yellow_4.png");
        pcar[4] = ImageLoader.loadImage("/images/motorcycle_black.png");
        pcar[5] = ImageLoader.loadImage("/images/tree_small.png");
        pcar[6] = ImageLoader.loadImage("/images/dboat.png");
        pcar[7] = ImageLoader.loadImage("/images/oil.png");
        player = ImageLoader.loadImage("/images/character_black_red.png");
        
        explosion = new BufferedImage[5];
        explosion[0] = ImageLoader.loadImage("/images/Explosion/explosion1.png");
        explosion[1] = ImageLoader.loadImage("/images/Explosion/explosion2.png");
        explosion[2] = ImageLoader.loadImage("/images/Explosion/explosion3.png");
        explosion[3] = ImageLoader.loadImage("/images/Explosion/explosion4.png");
        explosion[4] = ImageLoader.loadImage("/images/Explosion/explosion5.png");
        
        // Assign different menu screens
        menu = new BufferedImage[6];
        menu[0] = ImageLoader.loadImage("/images/menu/end.jpg");
        menu[1] = ImageLoader.loadImage("/images/menu/inicio.jpg");
        menu[2] = ImageLoader.loadImage("/images/menu/instructions.jpg");
        menu[3] = ImageLoader.loadImage("/images/menu/loser.jpg");
        menu[4] = ImageLoader.loadImage("/images/menu/pause.jpg");
        menu[5] = ImageLoader.loadImage("/images/menu/winner.jpg");
        
        tree = ImageLoader.loadImage("/images/tree_small.png");
    }
    
}
