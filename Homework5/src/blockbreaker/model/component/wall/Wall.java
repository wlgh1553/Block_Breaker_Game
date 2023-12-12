package blockbreaker.model.component.wall;

import java.awt.Color;
import java.awt.Graphics;

import blockbreaker.model.component.DetectableCollision;

public class Wall extends DetectableCollision {

	public Wall(int _x, int _y, int _w, int _h) {
		super(_x, _y, _w, _h);
	}

	public void draw(Graphics g) {
		// 이걸 다른 컴포넌트들이랑 함께 계속 업데이트 해주는 게 맞나...
		g.setColor(Color.DARK_GRAY);
		g.fillRect((int) x, (int) y, width, height);
	}
}
