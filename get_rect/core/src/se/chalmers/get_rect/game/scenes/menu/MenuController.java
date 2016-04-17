package se.chalmers.get_rect.game.scenes.menu;

import se.chalmers.get_rect.IGame;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.IInputAdapter;
import se.chalmers.get_rect.game.CameraManager;
import se.chalmers.get_rect.game.entities.IController;
import se.chalmers.get_rect.game.screens.GameScreen;

public class MenuController implements IController {

    private IInputAdapter input;
    private MenuModel model;
    private MenuView view;
    private GameScreen game;


    public MenuController(GameScreen game, IInputAdapter input, CameraManager camera) {
        this.input = input;
        model = new MenuModel(camera);
        view = new MenuView(model);
        this.game = game;

    }

    @Override
    public void update() {

        if (input.isKeyJustPressed(IInputAdapter.Keys.ENTER)) {
            model.pressCurrentButton(game);

        }
        if (input.isKeyJustPressed(IInputAdapter.Keys.UPKEY)) {
            model.moveMenuMarkerUp();
        }
        if (input.isKeyJustPressed(IInputAdapter.Keys.DOWNKEY)) {
            model.moveMenuMarkerDown();
        }


    }

    public void draw(IGraphicsAdapter graphics) {
        view.draw(graphics);
        model.draw(graphics);
    }
}
