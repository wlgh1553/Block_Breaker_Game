package blockbreaker.controller;

import blockbreaker.view.screen.PlayScreen;
import blockbreaker.view.screen.Screen;

public class PlayController implements Runnable {
	private PlayScreen screen;
	private Thread gameThread;

	public PlayController(Screen screen) {
		this.screen = (PlayScreen) screen;
		gameThread = new Thread(this);
	}

	public void play() {
		gameThread.start();
	}

	@Override
	public void run() {
		// game loop
		while (true) {
			// 1. update
			screen.updateComponents(0.016);

			// 2. resolve
			screen.resolveComponents();

			// 3. render
			screen.repaint();

			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				return;
			}
		}
	}
}
