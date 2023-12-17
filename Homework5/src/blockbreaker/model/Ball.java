package blockbreaker.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Ball extends GameComponent {
	public Ball(Point position, int radius) {
		super(new BallIgnorer(), new MovingAction(10)); // 스피드 임시
		super.position = position;
		super.halfWidth = radius;
		super.halfHeight = radius;
		super.color = Color.white;
	}

	public int getRadius() {
		return super.halfHeight;
	}

	public Point getPoint() {
		return position;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(super.color);
		g.fillOval(position.x - halfWidth, position.y - halfHeight, 2 * halfWidth, 2 * halfHeight);
	}

	@Override
	public void update(double dt) {
		super.actionManager.update(super.position, dt);
	}
}
