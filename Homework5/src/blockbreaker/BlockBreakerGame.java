package blockbreaker;

import javax.swing.JFrame;

import blockbreaker.controller.ScreenController;

public class BlockBreakerGame extends JFrame {

	// 나중에 메인 컨트롤러로 이동시키자
	ScreenController screenController = new ScreenController();

	BlockBreakerGame() {
		setTitle("Java Homework4");
		setSize(800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		screenController.showScreen(this);

		setVisible(true);
	}

	public static void main(String[] args) {
		new BlockBreakerGame();
	}

}
