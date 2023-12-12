package blockbreaker.controller;

import javax.swing.JFrame;

import blockbreaker.view.screen.PlayScreen;
import blockbreaker.view.screen.Screen;

public class ScreenController {
	private Screen screen;

	public ScreenController() {
		screen = (Screen) new PlayScreen();
	}

	public void showScreen(JFrame frame) {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(screen);
		frame.revalidate();
		frame.repaint();
	}
}
