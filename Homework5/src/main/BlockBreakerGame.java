package main;

import javax.swing.JFrame;

public class BlockBreakerGame extends JFrame {
	BlockBreakerGame() {
		setTitle("Java Homework4");
		setSize(800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// add!!

		setVisible(true);
	}

	public static void main(String[] args) {
		new BlockBreakerGame();
	}
}
