package blockbreaker.view;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import blockbreaker.BlockBreakerGame;

abstract public class Screen extends JPanel implements KeyListener {
	protected BlockBreakerGame controller;

	public Screen(BlockBreakerGame b) {
		controller = b;
		this.addKeyListener(this);
		this.setFocusable(true);
		this.requestFocus();
	}

	protected void drawBackground(Graphics g) {
		// 그라데이션 배경 그리기
		Color startColor = Color.black;
		Color endColor = new Color(127, 127, 166);
		GradientPaint gradient = new GradientPaint(0, 0, startColor, 0, getHeight(), endColor);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(gradient);
		g2d.fillRect(0, 0, getWidth(), getHeight());

	}
}
