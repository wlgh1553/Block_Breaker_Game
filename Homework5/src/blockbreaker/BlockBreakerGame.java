package blockbreaker;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;

import blockbreaker.model.Ball;

//나중에 over 패널과 title 패널도 만들어서 계속 갈아끼우자.
class PlayPanel extends JPanel {
	Ball temp;

	public PlayPanel() {
		temp = new Ball(new Point(400, 650), 10);
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

		// 공 그리기
		temp.draw(g);
	}
}

public class BlockBreakerGame extends JFrame {
	public BlockBreakerGame() {
		setTitle("Java Homework4");
		setSize(800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		add(new PlayPanel());

		setVisible(true);
	}

	public static void main(String[] args) {
		new BlockBreakerGame();
	}
}
