package Game;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Graphics2D;


public class Item {
	
	private int x, y, width, height;
	private static BufferedImage sprite = null;
	private static boolean triedLoad = false;
	private boolean collected = false;
	
	/**
	 * Creates a collectible item at a given coordinate location
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Item(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		loadSpriteOnce();
	}
	
	private static void loadSpriteOnce() {
		if (triedLoad)
			return;
		triedLoad = true;
		try {
			sprite = ImageIO.read(Player.class.getResource("/Game/CharacterSprites/ItemSprites/star.png"));
		} catch (IOException | IllegalArgumentException ex) {
			sprite = null;
		}

	}

	/**
	 * Returns the boundaries/hitbox of the item
	 * @return
	 */
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(this.getX(), this.getY(), width, height);
	}

	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		if (collected) return;
		
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
	
	public int getY() {
		return y;
	}
	
	public boolean isCollected() {
	    return collected;
	}

	/**
	 * Removes item from the screen while giving it to the player/marking it
	 * as collected
	 */
	public void collect() {
	    collected = true;
	}
	
	

}
