package blockbreaker.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import blockbreaker.BlockBreakerGame;
import blockbreaker.model.ComponentsManager;

public class PlayPanel extends Screen implements Runnable {
	ComponentsManager componentManager;
	private boolean isClearStage = false;
	private int stage;

	public PlayPanel(BlockBreakerGame b, int stage) {
		super(b);
		this.stage = stage;
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
		Graphics2D g2d = (Graphics2D) g;
		super.drawBackground(g);

		// 컴포넌트 그리기
		componentManager.paint(g2d);
	}

	@Override
	public void run() {
		while (true) {
			if (isClearStage)
				break;
			if (componentManager.isGameClear()) {
				isClearStage = true;
				playGameClearBGM();
				super.controller.updateNowScore(componentManager.getRemovedBlockCnt());
				super.controller.changeScreen(new PlayPanel(super.controller, stage + 1));
			} else if (componentManager.isGameOver()) {
				isClearStage = true;
				playGameOverBGM();
				super.controller.updateNowScore(componentManager.getRemovedBlockCnt());
				super.controller.changeScreen(new EndPanel(controller));
			}

			// update
			componentManager.update();

			// resolve
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
		} else if (e.getKeyCode() == e.VK_SPACE) {
			isClearStage = true;
			playGameOverBGM();
			super.controller.updateNowScore(componentManager.getRemovedBlockCnt());
			super.controller.changeScreen(new EndPanel(controller));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		componentManager.changeRacketDirection(0);
		repaint();
	}

	private void playGameClearBGM() {
		playSound("sound/게임클리어.wav");
	}

	private void playGameOverBGM() {
		playSound("sound/게임오버.wav");
	}

	private void playSound(String path) {
		try {
			Clip audio = AudioSystem.getClip();
			File audioFile = new File(path);
			AudioInputStream stream = AudioSystem.getAudioInputStream(audioFile);
			audio.open(stream);
			audio.setFramePosition(0);
			audio.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}