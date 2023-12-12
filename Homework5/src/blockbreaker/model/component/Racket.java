package blockbreaker.model.component;

import java.awt.Color;
import java.awt.Graphics;

public class Racket extends DetectableCollision implements Reactive {
	private int vx, speed;

	public Racket() {
		super(300, 703, 160, 30);
		vx = 0;
		speed = 400;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect((int) x, (int) y, width, height);
	}

	@Override
	public void update(double dt) {
		x += (double) vx * dt;
	}

	@Override
	public void resolve() {
		if (x > 766 - this.width) {
			x = 766 - this.width;
			vx = 0;
		} // 밖으로 나가지 못하도록
		if (x < 20) {
			x = 20;
			vx = 0;
		}
	}

	public void moveLeft() {
		this.vx = -speed;
	}

	public void moveRight() {
		this.vx = speed;
	}

	public void stopMove() {
		this.vx = 0;
	}
}
