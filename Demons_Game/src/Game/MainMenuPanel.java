package Game;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;

public class MainMenuPanel extends JPanel{
	public MainMenuPanel(MyApp app) {
		setPreferredSize(new Dimension(1000, 700));
		setBackground(Color.BLACK);
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
}
