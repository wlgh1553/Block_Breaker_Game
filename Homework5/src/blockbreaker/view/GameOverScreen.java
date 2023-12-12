package blockbreaker.view;

import javax.swing.JLabel;

public class GameOverScreen extends Screen {
	public GameOverScreen() {
		super();
	}

	@Override
	protected void initScreen() {
		JLabel l = new JLabel("game over!");
		add(l);
	}
}
