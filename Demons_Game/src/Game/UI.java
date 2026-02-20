package Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class UI {

    private int lives;
    private int score;
    private int totalStars;
    private boolean gameOver = false;
    private boolean win = false;

    private BufferedImage heart;

    public UI(int lives, int score, int totalStars) {
        this.lives = lives;
        this.score = score;
        this.totalStars = totalStars;

        loadImages();
    }


    
//    DRAWING the Heart(if need?)
    private void loadImages() {
        try {
            heart = ImageIO.read(getClass().getResource("/Game/CharacterSprites/UI/heart.png"));
        } catch (Exception e) {
            heart = null;
        }
    }


    // THE SETTERS
    public void setLives(int lives) {
        this.lives = lives;
        if (lives <= 0) gameOver = true;
    }

    public void setScore(int score) {
        this.score = score;
        if (score >= totalStars) win = true;
    }

    public void setTotalStars(int totalStars) {
        this.totalStars = totalStars;
    }


    public void updateLives(int lives) {
        this.lives = lives;
    	if (lives <= 0) {
            gameOver = true;
        }
    }
    
    public void updateScore(int score) {
        this.score = score;
        if (score >= totalStars) {
            win = true;
        }
    }
    
    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isWin() {
        return win;
    }
    
    
    
    
    
    
    
    // DRAW!!

    public void draw(Graphics2D g2) {

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 20));

        
        //Heart
        if (heart != null) {
            for (int i = 0; i < lives; i++) {
                g2.drawImage(heart, 20 + i * 40, 10, 30, 30, null);
            }
        } else {
            g2.drawString("Lives: " + lives, 20, 30);
        }

        
        
        
        //Score
        g2.drawString("Score: " + score + " / " + totalStars, 20, 60);

        
        
        //Exit(if need?)
//        g2.drawString("Exit", 900, 30);

        
        
       

        
        
        
//        //Game Over
//        if (gameOver) {
//            g2.setFont(new Font("Arial", Font.BOLD, 60));
//            g2.setColor(Color.RED);
//            g2.drawString("GAME OVER", 350, 350);
//        }
//  
//        //Win
//        if (win) {
//            g2.setFont(new Font("Arial", Font.BOLD, 60));
//            g2.setColor(Color.GREEN);
//            g2.drawString("YOU WIN!", 380, 350);
//        }
    }
}
