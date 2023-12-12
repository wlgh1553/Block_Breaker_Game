package blockbreaker.model.component.ball;

import java.awt.Graphics;

import blockbreaker.model.component.Component;

public class Ball extends Component {
	// 리팩토링 필요
	private double x, y;
	private final double radius;
	private double vx, vy;
	private double prevX, prevY;

	public Ball(double _x, double _y, double _r, double _vx, double _vy) {
		x = _x;
		y = _y;
		radius = _r;
		vx = _vx;
		vy = _vy;
		prevX = x;
		prevY = y;
	}

	@Override
	public void draw(Graphics g) {
		g.fillOval((int) (x - radius), (int) (y - radius), (int) (radius * 2), (int) (radius * 2));
	}

	public void update(double dt) {
		prevX = x;
		prevY = y;
		x += vx * dt;
		y += vy * dt;
	}

	public void resolve(Component o) {

	}

}
