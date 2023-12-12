package blockbreaker.model.component.wall;

import java.awt.Graphics;
import java.util.LinkedList;

import blockbreaker.model.component.DetectableCollision;

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

	public void addWalls(LinkedList<DetectableCollision> dtc) {
		for (Wall w : walls) {
			dtc.add(w);
		}
	}
}
