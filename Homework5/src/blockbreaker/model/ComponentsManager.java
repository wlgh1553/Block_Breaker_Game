package blockbreaker.model;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ComponentsManager {
	int stage;
	private LinkedList<GameComponent> components;
	private LinkedList<Ball> needToAddBalls;
	private LinkedList<Block> fadingBlocks;
	Racket racket;

	public ComponentsManager(int stage) {
		components = new LinkedList<>();
		needToAddBalls = new LinkedList<>();
		fadingBlocks = new LinkedList<>();
		this.stage = stage;

		// 초기 컴포넌트 구성하기
		makeInitialComponents();
	}

	public List<GameComponent> getComponents() {
		return Collections.unmodifiableList(components);
	}

	private void makeInitialComponents() {
		components.add(new Ball(new Point(400, 650), stage));
		components.add(new Wall(new Point(0, 0), 800, 20));
		components.add(new Wall(new Point(0, 20), 20, 800 - 20));
		components.add(new Wall(new Point(800 - 34, 20), 20, 800));
		racket = new Racket(new Point(400, 680), 150, 30);
		components.add(racket);
		Block.createBlock(stage, new Point(20, 20), 800 - 54, 400, components);
	}

	public void addDuplicatedThing(Ball b) {
		needToAddBalls.add(b);
	}

	public void update() {
		for (GameComponent g : components) {
			g.update(0.016);
		}
		for (Block b : fadingBlocks) {
			b.update(0.016);
		}
	}

	public void resolve() {
		for (GameComponent g : components) {
			g.resolve(this);
		}

		// 충돌한 벽, 밖으로 나간 공 지우기
		Iterator<GameComponent> iter = components.iterator();
		while (iter.hasNext()) {
			GameComponent here = iter.next();
			if (!here.isAlive) {
				if (here instanceof Block) {
					fadingBlocks.add((Block) here);
				}
				iter.remove();
			}
		}

		// 복제된 공 추가하기
		components.addAll(needToAddBalls);
		needToAddBalls.clear();

		// 블록 희미해지면 애니메이션 끝내기
		Iterator<Block> blockIter = fadingBlocks.iterator();
		while (blockIter.hasNext()) {
			Block here = blockIter.next();
			if (here.isFadingFinish()) {
				blockIter.remove();
			}
		}
	}

	public boolean isGameOver() {
		int cnt = 0;
		for (GameComponent g : components) {
			if (g instanceof Ball)
				cnt++;
		}
		return cnt == 0;
	}

	public boolean isGameClear() {
		int cnt = 0;
		for (GameComponent g : components) {
			if (g instanceof Block)
				cnt++;
		}
		return cnt == 0;
	}

	public void paint(Graphics2D g2d) {
		for (GameComponent gc : components) {
			gc.draw(g2d);
		}
		for (Block b : fadingBlocks) {
			b.draw(g2d);
		}
	}

	public void changeRacketDirection(int sign) {
		racket.changeDirection(sign);
	}
}