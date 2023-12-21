package blockbreaker;

import javax.swing.JFrame;
import javax.swing.JPanel;

import blockbreaker.view.StartPanel;

public class BlockBreakerGame extends JFrame {
	JPanel now;
	private int nowScore, highScore;

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

	public void resetNowScore() {
		nowScore = 0;
	}

	public void updateNowScore(int blockCnt) {
		nowScore += (blockCnt * 10);
		if (nowScore > highScore)
			highScore = nowScore;
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
