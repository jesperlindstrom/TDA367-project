package se.chalmers.get_rect.game;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import se.chalmers.get_rect.game.window.model.ErrorWindow;
import se.chalmers.get_rect.states.StateManager;

public class ErrorHandler {
    @Inject @Named("Window") private StateManager windowManager;
    private ErrorWindow errorWindow;

    public ErrorHandler() {
        //errorWindow = ((ErrorWindow)windowManager.getState(GameConfig.ERROR_WINDOW).getModel()).setMessage(e.getMessage());
    }

    public void showError(String message) {
        /*((ErrorWindow)windowManager.getState(GameConfig.ERROR_WINDOW).getModel()).setMessage(e.getMessage());
        windowManager.set(GameConfig.ERROR_WINDOW);
        System.out.println(e.getMessage());*/
    }
}
