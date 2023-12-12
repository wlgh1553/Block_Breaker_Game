package blockbreaker.view.screen;

import javax.swing.JLabel;

public class PlayScreen extends Screen implements Runnable {
	public PlayScreen() {
		super();
	}

	@Override
	protected void initScreen() {
		JLabel l = new JLabel("game playing!");
		add(l);
	}

	@Override
	public void run() {
		// game loop
		while (true) {
			// 1. update

			// 2. resolve
			// 3. render
		}
	}
}
