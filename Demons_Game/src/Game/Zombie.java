package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import Game.DrawingComponent.Direction;

public class Zombie implements Sprites {
	 private int x, y, width, height;
	    private int step = 10;
	    private Direction direction = Direction.UP;
	    private int stepsRemaining = 0;
	    private static BufferedImage sprite;
	    private static boolean triedLoad;
	    private static final Random random = new Random();
	    
	public Zombie(int x, int y, int width, int height) {
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
			sprite = ImageIO.read(Player.class.getResource("/Game/CharacterSprites/ZombiePNGS/Idle (1).png"));
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

//	@Override
//	public void update(int worldWidth, int worldHeight) {
//		// move first
//		x += dx;
//		y += dy;
//
//		// Left wall
//		if (x < 0) {
//			x = 0; // clamp
//			dx = -dx;
//		}
//		// Right wall
//		else if (x + width > worldWidth) {
//			x = worldWidth - width;
//			dx = -dx;
//		}
//
//		// Top wall
//		if (y < 0) {
//			y = 0;
//			dy = -dy;
//		}
//		// Bottom wall
//		else if (y + height > worldHeight) {
//			y = worldHeight - height;
//			dy = -dy;
//		}
//	}
//	
//	public void moveZombie(Direction direction) {
//		this.setX(this.getX() + direction.dx*step);
//		this.setY(this.getY() + direction.dy*step);
//	}
//	
//	private Direction currentZombieDirection = Direction.UP;
//	private int stepsRemaining = 0;
//	public void zombieRandomMovement() {
//		Random random = new Random();
//		if (stepsRemaining <= 0) {
//			currentZombieDirection = Direction.values()[random.nextInt(4)];
//			stepsRemaining = random.nextInt(20) + random.nextInt(10);
//		}
//		
//		moveZombie(currentZombieDirection);
//		stepsRemaining--;
//	}
	
	 public void update(Map map) {
	        if (stepsRemaining <= 0) {
	            direction = Direction.values()[random.nextInt(4)];
	            stepsRemaining = random.nextInt(30) + 10;
	        }

	        Rectangle next = new Rectangle(
	            x + direction.dx * step,
	            y + direction.dy * step,
	            width,
	            height
	        );

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
	
	 // collision checking in the corners (hopefully)
 	public boolean canMove(Rectangle bounds, Map map) {
 		int left = bounds.x;
 		int right  = bounds.x + bounds.width - 1;
 	    int top    = bounds.y;
 	    int bottom = bounds.y + bounds.height - 1;
 	    
 	    int leftCol   = left / Block.SIZE;
 	    int rightCol  = right / Block.SIZE;
 	    int topRow    = top / Block.SIZE;
 	    int bottomRow = bottom / Block.SIZE;
 	    
 	    return (map.isWalkable(topRow, leftCol) && map.isWalkable(topRow, rightCol) && map.isWalkable(bottomRow, leftCol) && map.isWalkable(bottomRow, rightCol));
 	    
 	}
}
