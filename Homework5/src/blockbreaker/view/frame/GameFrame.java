package blockbreaker.view.frame;

import javax.swing.JFrame;

import blockbreaker.view.screen.Screen;

public class GameFrame extends JFrame {

	public GameFrame(Screen screen) {
		setTitle("Java Homework4");
		setSize(800, 800);
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.add(screen);

		setVisible(true);
	}
}
