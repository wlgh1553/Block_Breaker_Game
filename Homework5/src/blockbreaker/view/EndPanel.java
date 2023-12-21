package blockbreaker.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import blockbreaker.BlockBreakerGame;

public class EndPanel extends Screen {
	LinkedList<MyText> texts = new LinkedList<>();

	public EndPanel(BlockBreakerGame b) {
		super(b);

		Font f = new Font("궁서체", Font.BOLD, 100);
		MyText mainTitle = new MyText("GAME OVER", f, Color.white, 0);
		mainTitle.setBounds(160, 220, 800, 110);
		texts.add(mainTitle);

		f = new Font("궁서체", Font.BOLD, 40);
		MyText high = new MyText("HIGH SCORE: " + b.getHighScore(), f, Color.gray, 0);
		high.setBounds(250, 400, 800, 60);
		texts.add(high);

		MyText your = new MyText("YOUR SCORE: " + b.getHighScore(), f, Color.gray, 0);
		your.setBounds(250, 460, 800, 60);
		texts.add(your);

		f = new Font("궁서체", Font.PLAIN, 30);
		MyText info = new MyText("PRESS SPACEBAR!", f, new Color(255, 40, 40), 100);
		info.setBounds(280, 600, 800, 40);
		texts.add(info);

		setLayout(null);
		for (MyText t : texts) {
			add(t);
		}
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
