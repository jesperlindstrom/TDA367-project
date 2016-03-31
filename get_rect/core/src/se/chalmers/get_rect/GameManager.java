package se.chalmers.get_rect;

import se.chalmers.get_rect.adapters.IGameLoopAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.IInputAdapter;

public class GameManager implements IGameLoopAdapter {
    private IGraphicsAdapter graphicsAdapter;
    private IInputAdapter inputAdapter;

    public GameManager(IGraphicsAdapter graphicsAdapter, IInputAdapter inputAdapter) {
        this.graphicsAdapter = graphicsAdapter;
        this.inputAdapter = inputAdapter;
    }

    @Override
    public void draw() {

    }

    @Override
    public void update(long delta) {

    }
}
