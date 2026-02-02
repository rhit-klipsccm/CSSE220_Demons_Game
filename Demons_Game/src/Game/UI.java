package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;


public class UI {
    private int lives;
    private int score;
    private int totalGems;


    public UI(int lives, int score, int totalGems) {
        this.lives = lives;
        this.score = score;
        this.totalGems = totalGems;
    }

    
    public void setLives(int lives) {
        this.lives = lives;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setTotalGems(int totalGems) {
        this.totalGems = totalGems;
    }



    //draw the UI
    public void draw(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 20));

        g2.drawString("Lives: " + lives, 20, 30);
        g2.drawString("Score: " + score + " / " + totalGems, 20, 60);

        g2.drawString("Exit", 900, 30);

        g2.setFont(new Font("Arial", Font.PLAIN, 16));
        g2.drawString("WASD to move, SPACE to catapult zombies", 300, 680);
    }
}