package se.chalmers.get_rect.game.scenes.menu.menuEntities.buttons;

import se.chalmers.get_rect.game.scenes.menu.menuEntities.IButton;
import se.chalmers.get_rect.game.screens.GameScreen;
import se.chalmers.get_rect.utilities.Point;

public class ContinueButton implements IButton {


    private Point position;
    private boolean active;

    public ContinueButton(Point position) {
        this.position = position;
    }

    @Override
    public void pressButton(GameScreen game) {
        game.setMenuActive(false);
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
