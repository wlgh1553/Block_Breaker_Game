package blockbreaker.model;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Point;

public class Racket extends GameComponent {
	private double vx, speed;

	public Racket(Point midPos, int width, int height) {
		super(new BallDetector());

		super.position = new PrecisePoint(midPos.x, midPos.y);
		super.halfWidth = width / 2;
		super.halfHeight = height / 2;
		super.color = new Color(150, 100, 100);

		vx = 0;
		speed = 500;
	}

	public void changeDirection(int sign) {
		vx = speed * sign;
	}

	@Override
	public void draw(Graphics2D g) {
		Color startColor = new Color(248, 196, 196);
		Color endColor = new Color(63, 44, 44);
		GradientPaint gradient = new GradientPaint(0, (int) (position.y - halfHeight), startColor, 0,
				(int) (position.y + halfHeight), endColor);
		g.setPaint(gradient);
		g.fillRect((int) (position.x - halfWidth), (int) (position.y - halfHeight), (int) (2 * halfWidth),
				(int) (2 * halfHeight));

		int blinkSize = 3;
		g.setColor(super.color);
		g.fillRect((int) (position.x - halfWidth + blinkSize), (int) (position.y - halfHeight + blinkSize),
				(int) (2 * (halfWidth - blinkSize)), (int) (2 * (halfHeight - blinkSize)));

	}

	@Override
	public void update(double dt) {
		super.position.x += vx * dt;
	}

	@Override
	public void resolve(ComponentsManager manager) {
		if (super.position.x + super.halfWidth >= 800 - 34) {
			super.position.x = 800 - 34 - super.halfWidth;
		} else if (super.position.x - super.halfWidth <= 20) {
			super.position.x = 20 + super.halfWidth;
		}
	}

	@Override
	public void playCollisionSound() {
		super.playSound("sound/라켓부딪침.wav");
	}
}
