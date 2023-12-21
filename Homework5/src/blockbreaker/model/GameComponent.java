package blockbreaker.model;

import java.awt.Color;
import java.awt.Graphics2D;

//너무 마음에 안드는데 일단은!
class CollisionBoundary {
	private double xmin, ymin, xmax, ymax;

	public CollisionBoundary(double ballRadius, PrecisePoint pos, double hw, double hh) {
		xmin = pos.x - hw - ballRadius;
		xmax = pos.x + hw + ballRadius;
		ymin = pos.y - hh - ballRadius;
		ymax = pos.y + hh + ballRadius;
	}

	public boolean checkCollision(PrecisePoint ball) {
		return ball.x >= xmin && ball.x <= xmax && ball.y >= ymin && ball.y <= ymax;
	}

	public double getXmin() {
		return xmin;
	}

	public double getXmax() {
		return xmax;
	}

	public double getYmin() {
		return ymin;
	}

	public double getYmax() {
		return ymax;
	}
}

interface BallDetectable {
	public boolean isCollision(Ball ball, PrecisePoint precisePoint, double hw, double hh);

	CollisionBoundary getCollisionBoundary();
}

class BallDetector implements BallDetectable { // wall, block, racket
	CollisionBoundary boundary;
	boolean isCrashed = false;

	@Override
	public boolean isCollision(Ball ball, PrecisePoint myPos, double hw, double hh) {
		boundary = new CollisionBoundary(ball.getRadius(), myPos, hw, hh);
		isCrashed = boundary.checkCollision(ball.getPoint());
		return isCrashed;
	}

	@Override
	public CollisionBoundary getCollisionBoundary() {
		return boundary;
	}

}

class BallIgnorer implements BallDetectable {

	@Override
	public boolean isCollision(Ball ball, PrecisePoint myPos, double hw, double hh) {
		return false;
	}

	@Override
	public CollisionBoundary getCollisionBoundary() {
		return null;
	}

}

class PrecisePoint {
	double x;
	double y;

	PrecisePoint(double _x, double _y) {
		x = _x;
		y = _y;
	}
}

public abstract class GameComponent {
	protected PrecisePoint position; // 네모든 원이든 중심 위치로!
	protected double halfWidth, halfHeight;
	protected Color color;
	protected BallDetectable collisionManager;

	GameComponent(BallDetectable collisionManager) {
		this.collisionManager = collisionManager;
	}

	abstract public void draw(Graphics2D g);

	abstract public void update(double dt);

	abstract public void resolve(ComponentsManager manager);

	PrecisePoint getPoint() {
		return position;
	}

	double getHalfHeight() {
		return halfHeight;
	}

	double getHalfWidth() {
		return halfWidth;
	}
}
