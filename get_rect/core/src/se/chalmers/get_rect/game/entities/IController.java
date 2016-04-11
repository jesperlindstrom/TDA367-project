package se.chalmers.get_rect.game.entities;

import se.chalmers.get_rect.game.IGameComponent;
import se.chalmers.get_rect.game.scenes.IScene;

public interface IController extends IGameComponent {
    void setScene(IScene scene);

}