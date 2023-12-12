package blockbreaker.view.screen;

import java.awt.Color;
import java.awt.Graphics;

import blockbreaker.model.component.ball.Ball;
import blockbreaker.model.component.ball.Balls;
import blockbreaker.model.component.wall.Walls;

public class PlayScreen extends Screen implements Runnable {
	private Walls walls;
	private Balls balls;

	public PlayScreen() {
		super();
		this.setBackground(Color.GRAY);
		walls = new Walls();
		balls = new Balls();
		balls.addBall(Ball.getInitialBall());

		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		walls.draw(g);
		balls.draw(g);
	}

	@Override
	public void run() {
		// game loop
		while (true) {
			// 1. update
			balls.update();

			// 2. resolve
			balls.resolve(walls);

			// 3. render
			repaint();

			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				return;
			}
		}
	}
}
