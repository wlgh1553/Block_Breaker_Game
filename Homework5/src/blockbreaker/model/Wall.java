package blockbreaker.model;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Point;

public class Wall extends GameComponent {

	public Wall(Point startPos, int width, int height) {
		super(new BallDetector(), new StaticAction());
		super.position = new Point(startPos.x + width / 2, startPos.y + height / 2);
		super.halfWidth = width / 2;
		super.halfHeight = height / 2;
		super.color = Color.gray;
	}

	@Override
	public void draw(Graphics2D g) {
		Color startColor = super.color.brighter();
		Color endColor = super.color.darker();
		GradientPaint gradient = new GradientPaint(0, 0, startColor, 0, super.halfHeight * 2, endColor);
		g.setPaint(gradient);
		g.fillRect(position.x - halfWidth, position.y - halfHeight, 2 * halfWidth, 2 * halfHeight);

		int blinkSize = 3;
		g.setColor(super.color);
		g.fillRect(position.x - halfWidth + blinkSize, position.y - halfHeight + blinkSize,
				2 * halfWidth - blinkSize * 2, 2 * halfHeight - blinkSize * 2);
	}

}
