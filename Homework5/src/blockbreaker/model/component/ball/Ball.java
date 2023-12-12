package blockbreaker.model.component.ball;

import java.awt.Color;
import java.awt.Graphics;

import blockbreaker.model.component.Component;
import blockbreaker.model.component.wall.Wall;
import blockbreaker.model.component.wall.Walls;

public class Ball extends Component {
	// 리팩토링 필요
	private double x, y;
	private final double radius;
	private double vx, vy;
	private double prevX, prevY;

	public Ball(double _x, double _y, double _vx, double _vy) {
		x = _x;
		y = _y;
		radius = 5.0;
		vx = _vx;
		vy = _vy;
		prevX = x;
		prevY = y;
	}

	public static Ball getInitialBall() {
		double angle = Math.random() * 3.141592 * 2;
		double speed = 400; // 레벨별로 수정하기
		double vx = Math.cos(angle) * speed;
		double vy = -Math.abs(Math.sin(angle) * speed);
		return new Ball(400, 700, vx, vy);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval((int) (x - radius), (int) (y - radius), (int) (radius * 2), (int) (radius * 2));
	}

	@Override
	public void update(double dt) {
		prevX = x;
		prevY = y;
		x += vx * dt;
		y += vy * dt;
	}

	// 더 좋은 방법을 고민해보자
	public void resolve(Walls walls) {
		for (Wall w : walls.walls) {
			checkCollision(w);
		}
	}

	public void checkCollision(Wall wall) {
		if (!wall.isCollision(x, y, radius))
			return;

		// 더 좋은 방법을 고민해보자.
		if (prevX < wall.xmin) {
			vx = -vx;
			x = wall.xmin;
		}
		if (prevX > wall.xmax) {
			vx = -vx;
			x = wall.xmax;
		}
		if (prevY < wall.ymin) {
			vy = -vy;
			y = wall.ymin;
		}
		if (prevY > wall.ymax) {
			vy = -vy;
			y = wall.ymax;
		}

	}

}
