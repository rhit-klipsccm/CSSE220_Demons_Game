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
	
	

	// DrawingComponent fields (example)
	private int start_x = 250;
	private int start_y = 250;
	private int x = start_x;
	private int y = 20;
	private int step = 10;
	private Timer timer;
	private Map map = new Map();
	private ArrayList<Zombie> zombies = new ArrayList<>();

	// more "dynamically" assign the player's and zombie's size now, changeable with block fields now, should scale better?
		private Player player = new Player(
			    Block.SIZE * 1,
			    Block.SIZE * 1,
			    Block.SIZE,
			    Block.SIZE
			);

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
		// possibly temporary, trying to get the map to fit in frame
		setPreferredSize(new Dimension(
			    map.getPixelWidth(),
			    map.getPixelHeight()
			));
		
		player = new Player(start_x, start_y, Block.SIZE, Block.SIZE);
		for (int i = 0; i < map.getZombieSpawnCount(); i++) {
		    zombies.add(new Zombie(
		        map.getZombieSpawnX(i),
		        map.getZombieSpawnY(i),
		        Block.SIZE,
		        Block.SIZE));
		}
		
		
		setFocusable(true);
		
		addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_W) player.move(0, -10, map);
                if (e.getKeyCode() == KeyEvent.VK_S) player.move(0, 10, map);
                if (e.getKeyCode() == KeyEvent.VK_A) player.move(-10, 0, map);
                if (e.getKeyCode() == KeyEvent.VK_D) player.move(10, 0, map);
                repaint();
            }
        });
		
		
		
		timer = new Timer(50, e -> {
		for (Zombie zombie : zombies) {
			zombie.update(map);
		}
		repaint();
		});
		timer.start();
			
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		map.draw(g2);
		player.draw(g2);
		for (Zombie zombie : zombies) {
		    zombie.draw(g2);
		}
//		setFocusable(true);

	}
	
	
	
	
	//TODO refactor player movement code!
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
	
	
	
	
	
	
	
	
	
}

