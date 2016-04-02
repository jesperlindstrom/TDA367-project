package se.chalmers.get_rect.game.entities;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.IGameComponent;

public interface IEntity extends IGameComponent {
    interface View {
        void draw(IGraphicsAdapter graphics);
    }

    interface Model {
        // todo: return type LibGDX adapter for vector2?
        void getPosition();
    }
}