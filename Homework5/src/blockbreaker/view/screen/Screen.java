package blockbreaker.view.screen;

import javax.swing.JPanel;

public abstract class Screen extends JPanel {
	public Screen() {
		initScreen();
	}

	protected abstract void initScreen();
}
