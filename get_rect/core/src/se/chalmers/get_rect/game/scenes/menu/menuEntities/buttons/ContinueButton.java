package se.chalmers.get_rect.game.scenes.menu.menuEntities.buttons;

import se.chalmers.get_rect.game.scenes.menu.menuEntities.IButton;
import se.chalmers.get_rect.game.screens.GameScreen;
import se.chalmers.get_rect.utilities.Point;

public class ContinueButton implements IButton {

    private Point position;

    public ContinueButton(Point position) {
        this.position = position;
    }

    @Override
    public void pressButton(GameScreen game) {
        game.setMenuActive(false);
    }

    @Override
    public String getImgPath() {
        return "img/pauseMenu/buttons/continue_button.png";
    }

    @Override
    public Point getPosition() {
        return position;
    }

}
