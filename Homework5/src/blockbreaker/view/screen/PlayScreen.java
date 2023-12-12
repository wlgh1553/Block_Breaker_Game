package blockbreaker.view.screen;

import java.awt.Color;
import java.awt.Graphics;

import blockbreaker.model.component.wall.Walls;

public class PlayScreen extends Screen implements Runnable {
	private Walls walls;

	public PlayScreen() {
		super();
		this.setBackground(Color.GRAY);
	}

	@Override
	protected void initScreen() {
		walls = new Walls();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		walls.draw(g);
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
