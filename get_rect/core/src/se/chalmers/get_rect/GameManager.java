package se.chalmers.get_rect;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.IInputAdapter;

public class GameManager {
    private IGraphicsAdapter graphicsAdapter;
    private IInputAdapter inputAdapter;

    public GameManager(IGraphicsAdapter graphicsAdapter, IInputAdapter inputAdapter) {
        this.graphicsAdapter = graphicsAdapter;
        this.inputAdapter = inputAdapter;
    }

    public void draw() {

    }

    public void update(long delta) {

    }
}
