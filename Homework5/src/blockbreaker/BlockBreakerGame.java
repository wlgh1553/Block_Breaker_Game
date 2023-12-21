package blockbreaker;

import javax.swing.JFrame;
import javax.swing.JPanel;

import blockbreaker.view.StartPanel;

public class BlockBreakerGame extends JFrame {
	JPanel now;
	int nowScore, maxScore;

	public BlockBreakerGame() {
		setTitle("Java Homework4");
		setSize(800, 800);
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		now = new StartPanel(this);
		add(now);

		setVisible(true);
	}

	public void changeScreen(JPanel next) {
		now.setVisible(false);
		now = next;
		add(now);
		now.setFocusable(true);
		now.requestFocus();
	}

	public static void main(String[] args) {
		new BlockBreakerGame();
	}
}
