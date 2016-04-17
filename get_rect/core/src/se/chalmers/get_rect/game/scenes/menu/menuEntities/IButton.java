package se.chalmers.get_rect.game.scenes.menu.menuEntities;

import se.chalmers.get_rect.game.screens.GameScreen;
import se.chalmers.get_rect.utilities.Point;

public interface IButton {
    void pressButton(GameScreen game);
    Point getPosition();
    boolean isActive();
    void setActive(boolean value);
}
