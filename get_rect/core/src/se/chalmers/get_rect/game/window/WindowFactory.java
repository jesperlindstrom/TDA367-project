package se.chalmers.get_rect.game.window;

import com.google.inject.Inject;
import se.chalmers.get_rect.adapters.IAssetManagerAdapter;
import se.chalmers.get_rect.game.window.model.*;
import se.chalmers.get_rect.game.input.GameInput;
import se.chalmers.get_rect.game.entities.EntityNotFoundException;
import se.chalmers.get_rect.game.entities.ICamera;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.entities.item.WeaponFactory;
import se.chalmers.get_rect.game.entities.item.WeaponRepository;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.game.window.controller.GridController;
import se.chalmers.get_rect.game.window.controller.IWindowController;
import se.chalmers.get_rect.game.window.controller.SplashController;
import se.chalmers.get_rect.game.window.view.*;
import se.chalmers.get_rect.states.StateManager;

public class WindowFactory {
    @Inject private IAssetManagerAdapter assetManager;
    @Inject private StateManager<IWindowController> windowManager;
    @Inject private ICamera camera;
    @Inject private GameInput gameInput;
    @Inject private IGameControl game;
    @Inject private Player player;
    @Inject private WeaponRepository weaponRepository;
    @Inject private WeaponFactory weaponFactory;

    public IWindowController makeSplash() {
        SplashModel model = new SplashModel(assetManager, windowManager);
        IWindowView view = new SplashView(model, camera);
        return new SplashController(model, view);
    }

    public IWindowController makeMainMenu() {
        MainMenu model = new MainMenu(game);
        IWindowView view = new MainMenuView(model, camera);
        return new GridController(gameInput, model, view);
    }

    public IWindowController makeInGameMenu() {
        InGameMenu model = new InGameMenu(game);
        IWindowView view = new InGameMenuView(model, camera);
        return new GridController(gameInput, model, view);
    }

    public IWindowController makeInventory() {
        Inventory model = new Inventory(player, weaponRepository);
        IWindowView view = new InventoryView(model, camera, weaponFactory);
        return new GridController(gameInput, model, view);
    }

    public IWindowController makeErrorWindow() {
        ErrorWindow model = new ErrorWindow(game);
        IWindowView view = new ErrorWindowView(model, camera);
        return new GridController(gameInput, model, view);
    }
}
