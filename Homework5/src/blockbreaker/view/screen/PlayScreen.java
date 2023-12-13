package blockbreaker.view.screen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import blockbreaker.model.component.DetectableCollision;
import blockbreaker.model.component.Racket;
import blockbreaker.model.component.ball.Ball;
import blockbreaker.model.component.ball.Balls;
import blockbreaker.model.component.wall.Walls;

public class PlayScreen extends Screen implements KeyListener {
	private Walls walls; // wall은 스테이지와 관계없이 유지될 순 없나
	private Balls balls;
	private Racket racket;

	// 리팩토링 하자
	private LinkedList<DetectableCollision> dtc = new LinkedList<>();

	public PlayScreen() {
		super();
		this.setBackground(Color.GRAY);
		initComponents();
	}

	private void initComponents() {
		// stage에 알맞게 블록 개수와 공 속도 조절
		walls = new Walls();
		balls = new Balls();
		balls.addBall(Ball.getInitialBall());
		racket = new Racket();

		walls.addWalls(dtc);
		dtc.add(racket);

		this.addKeyListener(this);
		this.setFocusable(true);
		this.requestFocus();
	}

	public void updateComponents(double dt) {
		balls.update();
		racket.update(dt);
	}

	public void resolveComponents() {
		balls.resolve(dtc);
		racket.resolve();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		walls.draw(g);
		balls.draw(g);
		racket.draw(g);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("hi");
		if (e.getKeyCode() == e.VK_LEFT) {
			racket.moveLeft();
		}
		if (e.getKeyCode() == e.VK_RIGHT) {
			racket.moveRight();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		racket.stopMove();
	}
}
