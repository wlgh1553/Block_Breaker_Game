package blockbreaker.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.LinkedList;

public class Ball extends GameComponent {
	private double prevx, prevy;
	private double nowx, nowy;
	private double vx, vy;

	public Ball(Point pos, int radius, double speed) {
		super(new BallIgnorer());

		super.position = new PrecisePoint(pos.x, pos.y);
		prevx = position.x;
		prevy = position.y;
		nowx = prevx;
		nowy = prevy;
		super.halfWidth = radius;
		super.halfHeight = radius;
		super.color = Color.white;

		double degree = Math.random() * 90 + 45;
		double angle = Math.toRadians(degree);
		vx = Math.cos(angle) * speed;
		vy = Math.sin(angle) * speed;
	}

	public int getRadius() {
		return super.halfHeight;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(super.color);
		g.fillOval((int) (position.x - halfWidth), (int) (position.y - halfHeight), 2 * halfWidth, 2 * halfHeight);
	}

	@Override
	public void update(double dt) {
		prevx = nowx;
		prevy = nowy;
		nowx += vx * dt;
		nowy += vy * dt;
		super.position.x = (int) nowx;
		super.position.y = (int) nowy;
	}

	@Override
	public void resolve(LinkedList<GameComponent> others) {
		for (GameComponent other : others) {
			if (other.collisionManager.isCollision(this, other.getPoint(), other.getHalfWidth(),
					other.getHalfHeight())) {
				CollisionBoundary boundary = other.collisionManager.getCollisionBoundary();

				if (prevx < boundary.getXmin()) {
					vx = -vx;
					position.x = boundary.getXmin();
				}
				if (prevx > boundary.getXmax()) {
					vx = -vx;
					position.x = boundary.getXmax();
				}
				if (prevy < boundary.getYmin()) {
					vy = -vy;
					position.y = boundary.getYmin();
				}
				if (prevy > boundary.getYmax()) {
					vy = -vy;
					position.y = boundary.getYmax();
				}
			}
		}
	}

}
