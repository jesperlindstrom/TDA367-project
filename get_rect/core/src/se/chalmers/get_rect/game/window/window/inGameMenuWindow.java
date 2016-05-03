package se.chalmers.get_rect.game.window.window;

import se.chalmers.get_rect.adapters.IInputAdapter;
import se.chalmers.get_rect.game.CameraManager;
import se.chalmers.get_rect.game.IGame;
import se.chalmers.get_rect.game.window.AbstractGridWindow;
import se.chalmers.get_rect.game.window.GridController;
import se.chalmers.get_rect.game.window.model.inGameMenu;
import se.chalmers.get_rect.game.window.view.inGameMenuView;
import se.chalmers.get_rect.utilities.Point;

public class inGameMenuWindow extends AbstractGridWindow {
    private inGameMenu model;

    public inGameMenuWindow(IGame game, IInputAdapter input, CameraManager camera) {
        super();
        this.model = new inGameMenu(game);
        setView(new inGameMenuView(camera, model));
        setController(new GridController(model, input));
    }

    @Override
    public void enteringState(Integer previousStateName) {
        model.setIndex(new Point(0, 0));
    }

}
