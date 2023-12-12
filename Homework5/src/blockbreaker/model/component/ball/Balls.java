package blockbreaker.model.component.ball;

import java.awt.Graphics;
import java.util.LinkedList;

import blockbreaker.model.component.wall.Walls;

public class Balls {
	private LinkedList<Ball> balls;

	public Balls() {
		balls = new LinkedList<>();
	}

	public void addBall(Ball ball) {
		balls.add(ball);
	}

	public void draw(Graphics g) {
		for (Ball b : balls) {
			b.draw(g);
		}
	}

	public void update() {
		for (Ball b : balls) {
			b.update(0.016); // 상수처리 해주자
		}
	}

	public void resolve(Walls walls) {
		for (Ball b : balls) {
			b.resolve(walls);
		}
	}
}
