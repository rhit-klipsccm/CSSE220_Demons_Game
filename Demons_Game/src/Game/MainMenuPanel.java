package Game;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;

/**
 * Starting screen of the game, includes level selector, displaying
 * gameplay instructions
 */
public class MainMenuPanel extends JPanel{
	/**
	 * Creates main menu with level selection buttons
	 * @param app
	 */
	public MainMenuPanel(MyApp app) {
		setPreferredSize(new Dimension(1000, 700));
		setBackground(new Color(96, 2, 140));
		setLayout(new GridBagLayout());
		
		JButton level1 = new JButton("Start Level 1");
		level1.addActionListener(e -> {
		    app.showGame("/Game/level.txt");
		});

		JButton level2 = new JButton("Start Level 2");
		level2.addActionListener(e -> {
		    app.showGame("/Game/level2.txt");
		});
		
		add(level1);
		add(level2);
	}
	
	/**
	 * Renders background elements and instructions text
	 */
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    Graphics2D g2 = (Graphics2D) g;
	    g2.setColor(Color.white);
	    g2.setFont(new Font("Arial", Font.BOLD, 18));
	    g2.drawString("WASD to move \n Two ways to win: Collect all the items, or make it to the green tile!", 150, 680);
	}
}
