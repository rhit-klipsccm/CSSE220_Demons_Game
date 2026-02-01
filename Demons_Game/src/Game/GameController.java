package Game;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;



public class GameController extends JPanel{
	private DrawingComponent drawing;
	public GameController() {
		setLayout(new BorderLayout());
		drawing=new DrawingComponent();
		add(drawing, BorderLayout.CENTER);
		
	}
}
