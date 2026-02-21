package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * Hostile enemy entity that moves around the map to collide with players and
 * damage the player
 */
public class Zombie implements Sprites {
	private int x, y, width, height;
	private int step = 20;
	private Direction direction = Direction.UP;
	private int stepsRemaining = 0;
	private static BufferedImage sprite;
	private static boolean triedLoad;
	private static final Random random = new Random();

	/**
	 * Creates zombie at the given coordinates on the map
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Zombie(int x, int y, int width, int height) {
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
			// tennis.png must be in the SAME package as Ball.java
			sprite = ImageIO.read(Player.class.getResource("/Game/CharacterSprites/ZombiePNGS/EvilTHING.png"));
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

	/**
	 * Updates zombie's position based on the random movement generator which
	 * assigns a direction.
	 * 
	 * @param map
	 */
	public void update(Map map) {
		if (stepsRemaining <= 0) {
			direction = Direction.values()[random.nextInt(4)];
			stepsRemaining = random.nextInt(30) + 10;
		}

		Rectangle next = new Rectangle(x + direction.dx * step, y + direction.dy * step, width, height);

		if (map.canMove(next)) {
			x = next.x;
			y = next.y;
			stepsRemaining--;
		} else {
			direction = direction.opposite();
			stepsRemaining = 0;
		}
	}

	public Rectangle getBounds() {
		return new Rectangle(this.getX(), this.getY(), width, height);
	}

	@Override
	public void setAction(String actionName) {
		// TODO Auto-generated method stub

	}

}
