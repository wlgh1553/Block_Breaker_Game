package blockbreaker.view;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import blockbreaker.BlockBreakerGame;

public class EndPanel extends Screen {
	public EndPanel(BlockBreakerGame b) {
		super(b);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// 그라데이션 배경 그리기
		super.drawBackground(g);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == e.VK_SPACE) {
			super.controller.changeScreen(new StartPanel(controller));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
