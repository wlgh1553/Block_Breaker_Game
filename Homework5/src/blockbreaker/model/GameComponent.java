package blockbreaker.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;

//너무 마음에 안드는데 일단은!
class CollisionBoundary {
	private double xmin, ymin, xmax, ymax;

	public CollisionBoundary(int ballRadius, PrecisePoint pos, int hw, int hh) {
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
	public boolean isCollision(Ball ball, PrecisePoint precisePoint, int hw, int hh);

	CollisionBoundary getCollisionBoundary();
}

class BallDetector implements BallDetectable { // wall, block, racket
	CollisionBoundary boundary;

	@Override
	public boolean isCollision(Ball ball, PrecisePoint myPos, int hw, int hh) {
		boundary = new CollisionBoundary(ball.getRadius(), myPos, hw, hh);
		return boundary.checkCollision(ball.getPoint());
	}

	@Override
	public CollisionBoundary getCollisionBoundary() {
		return boundary;
	}

}

class BallIgnorer implements BallDetectable {

	@Override
	public boolean isCollision(Ball ball, PrecisePoint myPos, int hw, int hh) {
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

	PrecisePoint(int _x, int _y) {
		x = _x;
		y = _y;
	}
}

public abstract class GameComponent {
	protected PrecisePoint position; // 네모든 원이든 중심 위치로!
	protected int halfWidth, halfHeight; //
	protected Color color;
	protected BallDetectable collisionManager;

	GameComponent(BallDetectable collisionManager) {
		this.collisionManager = collisionManager;
	}

	abstract public void draw(Graphics2D g);

	abstract public void update(double dt);

	abstract public void resolve(LinkedList<GameComponent> others);

	public PrecisePoint getPoint() {
		return position;
	}

	public int getHalfWidth() {
		return halfWidth;
	}

	public int getHalfHeight() {
		return halfHeight;
	}

}
