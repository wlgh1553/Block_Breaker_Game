package blockbreaker.view.screen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import blockbreaker.model.component.DetectableCollision;
import blockbreaker.model.component.Racket;
import blockbreaker.model.component.ball.Ball;
import blockbreaker.model.component.ball.Balls;
import blockbreaker.model.component.wall.Walls;

public class PlayScreen extends Screen implements Runnable, KeyListener {
	private Walls walls;
	private Balls balls;
	private Racket racket;

	// 리팩토링 하자
	private LinkedList<DetectableCollision> dtc = new LinkedList<>();

	public PlayScreen() {
		super();
		this.setBackground(Color.GRAY);
		walls = new Walls();
		balls = new Balls();
		balls.addBall(Ball.getInitialBall());
		racket = new Racket();

		walls.addWalls(dtc);
		dtc.add(racket);

		Thread thread = new Thread(this);
		thread.start();

		this.addKeyListener(this);
		this.setFocusable(true);
		this.requestFocus();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		walls.draw(g);
		balls.draw(g);
		racket.draw(g);
	}

	@Override
	public void run() {
		// game loop
		while (true) {
			// 1. update
			balls.update();
			racket.update(0.016);

			// 2. resolve
			balls.resolve(dtc);
			racket.resolve();

			// 3. render
			repaint();

			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				return;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == e.VK_LEFT) {
			racket.moveLeft();
		}
		if (e.getKeyCode() == e.VK_RIGHT) {
			racket.moveRight();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		racket.stopMove();
	}
}
