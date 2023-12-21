package blockbreaker;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import blockbreaker.view.PlayPanel;
import blockbreaker.view.StartPanel;

public class BlockBreakerGame extends JFrame implements KeyListener {
	JPanel now;

	public BlockBreakerGame() {
		setTitle("Java Homework4");
		setSize(800, 800);
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		now = new StartPanel();
		add(now);
		this.addKeyListener(this);
		this.setFocusable(true);
		this.requestFocus();

		setVisible(true);
	}

	public static void main(String[] args) {
		new BlockBreakerGame();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == e.VK_SPACE) {
			now.setVisible(false);
			now = new PlayPanel();
			add(now);
			now.setFocusable(true);
			now.requestFocus();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
