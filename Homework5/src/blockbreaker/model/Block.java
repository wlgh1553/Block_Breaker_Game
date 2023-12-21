package blockbreaker.model;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.LinkedList;

abstract class GameBlock {
	protected Color color;
	protected int alpha; // 0 투명 255 불투명

	GameBlock(Color c) {
		color = c;
		alpha = 255;
	}

	abstract void draw(Graphics2D g, Block b);

	abstract String getSoundPath();

	protected void drawRect(Graphics2D g, Color color, Color startColor, Color endColor, Block block) {
		GradientPaint gradient = new GradientPaint(0, (int) (block.position.y - block.halfHeight), startColor, 0,
				(int) (block.position.y + block.halfHeight), endColor);
		g.setPaint(gradient);
		g.fillRect((int) (block.position.x - block.halfWidth), (int) (block.position.y - block.halfHeight),
				(int) (2 * block.halfWidth), (int) (2 * block.halfHeight));

		double blinkSize = block.halfHeight / 13;

		g.setColor(color);
		g.fillRect((int) (block.position.x - block.halfWidth + blinkSize),
				(int) (block.position.y - block.halfHeight + blinkSize), (int) (2 * (block.halfWidth - blinkSize)),
				(int) (2 * (block.halfHeight - blinkSize)));
	}

	protected void drawBlinkRect(Graphics2D g, Block block) {
		Color c = new Color(255, 255, 145);
		Color s = new Color(255, 255, 245);
		Color e = new Color(255, 255, 45);
		drawRect(g, c, s, e, block);
	}

	abstract public void affectBalls(ComponentsManager manager, Ball core);

	public void updateTransparency() {
		alpha -= 10;
		if (alpha < 0)
			alpha = 0;
		color = new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
	}

	public boolean isAlphaZero() {
		return alpha == 0;
	}
}

class BasicBlock extends GameBlock {
	BasicBlock() {
		super(new Color(150, 100, 150));
	}

	@Override
	void draw(Graphics2D g, Block b) {
		Color startColor = color.brighter().brighter();
		Color endColor = color.darker().darker();
		super.drawRect(g, color, startColor, endColor, b);
	}

	@Override
	public void affectBalls(ComponentsManager manager, Ball core) {
		// nothing
	}

	@Override
	String getSoundPath() {
		return "sound/보라블록부딪침.wav";
	}
}

class ReplicatorBlock extends GameBlock {
	int blinkTime = 0;

	ReplicatorBlock() {
		super(new Color(200, 200, 0));
	}

	@Override
	void draw(Graphics2D g, Block b) {
		Color startColor = color.brighter().brighter();
		Color endColor = color.darker().darker();
		if (blinkTime == 0) {
			super.drawRect(g, color, startColor, endColor, b);
		} else {
			super.drawBlinkRect(g, b);
			blinkTime = (blinkTime - 1５) < 0 ? 0 : blinkTime - 1５;
		}

		if (super.alpha == 255 && blinkTime == 0) {
			boolean rand = (int) (Math.random() * 1000) <= 3;
			if (rand) {
				blinkTime = ２00;
			}
		}
	}

	@Override
	public void affectBalls(ComponentsManager manager, Ball core) {
		// duplicate
		manager.addDuplicatedThing(new Ball(core, 10));
		manager.addDuplicatedThing(new Ball(core, -10));
	}

	@Override
	String getSoundPath() {
		return "sound/노란블록부딪침.wav";
	}
}

public class Block extends GameComponent {
	private GameBlock blockManager;

	Block(PrecisePoint startPos, double width, double height, GameBlock blockSort) {
		super(new BallDetector());
		super.position = new PrecisePoint(startPos.x + width / 2, startPos.y + height / 2);
		super.halfWidth = width / 2;
		super.halfHeight = height / 2;
		this.blockManager = blockSort;
	}

	public static void createBlock(int stage, Point blocksStart, double blocksWidth, double blocksHeight,
			LinkedList<GameComponent> components) {
		int cnt = stage * 3;
		double blockWidth = blocksWidth / (double) cnt;
		double blockHeight = blocksHeight / (double) cnt;
		int replicatorBlockCnt = 0;
		final int maxReplicatorBlockCnt = cnt * cnt / 3;

		for (int i = 0; i < cnt; i++) {
			for (int j = 0; j < cnt; j++) {
				double startX = blocksStart.x + blockWidth * j;
				double startY = blocksStart.y + blockHeight * i;
				PrecisePoint start = new PrecisePoint(startX, startY);
				GameBlock gameBlock;
				if (replicatorBlockCnt < maxReplicatorBlockCnt) {
					boolean flag = ((int) (Math.random() * 100)) % 3 == 0;
					if (flag) {
						gameBlock = new ReplicatorBlock();
						replicatorBlockCnt++;
					} else {
						gameBlock = new BasicBlock();
					}
				} else
					gameBlock = new BasicBlock();
				components.add(new Block(start, blockWidth, blockHeight, gameBlock));
			}
		}
	}

	public void affectBalls(ComponentsManager manager, Ball core) {
		blockManager.affectBalls(manager, core);
	}

	public boolean isFadingFinish() {
		return blockManager.isAlphaZero();
	}

	@Override
	public void draw(Graphics2D g) {
		blockManager.draw(g, this);
	}

	@Override
	public void update(double dt) {
		if (this.isAlive)
			return;

		blockManager.updateTransparency();
	}

	@Override
	public void resolve(ComponentsManager manager) {
	}

	@Override
	public void playCollisionSound() {
		super.playSound(blockManager.getSoundPath());
	}

}
