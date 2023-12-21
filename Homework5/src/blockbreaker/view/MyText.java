package blockbreaker.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class MyText extends JLabel implements Runnable {
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
