package blockbreaker;

import javax.swing.JFrame;

import blockbreaker.view.StartPanel;

//나중에 over 패널과 title 패널도 만들어서 계속 갈아끼우자.

public class BlockBreakerGame extends JFrame {
	public BlockBreakerGame() {
		setTitle("Java Homework4");
		setSize(800, 800);
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		add(new StartPanel());

		setVisible(true);
	}

	public static void main(String[] args) {
		new BlockBreakerGame();
	}
}
