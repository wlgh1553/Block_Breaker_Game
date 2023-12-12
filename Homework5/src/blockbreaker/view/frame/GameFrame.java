package blockbreaker.view.frame;

import javax.swing.JFrame;

import blockbreaker.controller.ScreenController;

public class GameFrame extends JFrame {

	public GameFrame(ScreenController screenController) {
		setTitle("Java Homework4");
		setSize(800, 800);
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		screenController.showScreen(this);

		setVisible(true);
	}
}
