package Game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class DrawingComponent extends JPanel {
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 700;
	private Player player = new Player(80, 100, 100, 110);
	private Zombie zombie = new Zombie (920,100,90,100);
	// DrawingComponent fields (example)
	private int start_x = 250;
	private int x = start_x;
	private int y = 20;
	private int step = 10;
	private Timer timer;

	public DrawingComponent() {
		setBackground(Color.CYAN);
		setOpaque(true);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                if (code == KeyEvent.VK_W) moveUp();
                if (code == KeyEvent.VK_S) moveDown();
                if (code == KeyEvent.VK_A) moveLeft();
                if (code == KeyEvent.VK_D) moveRight();
                if (code == KeyEvent.VK_R) reset();
            }
        });
		
		
		
		timer = new Timer(50, e -> {
			zombieRandomMovement();
		});
		timer.start();
			
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		player.draw(g2);
		zombie.draw(g2);
//		setFocusable(true);

	}
	
	
	//TODO refactor player movement code!
	public void moveUp() {
		player.setY(player.getY()-step);
		repaint();
	}
	
	public void moveDown() {
		player.setY(player.getY()+step);
		repaint();
	}
	
	public void moveLeft() {
		player.setX(player.getX()-step);
		repaint();
	}

	public void moveRight() {
		player.setX(player.getX()+step);
		repaint();
	}

	public void reset() {
		x = start_x;
		repaint();
	}
	
	//TODO: refactor zombie random movement!
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
	
	
	public enum Direction {
		UP(0, -1), DOWN(0, 1), LEFT(-1, 0), RIGHT(1, 0);
		
		public final int dx;
		public final int dy;
		
		Direction(int dx, int dy) {
			this.dx = dx;
			this.dy = dy;
		}
	}
	
	public void moveZombie(Direction direction) {
		zombie.setX(zombie.getX() + direction.dx*step);
		zombie.setY(zombie.getY() + direction.dy*step);
		repaint();
	}
	
	private Direction currentZombieDirection = Direction.UP;
	private int stepsRemaining = 0;
	public void zombieRandomMovement() {
		Random random = new Random();
		if (stepsRemaining <= 0) {
			currentZombieDirection = Direction.values()[random.nextInt(4)];
			stepsRemaining = random.nextInt(20) + random.nextInt(10);
		}
		
		moveZombie(currentZombieDirection);
		stepsRemaining--;
	}
	
	
	
	
}

