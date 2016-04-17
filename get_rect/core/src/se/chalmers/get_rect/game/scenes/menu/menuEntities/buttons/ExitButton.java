package se.chalmers.get_rect.game.scenes.menu.menuEntities.buttons;

import se.chalmers.get_rect.game.scenes.menu.menuEntities.IButton;
import se.chalmers.get_rect.game.screens.GameScreen;
import se.chalmers.get_rect.utilities.Point;

public class ExitButton implements IButton {

    private Point position;
    private boolean active;

    public ExitButton(Point position) {
        this.position = position;
    }

    @Override
    public void pressButton(GameScreen game) {
        game.exit();
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean value) {
        active = value;
    }
}
