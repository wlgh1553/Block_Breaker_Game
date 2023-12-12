package blockbreaker.model.component.wall;

import java.awt.Graphics;
import java.util.LinkedList;

public class Walls {
	public LinkedList<Wall> walls;

	public Walls() {
		walls = new LinkedList<>();
		walls.add(new Wall(0, 0, 800, 20));
		walls.add(new Wall(0, 20, 20, 800 - 20));
		walls.add(new Wall(800 - 34, 20, 20, 800 - 20));
	}

	public void draw(Graphics g) {
		for (Wall w : walls) {
			w.draw(g);
		}
	}

	public void update() {
		for (Wall w : walls) {
			w.update(0.016); // 상수처리 해주자
		}
	}
}
