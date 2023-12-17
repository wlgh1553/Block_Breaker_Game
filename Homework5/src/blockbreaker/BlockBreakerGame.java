package blockbreaker;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

//나중에 over 패널과 title 패널도 만들어서 계속 갈아끼우자.
class PlayPanel extends JPanel {
	public PlayPanel() {
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// 그라데이션 색상 설정
		Color startColor = Color.black;
		Color endColor = new Color(127, 127, 166);

		// 그라데이션 객체 생성
		GradientPaint gradient = new GradientPaint(0, 0, startColor, 0, getHeight(), endColor);

		// Graphics2D 객체 얻기
		Graphics2D g2d = (Graphics2D) g;

		// 그라데이션으로 배경 그리기
		g2d.setPaint(gradient);
		g2d.fillRect(0, 0, getWidth(), getHeight());
	}
}

class GameFrame extends JFrame {
	public GameFrame() {
		setTitle("Java Homework4");
		setSize(800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}

public class BlockBreakerGame {
	public static void main(String[] args) {
		GameFrame gameFrame = new GameFrame();
		PlayPanel playPanel = new PlayPanel();
		gameFrame.add(playPanel);
	}
}
