package blockbreaker.model.component;

import java.awt.Graphics;

public abstract class Component {
	abstract public void draw(Graphics g);

	public void update(double dt) {
	}

	public void resolve(Component o) {
	}
}
