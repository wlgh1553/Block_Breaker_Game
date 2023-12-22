package blockbreaker.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class MyText extends JLabel implements Runnable {
	private int interval;
	private boolean turnOn = false;
	private Font font;
	private Color color;
	private int startWait = 0;

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

	public MyText(String text, Font font, Color color, int inv, int startWait) {
		this(text, font, color, inv);
		this.startWait = startWait;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(startWait);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
