package blockbreaker.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JPanel;

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

public class StartPanel extends JPanel implements KeyListener {
	LinkedList<MyText> texts = new LinkedList<>();

	public StartPanel() {
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
		Color startColor = Color.black;
		Color endColor = new Color(127, 127, 166);
		GradientPaint gradient = new GradientPaint(0, 0, startColor, 0, getHeight(), endColor);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(gradient);
		g2d.fillRect(0, 0, getWidth(), getHeight());
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
