package se.chalmers.get_rect.game.scenes.menu.menuEntities;

import se.chalmers.get_rect.game.scenes.menu.menuEntities.buttons.ContinueButton;
import se.chalmers.get_rect.game.scenes.menu.menuEntities.buttons.ExitButton;
import se.chalmers.get_rect.utilities.Point;

public class InGameMenu extends AbstractGridModel {

    public InGameMenu() {
        super();
        super.addToMap(0, 0, new ContinueButton(new Point(430, 390)));
        super.addToMap(0, 1, new ExitButton(new Point(430, 240)));
    }

}
