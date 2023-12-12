package blockbreaker.controller;

import javax.swing.JFrame;

import blockbreaker.view.Screen;
import blockbreaker.view.TitleScreen;

public class ScreenController {
	private Screen screen;

	public ScreenController() {
		screen = (Screen) new TitleScreen();
	}

	public void showScreen(JFrame frame) {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(screen);
		frame.revalidate();
		frame.repaint();
	}
}
