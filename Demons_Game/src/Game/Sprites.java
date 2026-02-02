package Game;

import java.awt.Graphics2D;

public interface Sprites {
    void draw(Graphics2D g2);// this is for painting the frame
    void setAction(String actionName); // this is for changing the motion like walk and dead
}