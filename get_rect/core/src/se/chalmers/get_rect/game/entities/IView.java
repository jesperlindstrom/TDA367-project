package se.chalmers.get_rect.game.entities;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;

public interface IView {
    int getDrawPriority();
    boolean shouldBeRemoved();
    void draw(IGraphicsAdapter graphics);
}