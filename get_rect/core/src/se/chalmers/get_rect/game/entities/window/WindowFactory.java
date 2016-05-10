package se.chalmers.get_rect.game.entities.window;

import com.google.inject.Inject;
import se.chalmers.get_rect.adapters.IAssetManagerAdapter;
import se.chalmers.get_rect.adapters.IInputAdapter;
import se.chalmers.get_rect.game.IGame;
import se.chalmers.get_rect.game.entities.EntityNotFoundException;
import se.chalmers.get_rect.game.entities.ICamera;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.entities.window.controller.GridController;
import se.chalmers.get_rect.game.entities.window.controller.IWindowController;
import se.chalmers.get_rect.game.entities.window.controller.SplashController;
import se.chalmers.get_rect.game.entities.window.model.InGameMenu;
import se.chalmers.get_rect.game.entities.window.model.MainMenu;
import se.chalmers.get_rect.game.entities.window.model.SplashModel;
import se.chalmers.get_rect.game.entities.window.view.InGameMenuView;
import se.chalmers.get_rect.game.entities.window.view.MainMenuView;
import se.chalmers.get_rect.game.entities.window.view.SplashView;
import se.chalmers.get_rect.states.StateManager;

public class WindowFactory {
    private IAssetManagerAdapter assetManager;
    private StateManager<IWindowController> windowManager;
    private ICamera camera;
    private IInputAdapter input;
    private IGame game;

    @Inject
    public WindowFactory(IAssetManagerAdapter assetManager, StateManager<IWindowController> windowManager, ICamera camera, IInputAdapter input, IGame game) {
        this.assetManager = assetManager;
        this.windowManager = windowManager;
        this.camera = camera;
        this.input = input;
        this.game = game;
    }

    public IWindowController make(String type) {
        if (type.equals("splash"))
            return makeSplash();

        if (type.equals("mainMenu"))
            return makeMainMenu();

        if (type.equals("inGameMenu"))
            return makeInGameMenu();

        throw new EntityNotFoundException("window", type);
    }

    private IWindowController makeSplash() {
        SplashModel model = new SplashModel(assetManager, windowManager);
        IView view = new SplashView(model, camera);
        return new SplashController(model, view);
    }

    private IWindowController makeMainMenu() {
        MainMenu model = new MainMenu(game);
        IView view = new MainMenuView(model, camera);
        return new GridController(input, model, view);
    }

    private IWindowController makeInGameMenu() {
        InGameMenu model = new InGameMenu(game);
        IView view = new InGameMenuView(model, camera);
        return new GridController(input, model, view);
    }
}
