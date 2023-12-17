package blockbreaker.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

//너무 마음에 안드는데 일단은!
class CollisionBoundary {
	private int xmin, ymin, xmax, ymax;

	public CollisionBoundary(int ballRadius, Point pos, int w, int h) {
		xmin = pos.x - ballRadius;
		xmax = pos.x + w + ballRadius;
		ymin = pos.y - ballRadius;
		ymax = pos.y + h + ballRadius;
	}

	public boolean checkCollision(Point ball) {
		return ball.x > xmin && ball.x < xmax && ball.y > ymin && ball.y > ymax;
	}

	public int getXmin() {
		return xmin;
	}

	public int getXmax() {
		return xmax;
	}

	public int getYmin() {
		return ymin;
	}

	public int getYmax() {
		return ymax;
	}
}

interface BallDetectable {
	public boolean isCollision(Ball ball, Point myPos, int w, int h);

	CollisionBoundary getCollisionBoundary();
}

class BallDetector implements BallDetectable { // wall, block, racket
	CollisionBoundary boundary;

	@Override
	public boolean isCollision(Ball ball, Point myPos, int w, int h) {
		boundary = new CollisionBoundary(ball.getRadius(), myPos, w, h);
		return boundary.checkCollision(ball.getPoint());
	}

	@Override
	public CollisionBoundary getCollisionBoundary() {
		return boundary;
	}

}

class BallIgnorer implements BallDetectable {

	@Override
	public boolean isCollision(Ball ball, Point myPos, int w, int h) {
		return false;
	}

	@Override
	public CollisionBoundary getCollisionBoundary() {
		return null;
	}

}

interface Actionable {
	public void update(double dt);

	public void resolve();
}

class StaticAction implements Actionable { // wall

	@Override
	public void update(double dt) {
	}

	@Override
	public void resolve() {
	}

}

class AnimatedAction implements Actionable { // block
	@Override
	public void update(double dt) {
	}

	@Override
	public void resolve() {
	}
}

class MovingAction implements Actionable { // ball, racket
	@Override
	public void update(double dt) {
	}

	@Override
	public void resolve() {
	}
}

public abstract class GameComponent {
	protected Point position; // 네모든 원이든 중심 위치로!
	protected int halfWidth, halfHeight; //
	protected Color color;
	private BallDetectable collisionManager;
	private Actionable actionManager;

	GameComponent(BallDetectable collisionManager, Actionable actionManager) {
		this.collisionManager = collisionManager;
		this.actionManager = actionManager;
	}

	abstract public void draw(Graphics2D g);
}
