package blockbreaker.model.component.wall;

import java.awt.Color;
import java.awt.Graphics;

import blockbreaker.model.component.Component;

public class Wall extends Component {
	private int x, y, width, height;

	// 꼭 이렇게 해야만 하는가
	public double xmin, xmax, ymin, ymax;

	public Wall(int _x, int _y, int _w, int _h) {
		x = _x;
		y = _y;
		width = _w;
		height = _h;

		xmin = 0;
		xmax = 0;
		ymin = 0;
		ymax = 0;
	}

	@Override
	public void draw(Graphics g) {
		// 이걸 다른 컴포넌트들이랑 함께 계속 업데이트 해주는 게 맞나...
		g.setColor(Color.DARK_GRAY);
		g.fillRect(x, y, width, height);
	}

	public boolean isCollision(double ballX, double ballY, double ballR) {
		xmin = x - ballR;
		xmax = x + width + ballR;
		ymin = y - ballR;
		ymax = y + height + ballR;

		if (ballX > xmin && ballX < xmax && ballY > ymin && ballY < ymax)
			return true;
		return false;
	}
}
