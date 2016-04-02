package se.chalmers.get_rect.game;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;

public interface IGameComponent {
    void update(long delta);
    void draw(IGraphicsAdapter graphics);
}
