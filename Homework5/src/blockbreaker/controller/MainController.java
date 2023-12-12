package blockbreaker.controller;

import blockbreaker.view.frame.GameFrame;

public class MainController {
	// 필드로 만들필요 없는 것들은 모두 지우자
	private ScreenController screenController;
	private GameFrame gameFrame;

	public MainController() {
		screenController = new ScreenController();
		gameFrame = new GameFrame(screenController);

	}
}
