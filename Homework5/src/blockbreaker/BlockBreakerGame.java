package blockbreaker;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import blockbreaker.model.Ball;
import blockbreaker.model.Block;
import blockbreaker.model.GameComponent;
import blockbreaker.model.Racket;
import blockbreaker.model.Wall;

//나중에 over 패널과 title 패널도 만들어서 계속 갈아끼우자.
class PlayPanel extends JPanel implements KeyListener, Runnable {
	// 다 짬뽕해서 관리해보자
	LinkedList<GameComponent> components;
	Racket racket;

	public PlayPanel() {
		components = new LinkedList<>();

		components.add(new Ball(new Point(400, 650), 5, 500)); // 속도 조절
		components.add(new Wall(new Point(0, 0), 800, 20));
		components.add(new Wall(new Point(0, 20), 20, 800 - 20));
		components.add(new Wall(new Point(800 - 34, 20), 20, 800));
		racket = new Racket(new Point(400, 680), 150, 30);
		components.add(racket);
		Block.createBlock(2, new Point(20, 20), 800 - 54, 400, components);

		Thread t = new Thread(this);
		t.start();

		this.addKeyListener(this);
		this.setFocusable(true);
		this.requestFocus();
	}

	@Override
	public void run() {
		while (true) {
			// update
			for (GameComponent g : components) {
				g.update(0.016);
			}

			// resolve
			for (GameComponent g : components) {
				g.resolve(components);
			}

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
		for (GameComponent gc : components) {
			gc.draw(g2d);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == e.VK_LEFT) {
			racket.changeDirection(-1);
			repaint();
		} else if (e.getKeyCode() == e.VK_RIGHT) {
			racket.changeDirection(1);
			repaint();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		racket.changeDirection(0);
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
