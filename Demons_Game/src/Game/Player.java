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
	private long lastHitTime = 0;
	private static final long HIT_COOLDOWN = 1000;
	
	private static BufferedImage idleSprite;
	private static BufferedImage moveRightSprite;
	private static BufferedImage moveLeftSprite;
	private static BufferedImage dustSprite;

	private BufferedImage currentSprite;

	private boolean moving = false;

	public Player(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width=width;
		this.height=height;
		this.health=3;
		loadSpriteOnce();
	}

	private static void loadSpriteOnce() {
	    if (triedLoad) return;
	    triedLoad = true;

	    try {
	        idleSprite = ImageIO.read(Player.class.getResource("/Game/CharacterSprites/BoyPNGS/CharacterStanding.png"));
	        moveRightSprite = ImageIO.read(Player.class.getResource("/Game/CharacterSprites/BoyPNGS/CharacterMovingRight.png"));
	        moveLeftSprite = ImageIO.read(Player.class.getResource("/Game/CharacterSprites/BoyPNGS/CharacterMovingLeft.png"));
	        dustSprite = ImageIO.read(Player.class.getResource("/Game/CharacterSprites/Dust.png"));
	    } catch (IOException | IllegalArgumentException e) {
	        return;
	    }
	}

	public void draw(Graphics2D g2) {
	    if (currentSprite == null) {
	        currentSprite = idleSprite;
	    }

	    g2.drawImage(currentSprite, x, y, width, height, null);

	    // Draw dust when moving
	    if (moving && dustSprite != null) {
	        int dustX = x - width / 4;
	        int dustY = y + height - height / 4;
	        g2.drawImage(dustSprite, dustX, dustY, width / 2, height / 2, null);
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

	        moving = true;

	        if (dx > 0) {
	            currentSprite = moveRightSprite;
	        } 
	        else if (dx < 0) {
	            currentSprite = moveLeftSprite;
	        }
	    } 
	    else {
	        moving = false;
	    }
	}
	
	public void setIdle() {
	    if (!moving) {
	        currentSprite = idleSprite;
	    }
	    moving = false; 
	}
    
    
    public int getLives() {
        return health;
    }
    
    public boolean canTakeDamage() {
        return System.currentTimeMillis() - lastHitTime >= HIT_COOLDOWN;
    }
    
    public void loseLife() {
        health--;
        lastHitTime = System.currentTimeMillis();
    }
    
    
    
	@Override
	public void setAction(String actionName) {
		// TODO Auto-generated method stub
		
	}

}
