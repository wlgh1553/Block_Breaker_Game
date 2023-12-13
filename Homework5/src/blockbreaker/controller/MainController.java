package blockbreaker.controller;

import blockbreaker.view.frame.GameFrame;
import blockbreaker.view.screen.PlayScreen;
import blockbreaker.view.screen.Screen;
import blockbreaker.view.screen.TitleScreen;

public class MainController {
	// 필드로 만들필요 없는 것들은 모두 지우자
	private Screen screen;
	private GameFrame gameFrame;
	private PlayController playController = null;// 이거 위치 나중에 바꾸자!

	public MainController() {
		screen = new TitleScreen();
		gameFrame = new GameFrame(screen);
		playGame(); // 임시
	}

	private void playGame() {
		// screen setting
		screen = new PlayScreen();
		screenTransition(screen);

		// controller
		playController = new PlayController(screen);
		playController.play();
	}

	private void screenTransition(Screen screen) {
		gameFrame.getContentPane().removeAll();
		gameFrame.getContentPane().add(screen);
		gameFrame.revalidate();
		gameFrame.repaint();
	}
}
