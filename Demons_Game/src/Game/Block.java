package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;
import javax.imageio.ImageIO;



import java.awt.Color;
import java.awt.Graphics2D;



public class Block {

    public static final int SIZE = 40;
    private int type;
    public Block(int type) {
        this.type = type;
    }


    public boolean isWalkable() {
        return type != 0; //
    }// if it is not walkable, like the wall, it cannot be walk into. The floors and exit are walkable.


    
    // so in this case I wrote three kinds of blocks, 0 is the wall, 1 is the exit(if the player touch the exit, he just win), 2 is the floor
    public void draw(Graphics2D g2, int x, int y) {
        switch (type) {
            case 0 -> {
                g2.setColor(Color.DARK_GRAY);
                g2.fillRect(x, y, SIZE, SIZE);
            }
            case 1 -> {
                g2.setColor(Color.GREEN);
                g2.fillRect(x, y, SIZE, SIZE);
            }
            case 2 -> {
                g2.setColor(Color.LIGHT_GRAY);
                g2.fillRect(x, y, SIZE, SIZE);
            }
           
        }
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}









