package blockbreaker.model;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.LinkedList;

abstract class GameBlock {
	protected Color color;

	abstract void draw(Graphics2D g, Block b);

	protected void drawRect(Graphics2D g, Color startColor, Color endColor, Block block) {
		GradientPaint gradient = new GradientPaint(0, (int) (block.position.y - block.halfHeight), startColor, 0,
				(int) (block.position.y + block.halfHeight), endColor);
		g.setPaint(gradient);
		g.fillRect((int) (block.position.x - block.halfWidth), (int) (block.position.y - block.halfHeight),
				(int) (2 * block.halfWidth), (int) (2 * block.halfHeight));

		int blinkSize = 3;
		g.setColor(color);
		g.fillRect((int) (block.position.x - block.halfWidth + blinkSize),
				(int) (block.position.y - block.halfHeight + blinkSize), (int) (2 * (block.halfWidth - blinkSize)),
				(int) (2 * (block.halfHeight - blinkSize)));
	}
}

class BasicBlock extends GameBlock {
	BasicBlock() {
		super.color = new Color(150, 100, 150);
	}

	@Override
	void draw(Graphics2D g, Block b) {
		Color startColor = new Color(225, 225, 225);
		Color endColor = new Color(62, 48, 66);
		super.drawRect(g, startColor, endColor, b);
	}
}

class ReplicatorBlock extends GameBlock {
	ReplicatorBlock() {
		super.color = new Color(200, 200, 0);
	}

	@Override
	void draw(Graphics2D g, Block b) {
		Color startColor = new Color(250, 250, 60);
		Color endColor = new Color(85, 85, 0);
		super.drawRect(g, startColor, endColor, b);
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

		for (int i = 0; i < cnt; i++) {
			for (int j = 0; j < cnt; j++) {
				double startX = blocksStart.x + blockWidth * j;
				double startY = blocksStart.y + blockHeight * i;
				PrecisePoint start = new PrecisePoint(startX, startY);
				components.add(new Block(start, blockWidth, blockHeight, new BasicBlock()));
			}
		}
	}

	@Override
	public void draw(Graphics2D g) {
		blockManager.draw(g, this);
	}

	@Override
	public void update(double dt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resolve(LinkedList<GameComponent> others) {
		// TODO Auto-generated method stub

	}

}
