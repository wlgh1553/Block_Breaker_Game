package blockbreaker.view;

import javax.swing.JLabel;

public class TitleScreen extends Screen {
	public TitleScreen() {
		super();
	}

	@Override
	protected void initScreen() {
		JLabel l = new JLabel("title screen!");
		add(l);
	}
}
