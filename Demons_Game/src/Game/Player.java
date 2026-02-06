package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Player implements Collidable, Sprites {
	private int x, y, width, height;
	private int dx = 1; // direction + speed, 4 pixels per move
	private int dy = 1; // direction + speed
	private static BufferedImage sprite = null;
	private static boolean triedLoad = false;
	private int health;

	public Player(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width=width;
		this.height=height;
		this.health=3;
		loadSpriteOnce();
	}
	
	public void subtractHealth() {
		this.health--;
	}
	
	public void subtractHealth(int health) {
		this.health-=health;
	}
	
	public void addHealth() {
		this.health++;
	}
	
	public void addHealth(int health) {
		this.health+=health;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	private static void loadSpriteOnce() {
		if (triedLoad)
			return;
		triedLoad = true;
		try {
			// tennis.png must be in the SAME package as Ball.java
			sprite = ImageIO.read(Player.class.getResource("/Game/CharacterSprites/BoyPNGS/Idle (1).png"));
		} catch (IOException | IllegalArgumentException ex) {
			sprite = null;
		}

	}

	public void draw(Graphics2D g2) {
		

		if (sprite != null) {
			// sprite replaces the circle
			g2.drawImage(sprite, x, y, width, height, null);
//			System.out.println("hey");
		} else {
			// fallback if sprite failed to load
			g2.setColor(Color.RED);
			g2.fillOval(x, y, width, height);
		}
	}

	

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public void update(int worldWidth, int worldHeight) {
		// move first
		x += dx;
		y += dy;

		// Left wall
		if (x < 0) {
			x = 0; // clamp
			dx = -dx;
		}
		// Right wall
		else if (x + width > worldWidth) {
			x = worldWidth - width;
			dx = -dx;
		}

		// Top wall
		if (y < 0) {
			y = 0;
			dy = -dy;
		}
		// Bottom wall
		else if (y + height > worldHeight) {
			y = worldHeight - height;
			dy = -dy;
		}
	}

	public Rectangle getBounds() {
	    return new Rectangle(this.getX(), this.getY(), width, height);
	}
	
	
    public void move(int dx, int dy, Map map) {
        Rectangle next = new Rectangle(x + dx, y + dy, width, height);
        if (map.canMove(next)) {
            x += dx;
            y += dy;
        }
    }

	@Override
	public void setAction(String actionName) {
		// TODO Auto-generated method stub
		
	}

}
