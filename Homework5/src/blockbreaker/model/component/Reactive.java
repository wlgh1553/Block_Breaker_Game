package blockbreaker.model.component;

import java.awt.Graphics;

public interface Reactive {
	public void draw(Graphics g);

	public void update(double dt);

	public void resolve();
}
