package blockbreaker.model.component;

import java.awt.Color;
import java.awt.Graphics;

public class Ball implements Reactive {
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
		double angle = 4;
		double speed = 400; // 레벨별로 수정하기
		double vx = Math.cos(angle) * speed;
		double vy = Math.sin(angle) * speed;
		return new Ball(400, 700, vx, vy); // 위치는 상수로 만들기
	}

	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval((int) (x - radius), (int) (y - radius), (int) (radius * 2), (int) (radius * 2));
	}

	public void update(double dt) {
		prevX = x;
		prevY = y;
		x += vx * dt;
		y += vy * dt;
	}

	// 더 좋은 방법을 고민해보자
	public void resolve(DetectableCollision d) {
		checkCollision(d);
	}

	// 아... 어쩌지..
	@Override
	public void resolve() {
		// TODO Auto-generated method stub

	}

	public void checkCollision(DetectableCollision d) {
		if (!d.isCollision(x, y, radius))
			return;

		// 더 좋은 방법을 고민해보자.
		if (prevX < d.xmin) {
			vx = -vx;
			x = d.xmin;
		}
		if (prevX > d.xmax) {
			vx = -vx;
			x = d.xmax;
		}
		if (prevY < d.ymin) {
			vy = -vy;
			y = d.ymin;
		}
		if (prevY > d.ymax) {
			vy = -vy;
			y = d.ymax;
		}

	}
}
