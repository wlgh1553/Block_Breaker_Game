package blockbreaker.view;

import javax.swing.JLabel;

public class PlayScreen extends Screen {
	public PlayScreen() {
		super();
	}

	@Override
	protected void initScreen() {
		JLabel l = new JLabel("game playing!");
		add(l);
	}
}
