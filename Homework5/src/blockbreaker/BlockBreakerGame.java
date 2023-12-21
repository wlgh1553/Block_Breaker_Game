package blockbreaker;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import blockbreaker.model.ComponentsManager;

//나중에 over 패널과 title 패널도 만들어서 계속 갈아끼우자.
class PlayPanel extends JPanel implements KeyListener, Runnable {
	// 다 짬뽕해서 관리해보자
	ComponentsManager componentManager;

	public PlayPanel() {
		int stage = 2;
		componentManager = new ComponentsManager(stage);

		Thread t = new Thread(this);
		t.start();

		this.addKeyListener(this);
		this.setFocusable(true);
		this.requestFocus();
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

		// 컴포넌트 그리기
		componentManager.paint(g2d);
	}

	@Override
	public void run() {
		while (true) {
			// update
			componentManager.update();

			// resolve
			if (componentManager.isGameClear()) {
				System.out.println("clear!!");
			} else if (componentManager.isGameOver()) {
				System.out.println("overㅠㅠ");
			}
			componentManager.resolve();

			// render
			repaint();

			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == e.VK_LEFT) {
			componentManager.changeRacketDirection(-1);
			repaint();
		} else if (e.getKeyCode() == e.VK_RIGHT) {
			componentManager.changeRacketDirection(1);
			repaint();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		componentManager.changeRacketDirection(0);
		repaint();
	}

}

public class BlockBreakerGame extends JFrame {
	public BlockBreakerGame() {
		setTitle("Java Homework4");
		setSize(800, 800);
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		add(new PlayPanel());

		setVisible(true);
	}

	public static void main(String[] args) {
		new BlockBreakerGame();
	}
}
