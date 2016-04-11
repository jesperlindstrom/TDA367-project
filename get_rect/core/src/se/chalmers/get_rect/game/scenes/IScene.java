package se.chalmers.get_rect.game.scenes;

import se.chalmers.get_rect.game.IGameComponent;
import se.chalmers.get_rect.game.entities.IController;
import se.chalmers.get_rect.states.IState;

public interface IScene extends IGameComponent, IState {
    void addEntity(layer layer, IController controller);

    enum layer {FOREGROUND, BACKGROUND, FOREGROUND_EFFECTS, BACKGROUND_EFFECTS}
}
