package blockbreaker.model;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ComponentsManager {
	int stage;
	private LinkedList<GameComponent> components;
	private HashSet<GameComponent> needToEraseComponents;
	Racket racket;

	public ComponentsManager(int stage) {
		components = new LinkedList<>();
		needToEraseComponents = new HashSet<>();
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

	public void addEraseThing(GameComponent e) {
		needToEraseComponents.add(e);
	}

	public void update() {
		for (GameComponent g : components) {
			g.update(0.016);
		}
	}

	public void resolve() {
		for (GameComponent g : components) {
			g.resolve(this);
		}

		Iterator<GameComponent> iter = components.iterator();
		while (iter.hasNext()) {
			GameComponent here = iter.next();
			if (needToEraseComponents.contains(here)) {
				iter.remove();
			}
		}
	}

	public void paint(Graphics2D g2d) {
		for (GameComponent gc : components) {
			gc.draw(g2d);
		}
	}

	public void changeRacketDirection(int sign) {
		racket.changeDirection(sign);
	}
}