package blockbreaker.model.components;

import java.awt.Graphics;
import java.util.LinkedList;

import blockbreaker.model.component.Ball;
import blockbreaker.model.component.DetectableCollision;

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

	public void resolve(LinkedList<DetectableCollision> dtc) {
		for (DetectableCollision d : dtc) {
			for (Ball b : balls) {
				b.resolve(d);
			}
		}
	}
}
