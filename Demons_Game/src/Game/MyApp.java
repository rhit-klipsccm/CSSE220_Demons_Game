package Game;

import java.awt.*;

import javax.swing.*;

/**
 * Main controller of the application Manages the CardLayout screens.
 */
public class MyApp {
	private JFrame frame;
	private JPanel container;
	private CardLayout cardLayout;

	public static String MENU = "Menu";
	public static String GAME = "Game";
	public static String GAME_OVER = "Game Over!";

	private DrawingComponent game;
	private String currentLevel;

	public MyApp() {
		frame = new JFrame(GAME);

		cardLayout = new CardLayout();
		container = new JPanel(cardLayout);

		MainMenuPanel menu = new MainMenuPanel(this);
		game = new DrawingComponent(this, null);
		GameOverPanel gameOver = new GameOverPanel(this);

		container.add(menu, MENU);
		container.add(game, GAME);
		container.add(gameOver, GAME_OVER);

		frame.setContentPane(container);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Switches the view to the main menu screen.
	 */
	public void showMenu() {
		cardLayout.show(container, MENU);
	}

	/**
	 * Loads and displays game panel with specified level file
	 * 
	 * @param levelFile
	 */
	public void showGame(String levelFile) {
		this.currentLevel = levelFile; // remember level
		game.loadLevel(levelFile);
		cardLayout.show(container, GAME);

		container.revalidate();
		container.doLayout();

		frame.pack();
		frame.setLocationRelativeTo(null);
		SwingUtilities.invokeLater(() -> game.requestFocusInWindow());
	}

	/**
	 * Switches to game over screen
	 */
	public void showGameOver() {
		cardLayout.show(container, GAME_OVER);
	}

	/**
	 * Restarts current level from the beginning
	 */
	public void replayLevel() {
		if (currentLevel != null) {
			game.loadLevel(currentLevel);
			cardLayout.show(container, GAME);
			game.requestFocusInWindow();
		}
	}

	public void run() {
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		showMenu();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			MyApp app = new MyApp();
			app.run();
		});
	}
}
