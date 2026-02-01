package Game;

import java.awt.*;

import javax.swing.*;

public class MyApp {
	private JFrame frame;

	public MyApp() {
		frame = new JFrame("Game");
		frame.setContentPane(new GameController());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void run() {
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			MyApp app = new MyApp();
			app.run();
		});
	}
}
