package blockbreaker.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.LinkedList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import blockbreaker.BlockBreakerGame;

public class StartPanel extends Screen {
	LinkedList<MyText> texts = new LinkedList<>();
	Clip audio = null;

	public StartPanel(BlockBreakerGame b) {
		super(b);
		setAudio();

		Font f = new Font("Arial", Font.PLAIN, 50);
		MyText subTitle1 = new MyText("Java Programming", f, Color.white, 0);
		subTitle1.setBounds(190, 100, 700, 60);
		texts.add(subTitle1);
		MyText subTitle2 = new MyText("Homework #5", f, Color.white, 0);
		subTitle2.setBounds(240, 170, 700, 60);
		texts.add(subTitle2);

		f = new Font("궁서체", Font.BOLD, 100);
		String[] strings = { "B", "L", "O", "C", "K", " ", "B", "R", "E", "A", "K", "E", "R" };
		for (int i = 0; i < strings.length; i++) {
			MyText myText = new MyText(strings[i], f, Color.white, 800, i * 100);
			myText.setBounds(55 + i * 50, 320, 150, 150);
			texts.add(myText);
		}

		f = new Font("궁서체", Font.PLAIN, 30);
		MyText info = new MyText("PRESS SPACEBAR TO PLAY!", f, new Color(255, 40, 40), 100);
		info.setBounds(230, 540, 800, 40);
		texts.add(info);

		setLayout(null);
		for (MyText t : texts) {
			add(t);
		}

		b.resetNowScore();
	}

	private void setAudio() {
		try {
			audio = AudioSystem.getClip();
			URL url = getClass().getClassLoader().getResource("sound/시작화면.wav");
			AudioInputStream stream = AudioSystem.getAudioInputStream(url);
			audio.open(stream);
			audio.setFramePosition(0);
			audio.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// 그라데이션 배경 그리기
		super.drawBackground(g);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == e.VK_SPACE) {
			audio.stop();
			super.controller.changeScreen(new PlayPanel(controller, 1));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
