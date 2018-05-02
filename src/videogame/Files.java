/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hectordeluna
 */
public class Files {
    
    // Save File
    public static void saveFile(Game game) {
        // define objects
        PrintWriter printWriter;
        FileWriter fileWriter;
        try {
            
        
            // creating file object
            printWriter = new PrintWriter(new FileWriter("data.txt"));
            // writing the game
            printWriter.println(game.getLevel());
           
            printWriter.close();
            
        } catch (IOException ioe) {
            System.out.println("Se te lleno el disco duro");
        }
        
        
    }
    
    public static void loadFile(Game game) {
        BufferedReader bufferedReader;
        
        try {
            
            bufferedReader = new BufferedReader(new FileReader("data.txt"));
            String line = bufferedReader.readLine();
            game.setLevel(Integer.parseInt(line));
            
            /*
            line = bufferedReader.readLine();
            tokens = line.split(",");
            game.setPoints(Integer.parseInt(tokens[0]));
            
            int enemies;
            enemies = Integer.parseInt(bufferedReader.readLine());
            
            // get enemies information
            game.getEnemies().clear();
            game.getBullets().clear();
            for (int i = 0; i < enemies; i++) {
                line = bufferedReader.readLine();
                tokens = line.split(",");
                int x = Integer.parseInt(tokens[0]);
                int y = Integer.parseInt(tokens[1]);
                int alto = Integer.parseInt(tokens[2]);
                int move = Integer.parseInt(tokens[3]);
                int count = Integer.parseInt(tokens[4]);
                Enemy enemy = new Enemy(x,y,game.getWidth() / 10 - 6,game.getHeight() / 3 / 5 - 10,game,move);
                enemy.setCount(count);
                game.getEnemies().add(enemy);
            }
            // get bullets informations
            int bullets = Integer.parseInt(bufferedReader.readLine());
            for (int i = 0; i < bullets; i++) {
                line = bufferedReader.readLine();
                tokens = line.split(",");
                int x = Integer.parseInt(tokens[0]);
                int y = Integer.parseInt(tokens[1]);
                
                Bullet bullet = new Bullet(x,y,20, 20,0,0,game);
                game.getBullets().add(bullet);
            }
            // get Enemies bullets informaiton
            game.getBulletsEnemies().clear();
            bullets = Integer.parseInt(bufferedReader.readLine());
            for (int i = 0; i < bullets; i++) {
               line = bufferedReader.readLine();
               tokens = line.split(",");
               int x = Integer.parseInt(tokens[0]);
               int y = Integer.parseInt(tokens[1]); 
               BulletEnemy bullet = new BulletEnemy(x,y,40, 40,0,0,game);
               game.getBulletsEnemies().add(bullet);
            }
            
            // get covers information
            game.getCovers().clear();
            int covernum = Integer.parseInt(bufferedReader.readLine());
            for (int i = 0; i < covernum; i++) {
               line = bufferedReader.readLine();
               tokens = line.split(",");
               int x = Integer.parseInt(tokens[0]);
               int y = Integer.parseInt(tokens[1]); 
               int health = Integer.parseInt(tokens[2]);
               Cover cover = new Cover(x,y, 200, 150, game);
               cover.setHealth(health);
               game.getCovers().add(cover);
            }
            */
            
        } catch (IOException ioe) {
           System.out.println("Juego no ha sido guardado " + ioe.toString());
        }

    }
    
}
