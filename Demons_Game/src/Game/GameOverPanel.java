package Game;

import javax.swing.*;
import java.awt.*;

/**
 * New panel that is displayed when the player dies/loses all lives
 * Provides restart button and return to menu button
 */
public class GameOverPanel extends JPanel {

	/**
	 * Initializes the panel with "GAME OVER" label and nav buttons
	 * @param app
	 */
    public GameOverPanel(MyApp app) {

        setPreferredSize(new Dimension(1000, 700));
        setBackground(Color.DARK_GRAY);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        JLabel label = new JLabel("GAME OVER");
        label.setFont(new Font("Arial", Font.BOLD, 48));
        label.setForeground(Color.RED);

        JButton replay = new JButton("Replay Level");
        JButton menu = new JButton("Return to Main Menu");

        replay.addActionListener(e -> {
            app.replayLevel();
        });

        menu.addActionListener(e -> {
            app.showMenu();
        });

        gbc.gridy = 0;
        add(label, gbc);

        gbc.gridy = 1;
        add(replay, gbc);

        gbc.gridy = 2;
        add(menu, gbc);
    }
}