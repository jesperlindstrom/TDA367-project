package se.chalmers.get_rect.game.input;

import se.chalmers.get_rect.adapters.IControllerInputAdapter;
import se.chalmers.get_rect.adapters.IKeyboardInputAdapter;
import se.chalmers.get_rect.utilities.Point;

public class GameInput {
    private XboxController controller;
    private Keyboard keyboard;
    private Point nameInProgress = new Point(0, 0);

    public GameInput(IKeyboardInputAdapter keyboardAdapter, IControllerInputAdapter controllerAdapter) {
        controller = new XboxController(controllerAdapter);
        keyboard = new Keyboard(keyboardAdapter);
    }

    public Point getMousePosition() {
        return keyboard.getMousePosition();
    }

    public boolean isKeyPressed(Actions key) {
        if (key.equals(Actions.SHOOT))
            return (!keyboard.getAim().equals(nameInProgress) || (!controller.getAim().equals(nameInProgress)));

        return keyboard.isKeyPressed(key) || controller.isKeyPressed(key);
    }

    public boolean isKeyJustPressed(Actions key) {
        if (key.equals(Actions.SHOOT))
            return (!keyboard.getAim().equals(nameInProgress) || (!controller.getAim().equals(nameInProgress)));

        return keyboard.isKeyJustPressed(key) || controller.isKeyJustPressed(key);
    }

    public Point getAim() {
        return controller.getAim().add(keyboard.getAim()).normalize();
    }
}
