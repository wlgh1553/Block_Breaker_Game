package blockbreaker.model.component;

public abstract class DetectableCollision {
	protected double x, y;
	protected int width, height;

	// 꼭 이랬어야 했는가...
	public double xmin, xmax, ymin, ymax;

	public DetectableCollision(int _x, int _y, int _w, int _h) {
		x = _x;
		y = _y;
		width = _w;
		height = _h;
		xmin = 0;
		xmax = 0;
		ymin = 0;
		ymax = 0;
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
