package blockbreaker.view;

import javax.swing.JPanel;

public abstract class Screen extends JPanel {
	public Screen() {
		initScreen();
	}

	protected abstract void initScreen();
}
