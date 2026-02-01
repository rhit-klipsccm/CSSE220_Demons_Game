package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
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
	// ✅ sprite cache (shared by ALL balls)
	private static BufferedImage sprite = null;
	private static boolean triedLoad = false;

	public Player(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width=width;
		this.height=height;
		loadSpriteOnce();
	}

	private static void loadSpriteOnce() {
		if (triedLoad)
			return;
		triedLoad = true;
		try {
			// tennis.png must be in the SAME package as Ball.java
			sprite = ImageIO.read(Player.class.getResource("Game.CharacterSprites.BoyPNGS/Idle (1)"));
//			System.out.println("hey");
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

	public void move() {
		x += dx;
		y += dy;
	}

	public void flip() {
		dx = -dx;
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
}
