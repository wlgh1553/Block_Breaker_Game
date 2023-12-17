package blockbreaker.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Ball extends GameComponent {
	public Ball(Point position, int radius) {
		super(new BallIgnorer(), new MovingAction());
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
	public void draw(Graphics g) {
		g.setColor(super.color);
		g.fillOval(position.x - halfWidth, position.y - halfHeight, halfWidth, halfHeight);
	}
}
