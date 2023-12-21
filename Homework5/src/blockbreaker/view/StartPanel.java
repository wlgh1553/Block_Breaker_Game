package blockbreaker.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import javax.swing.JLabel;

import blockbreaker.BlockBreakerGame;

class MyText extends JLabel implements Runnable {
	private int interval;
	private boolean turnOn = false;
	private Font font;
	private Color color;

	public MyText(String text, Font font, Color color, int inv) {
		super(text);
		this.font = font;
		this.color = color;
		super.setFont(font);
		super.setForeground(color); // 색 변경
		this.interval = inv;

		if (interval != 0) {
			Thread t = new Thread(this);
			t.start();
		}
	}

	@Override
	public void run() {
		while (true) {
			if (turnOn)
				this.setForeground(color);
			else
				this.setForeground(color.darker());

			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			turnOn = !turnOn;
		}
	}

}

public class StartPanel extends Screen {
	LinkedList<MyText> texts = new LinkedList<>();

	public StartPanel(BlockBreakerGame b) {
		super(b);
		Font f = new Font("Arial", Font.PLAIN, 50);
		MyText subTitle1 = new MyText("Java Programming", f, Color.white, 0);
		subTitle1.setBounds(190, 100, 700, 60);
		texts.add(subTitle1);
		MyText subTitle2 = new MyText("Homework #5", f, Color.white, 0);
		subTitle2.setBounds(240, 170, 700, 60);
		texts.add(subTitle2);

		f = new Font("궁서체", Font.BOLD, 100);
		MyText mainTitle = new MyText("BLOCK BREAKER", f, Color.white, 0);
		mainTitle.setBounds(50, 320, 800, 110);
		texts.add(mainTitle);

		f = new Font("궁서체", Font.PLAIN, 30);
		MyText info = new MyText("PRESS SPACEBAR TO PLAY!", f, new Color(255, 40, 40), 100);
		info.setBounds(230, 540, 800, 40);
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

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == e.VK_SPACE) {
			super.controller.changeScreen(new PlayPanel(controller, 1));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
