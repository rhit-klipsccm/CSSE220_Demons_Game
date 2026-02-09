package Game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class DrawingComponent extends JPanel {
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 700;

	// DrawingComponent fields (example)
	private int start_x = 250;
	private int start_y = 250;
	private int x = start_x;
	private int y = 20;
	private int step = 10;
	private Timer timer;
	private Map map = new Map();
	private ArrayList<Zombie> zombies = new ArrayList<>();
	private ArrayList<Item> items = new ArrayList<>();
	private Player player;
	private UI ui;
	private int playerLives = 3;
	private boolean gameOver = false;
	private int score = 0;
	private int totalStars = 0;
	
	private Rectangle restartButton;
	// more "dynamically" assign the player's and zombie's size now, changeable with
	// block fields now, should scale better?
//		private Player player = new Player(
//			    map.getPlayerStartX(),
//			    map.getPlayerStartY(),
//			    Block.SIZE,
//			    Block.SIZE
//			);

//		private Zombie zombie = new Zombie(
//			    Block.SIZE * (map.getCols() - 2),
//			    Block.SIZE * 1,
//			    Block.SIZE,
//			    Block.SIZE
//			);
//	

	public DrawingComponent() {
		setBackground(Color.CYAN);
		setOpaque(true);
		setPreferredSize(new Dimension(map.getPixelWidth(), map.getPixelHeight()));

		initializeGame();

//	    player = new Player(
//	        map.getPlayerStartX(),
//	        map.getPlayerStartY(),
//	        Block.SIZE,
//	        Block.SIZE
//	    );
//	    
//	    for (int i = 0; i < map.getZombieSpawnCount(); i++) {
//	        zombies.add(new Zombie(
//	            map.getZombieSpawnX(i),
//	            map.getZombieSpawnY(i),
//	            Block.SIZE,
//	            Block.SIZE));
//	    }
//	    
//	    for (int i = 0; i < map.getItemSpawnCount(); i++) {
//	    	items.add(new Item(
//	    		map.getItemSpawnX(i),
//	    		map.getItemSpawnY(i),
//	    		Block.SIZE,
//	    		Block.SIZE));
//	    }
//	    
//	    ui = new UI(playerLives, score, totalStars);

		setFocusable(true);

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (!gameOver) {
					boolean moved = false;
					if (e.getKeyCode() == KeyEvent.VK_W) {
			            player.move(0, -10, map);
			            moved = true;
			        }
			        if (e.getKeyCode() == KeyEvent.VK_S) {
			            player.move(0, 10, map);
			            moved = true;
			        }
			        if (e.getKeyCode() == KeyEvent.VK_A) {
			            player.move(-10, 0, map);
			            moved = true;
			        }
			        if (e.getKeyCode() == KeyEvent.VK_D) {
			            player.move(10, 0, map);
			            moved = true;
			        }

			        if (moved) {
			            checkCollisions();
			            repaint();
			        }
				}
			}
		});

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (gameOver && restartButton != null) {
					if (restartButton.contains(e.getPoint())) {
						restartGame();
					}
				}
			}
		});

		timer = new Timer(50, e -> {
			if (!gameOver) { // Only update if game is not over
//				for (Zombie zombie : zombies) {
//					zombie.update(map);
//				}
//				
////				checkCollisions(); // Check for collisions
//				repaint();
			
		    for (Zombie zombie : zombies) {
		        zombie.update(map);
		    }
		    
		    checkCollisions();
		    
		    if (ui.isGameOver()) {
		        gameOver = true;
		        timer.stop();
		    }
		    else if (ui.isWin()) {
		    	timer.stop();
		    }
		    repaint();
			}
		});
		timer.start();

	}

	/**
	 * Reset all movements to starting point
	 */
	private void initializeGame() {
		player = new Player(map.getPlayerStartX(), map.getPlayerStartY(), Block.SIZE, Block.SIZE);

		zombies.clear();
		for (int i = 0; i < map.getZombieSpawnCount(); i++) {
			zombies.add(new Zombie(map.getZombieSpawnX(i), map.getZombieSpawnY(i), Block.SIZE, Block.SIZE));
		}
		
		items.clear();
		for (int i = 0; i < map.getItemSpawnCount(); i++) {
			items.add(new Item(map.getItemSpawnX(i), map.getItemSpawnY(i), Block.SIZE, Block.SIZE));
		}

		playerLives = 3;
		totalStars = items.size();
		score = 0;
		gameOver = false;
		
		ui = new UI(playerLives, score, totalStars);
		ui.updateLives(playerLives);
		ui.updateScore(score); 
	}

	/**
	 * Check for collisions between players and all zombies on the map, AND items
	 */
	private void checkCollisions() {
		Rectangle playerBounds = player.getBounds();

		for (Zombie zombie : zombies) {
			Rectangle zombieBounds = zombie.getBounds();
			

			if (playerBounds.intersects(zombieBounds)) {
			    if (player.canTakeDamage()) {
			        player.loseLife();
			        ui.updateLives(player.getLives());

			        if (player.getLives() <= 0) {
			            gameOver = true;
			            timer.stop();
			        }
			    }
			    break;
			}

			
		}
		int centerX = playerBounds.x + playerBounds.width / 2;
		int centerY = playerBounds.y + playerBounds.height / 2;
		Rectangle point = new Rectangle(centerX, centerY, 1, 1);

		for (Item item : items) {
		    if (point.intersects(item.getBounds()) && !item.isCollected()) {
		        item.collect();
		        score++;
		        ui.updateScore(score);
		        break;
		    }
		}
		
		
		
		if (map.isExit(player.getBounds())) {
//			if reach the total score, then win
		    ui.updateScore(totalStars);
		    timer.stop();
		}
		}

	/**
	 * subtracts health from player whenever called
	 */
	private void handlePlayerHit() {
		playerLives--;
		ui.setLives(playerLives);

		if (playerLives <= 0) {
			gameOver = true;
			timer.stop(); // Stop the game
		} else {
			// Respawn player at starting position
			player.setX(map.getPlayerStartX());
			player.setY(map.getPlayerStartY());
		}
	}

	/**
	 * Game restart code
	 */
	private void restartGame() {
		timer.stop();
		initializeGame();
		timer.start();
		requestFocusInWindow(); // Return focus to the panel for keyboard input
		repaint();
	}

	/**
	 * paint component method that is responsible for drawing what the user sees
	 * draws game ui, game over screen and restart button
	 * @param g
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		map.draw(g2);
		player.draw(g2);
		for (Zombie zombie : zombies) {
			zombie.draw(g2);
		}
		for (Item item : items) {
			item.draw(g2);
		}
		ui.draw(g2);

		if (gameOver) {
			g2.setColor(new Color(0, 0, 0, 150));
			g2.fillRect(0, 0, getWidth(), getHeight());

			g2.setColor(Color.RED);
			g2.setFont(new Font("Arial", Font.BOLD, 48));
			String gameOverText = "GAME OVER";
			int textWidth = g2.getFontMetrics().stringWidth(gameOverText);
			g2.drawString(gameOverText, (getWidth() - textWidth) / 2, getHeight() / 2);

			int buttonWidth = 200;
			int buttonHeight = 60;
			int buttonX = (getWidth() - buttonWidth) / 2;
			int buttonY = getHeight() / 2 + 20;
			restartButton = new Rectangle(buttonX, buttonY, buttonWidth, buttonHeight);

			g2.setColor(new Color(100, 200, 100));
			g2.fillRoundRect(buttonX, buttonY, buttonWidth, buttonHeight, 20, 20);

			g2.setColor(Color.WHITE);
			g2.setStroke(new BasicStroke(3));
			g2.drawRoundRect(buttonX, buttonY, buttonWidth, buttonHeight, 20, 20);

			g2.setFont(new Font("Arial", Font.BOLD, 24));
			String buttonText = "RESTART";
			int btnTextWidth = g2.getFontMetrics().stringWidth(buttonText);
			g2.drawString(buttonText, buttonX + (buttonWidth - btnTextWidth) / 2, buttonY + buttonHeight / 2 + 8);
			} 
		else if (ui.isWin()) {
			g2.setColor(Color.RED);
			g2.setFont(new Font("Arial", Font.BOLD, 48));
			String gameOverText = "YOU WIN !!!";
			int textWidth = g2.getFontMetrics().stringWidth(gameOverText);
			g2.drawString(gameOverText, (getWidth() - textWidth) / 2, getHeight() / 2);
		}

	}

	// TODO refactor player movement code!
//	public void moveUp() {
//		player.setY(player.getY()-step);
//		repaint();
//	}
//	
//	public void moveDown() {
//		player.setY(player.getY()+step);
//		repaint();
//	}
//	
//	public void moveLeft() {
//		player.setX(player.getX()-step);
//		repaint();
//	}
//
//	public void moveRight() {
//		player.setX(player.getX()+step);
//		repaint();
//	}
//
//	public void reset() {
//		x = start_x;
//		repaint();
//	}

	// TODO: refactor zombie random movement!
//	public void zombieMoveUp() {
//		zombie.setY(zombie.getY()-step);
//		repaint();
//	}
//	
//	public void zombieMoveDown() {
//		zombie.setY(zombie.getY()+step);
//		repaint();
//	}
//	
//	public void zombieMoveLeft() {
//		zombie.setX(zombie.getX()-step);
//		repaint();
//	}
//
//	public void zombieMoveRight() {
//		zombie.setX(zombie.getX()+step);
//		repaint();
//	}
//
//	public void zombieReset() {
//		x = start_x;
//		repaint();
//	}
//	
//	public void zombieRandomMove() {
//		ArrayList<String> directions = new ArrayList<String>();
//		directions.add("UP");
//		directions.add("DOWN");
//		directions.add("LEFT");
//		directions.add("RIGHT");
//		
//		Random random = new Random();
//		int randomIndex = random.nextInt(directions.size());
//		
//		String randomDirection = directions.get(randomIndex);
//		
//		if (randomDirection == "UP") zombieMoveUp();
//		else if (randomDirection == "DOWN") zombieMoveDown();
//		else if (randomDirection == "LEFT") zombieMoveLeft();
//		else if (randomDirection == "RIGHT") zombieMoveRight();
//		
//
//	}

}
