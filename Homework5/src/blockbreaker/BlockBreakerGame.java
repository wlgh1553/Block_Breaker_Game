package blockbreaker;

import blockbreaker.controller.ScreenController;
import blockbreaker.view.frame.GameFrame;

public class BlockBreakerGame {
	public static void main(String[] args) {
		// mainController에서 screenController를 관리할 수 있도록 나중에 빼주자
		ScreenController screenController = new ScreenController();

		// 이거 위치도 옮겨 나중에!!
		new GameFrame(screenController);
	}

}
