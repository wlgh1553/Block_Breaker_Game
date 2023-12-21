package blockbreaker;

import javax.swing.JFrame;
import javax.swing.JPanel;

import blockbreaker.view.EndPanel;

public class BlockBreakerGame extends JFrame {
	JPanel now;
	private int nowScore, highScore;

	public BlockBreakerGame() {
		setTitle("Java Homework4");
		setSize(800, 800);
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		now = new EndPanel(this);
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

	public int getHighScore() {
		return highScore;
	}

	public int getNowScore() {
		return nowScore;
	}

	public static void main(String[] args) {
		new BlockBreakerGame();
	}
}
