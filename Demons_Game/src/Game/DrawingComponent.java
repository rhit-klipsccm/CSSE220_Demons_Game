package Game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

public class DrawingComponent extends JPanel {
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 700;
	private Player player = new Player(80, 100, 50, 56);
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
//			player.update(WIDTH, HEIGHT);
//			repaint();
		});
		timer.start();
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		player.draw(g2);
//		setFocusable(true);

	}
	
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
}
