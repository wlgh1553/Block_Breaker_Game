package blockbreaker.model;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.LinkedList;

public class Wall extends GameComponent {

	public Wall(Point startPos, int width, int height) {
		super(new BallDetector());
		super.position = new PrecisePoint(startPos.x + width / 2, startPos.y + height / 2);
		super.halfWidth = width / 2;
		super.halfHeight = height / 2;
		super.color = Color.gray;
	}

	@Override
	public void draw(Graphics2D g) {
		Color startColor = super.color.brighter();
		Color endColor = super.color.darker();
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

	}

	@Override
	public void resolve(LinkedList<GameComponent> others) {

	}

}
