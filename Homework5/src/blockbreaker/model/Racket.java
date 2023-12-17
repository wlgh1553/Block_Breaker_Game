package blockbreaker.model;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Point;

public class Racket extends GameComponent {
	public Racket(Point midPos, int width, int height) {
		super(new BallDetector(), new MovingAction());
		super.position = midPos;
		super.halfWidth = width / 2;
		super.halfHeight = height / 2;
		super.color = new Color(150, 100, 100);
	}

	@Override
	public void draw(Graphics2D g) {
		Color startColor = new Color(248, 196, 196);
		Color endColor = new Color(63, 44, 44);
		GradientPaint gradient = new GradientPaint(0, position.y - halfHeight, startColor, 0, position.y + halfHeight,
				endColor);
		g.setPaint(gradient);
		g.fillRect(position.x - halfWidth, position.y - halfHeight, 2 * halfWidth, 2 * halfHeight);

		int blinkSize = 3;
		g.setColor(super.color);
		g.fillRect(position.x - halfWidth + blinkSize, position.y - halfHeight + blinkSize,
				2 * halfWidth - blinkSize * 2, 2 * halfHeight - blinkSize * 2);
	}
}
