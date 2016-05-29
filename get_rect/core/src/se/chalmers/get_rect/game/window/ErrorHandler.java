package se.chalmers.get_rect.game.window;

import se.chalmers.get_rect.game.GameConfig;
import se.chalmers.get_rect.game.window.controller.IWindowController;
import se.chalmers.get_rect.game.window.model.ErrorWindow;
import se.chalmers.get_rect.states.StateManager;

public class ErrorHandler {
    private StateManager<IWindowController> windowManager;

    public ErrorHandler(StateManager<IWindowController> windowManager) {
        this.windowManager = windowManager;
    }

    public void showError(String message) {
        ErrorWindow window = getWindow();

        if (window == null) {
            throw new RuntimeException("The error window can't be displayed. Error: " + message);
        }

        window.setMessage(message);
        windowManager.set(GameConfig.ERROR_WINDOW);
    }

    private ErrorWindow getWindow() {
        IWindowController controller = windowManager.getState(GameConfig.ERROR_WINDOW);

        if (controller.getModel() instanceof ErrorWindow)
            return (ErrorWindow) controller.getModel();

        return null;
    }
}