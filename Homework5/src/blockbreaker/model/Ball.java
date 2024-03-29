package blockbreaker.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Ball extends GameComponent {
	private double prevx, prevy;
	private double nowx, nowy;
	private double vx, vy;
	public static final int RADIUS = 5;

	public Ball(Point pos, int stage) {
		super(new BallIgnorer());

		initShapeInfo(pos.x, pos.y);

		double speed = (stage + 2) * 100;
		double degree = Math.random() * 90 + 45;
		double angle = Math.toRadians(degree);
		vx = Math.cos(angle) * speed;
		vy = Math.sin(angle) * speed;
	}

	public Ball(Ball core, double degree) {
		super(new BallIgnorer());

		initShapeInfo(core.position.x, core.position.y);

		double cos = Math.cos(Math.toRadians(degree));
		double sin = Math.sin(Math.toRadians(degree));
		vx = core.vx * cos - core.vy * sin;
		vy = core.vx * sin + core.vy * cos;
	}

	private void initShapeInfo(double x, double y) {
		super.position = new PrecisePoint(x, y);
		prevx = position.x;
		prevy = position.y;
		nowx = prevx;
		nowy = prevy;
		super.halfWidth = RADIUS;
		super.halfHeight = RADIUS;
		super.color = Color.white;
	}

	public double getRadius() {
		return super.halfHeight;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(super.color);
		g.fillOval((int) (position.x - halfWidth), (int) (position.y - halfHeight), (int) (2 * halfWidth),
				(int) (2 * halfHeight));
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
	public void resolve(ComponentsManager manager) {
		// 화면 밖으로 나갔는지 검사
		if (this.position.y > 800) {
			super.isAlive = false;
		}

		// 충돌 검사
		GameComponent eraseComponent = null;
		for (GameComponent other : manager.getComponents()) {
			if (other.collisionManager.isCollision(this, other.getPoint(), other.getHalfWidth(),
					other.getHalfHeight())) {

				other.playCollisionSound();

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

				if (other instanceof Block) {
					other.isAlive = false;
					Block b = (Block) other;
					b.affectBalls(manager, this);
				}

			}
		}
	}

	@Override
	public void playCollisionSound() {
		// nothing
	}

}
