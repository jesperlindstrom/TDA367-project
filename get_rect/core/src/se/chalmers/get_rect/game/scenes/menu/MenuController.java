package se.chalmers.get_rect.game.scenes.menu;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.IInputAdapter;
import se.chalmers.get_rect.game.CameraManager;
import se.chalmers.get_rect.game.entities.IController;
import se.chalmers.get_rect.game.scenes.menu.menuEntities.AbstractGridModel;
import se.chalmers.get_rect.game.scenes.menu.menuEntities.IButton;
import se.chalmers.get_rect.game.scenes.menu.menuEntities.InGameMenu;
import se.chalmers.get_rect.game.screens.GameScreen;
import se.chalmers.get_rect.utilities.Point;

public class MenuController implements IController {

    private IInputAdapter input;
    private AbstractGridModel model;
    private MenuView view;
    private GameScreen game;


    public MenuController(GameScreen game, IInputAdapter input, CameraManager camera) {
        this.input = input;
        model = new InGameMenu();
        view = new MenuView(camera, model);
        this.game = game;
        model.setIndex(new Point(0, 0));

    }

    @Override
    public void update() {

        if (input.isKeyJustPressed(IInputAdapter.Keys.ENTER)) {
            model.getCurrentlyMarkedButton().pressButton(game);
        }
        if (input.isKeyJustPressed(IInputAdapter.Keys.UPKEY)) {
            model.moveMarkUp();
        }
        if (input.isKeyJustPressed(IInputAdapter.Keys.DOWNKEY)) {
            model.moveMarkDown();
        }


    }

    public void draw(IGraphicsAdapter graphics) {
        view.draw(graphics);
    }
}
