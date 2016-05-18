package se.chalmers.get_rect.game;

import se.chalmers.get_rect.adapters.IControllerInputAdapter;
import se.chalmers.get_rect.adapters.IKeyboardInputAdapter;
import se.chalmers.get_rect.utilities.Point;

import java.util.HashMap;
import java.util.Map;

public class GameInput {

    private IControllerInputAdapter controller;
    private IKeyboardInputAdapter keyboard;
    private Map<Actions, IKeyboardInputAdapter.Keys> keyboardMap;
    private Map<Actions, IControllerInputAdapter.Keys> controllerMap;
    private Point nameInProgress;

    public GameInput(IKeyboardInputAdapter keyboard, IControllerInputAdapter controller) {
        nameInProgress = new Point();
        this.controller = controller;
        this.keyboard = keyboard;
        fillKeyboardMap();
        fillControllerMap();
    }

    public Point getMousePosition() {
        if (keyboard.getMousePosition() == null) return new Point(0 ,0);
        return keyboard.getMousePosition();
    }

    public boolean isKeyPressed(Actions key) {
            if (key.equals(Actions.SHOOT))
                return (!getKeyboardDirection().equals(nameInProgress) || (!controller.getDirection().equals(nameInProgress)));
            return (keyboardMap.containsKey(key) && keyboard.isKeyPressed(keyboardMap.get(key)))
                    || (controllerMap.containsKey(key) && controller.isKeyPressed(controllerMap.get(key)));
    }

    public boolean isKeyJustPressed(Actions key) {
        if (key.equals(Actions.SHOOT))
            return (!getKeyboardDirection().equals(nameInProgress) || (!controller.getDirection().equals(nameInProgress)));
        return (keyboardMap.containsKey(key) && keyboard.isKeyJustPressed(keyboardMap.get(key)))
                || (controllerMap.containsKey(key) && controller.isKeyJustPressed(controllerMap.get(key)));
    }

    public Point getAim() {
        return controller.getDirection().add(getKeyboardDirection()).normalize();
    }

    public enum Actions {
        MOVE_LEFT, MOVE_UP, MOVE_RIGHT, MOVE_DOWN,
        MENU_UP, MENU_LEFT, MENU_DOWN, MENU_RIGHT,
        SWITCH_WEAPON, INTERACT, JUMP, CONFIRM, RESPAWN, MENU,
        SHOOT, EXIT_MENU
    }

    private void fillKeyboardMap() {
        keyboardMap = new HashMap<>();
        keyboardMap.put(Actions.MOVE_LEFT, IKeyboardInputAdapter.Keys.A);
        keyboardMap.put(Actions.MOVE_UP, IKeyboardInputAdapter.Keys.W);
        keyboardMap.put(Actions.MOVE_RIGHT, IKeyboardInputAdapter.Keys.D);
        keyboardMap.put(Actions.MOVE_DOWN, IKeyboardInputAdapter.Keys.S);
        keyboardMap.put(Actions.MENU_LEFT, IKeyboardInputAdapter.Keys.LEFT_KEY);
        keyboardMap.put(Actions.MENU_UP, IKeyboardInputAdapter.Keys.UP_KEY);
        keyboardMap.put(Actions.MENU_RIGHT, IKeyboardInputAdapter.Keys.RIGHT_KEY);
        keyboardMap.put(Actions.MENU_DOWN, IKeyboardInputAdapter.Keys.DOWN_KEY);
        keyboardMap.put(Actions.SWITCH_WEAPON, IKeyboardInputAdapter.Keys.Q);
        keyboardMap.put(Actions.INTERACT, IKeyboardInputAdapter.Keys.E);
        keyboardMap.put(Actions.JUMP, IKeyboardInputAdapter.Keys.SPACE);
        keyboardMap.put(Actions.CONFIRM, IKeyboardInputAdapter.Keys.ENTER);
        keyboardMap.put(Actions.MENU, IKeyboardInputAdapter.Keys.ESC);
        keyboardMap.put(Actions.EXIT_MENU, IKeyboardInputAdapter.Keys.SPACE);
    }

    private void fillControllerMap() {
        controllerMap = new HashMap<>();
        controllerMap.put(Actions.MOVE_LEFT, IControllerInputAdapter.Keys.L_LEFT);
        controllerMap.put(Actions.MOVE_UP, IControllerInputAdapter.Keys.L_UP);
        controllerMap.put(Actions.MOVE_RIGHT, IControllerInputAdapter.Keys.L_RIGHT);
        controllerMap.put(Actions.MOVE_DOWN, IControllerInputAdapter.Keys.L_DOWN);
        controllerMap.put(Actions.MENU_LEFT, IControllerInputAdapter.Keys.DPAD_LEFT);
        controllerMap.put(Actions.MENU_UP, IControllerInputAdapter.Keys.DPAD_UP);
        controllerMap.put(Actions.MENU_RIGHT, IControllerInputAdapter.Keys.DPAD_RIGHT);
        controllerMap.put(Actions.MENU_DOWN, IControllerInputAdapter.Keys.DPAD_DOWN);
        controllerMap.put(Actions.SWITCH_WEAPON, IControllerInputAdapter.Keys.Y);
        controllerMap.put(Actions.INTERACT, IControllerInputAdapter.Keys.A);
        controllerMap.put(Actions.JUMP, IControllerInputAdapter.Keys.R_TRIGGER);
        controllerMap.put(Actions.CONFIRM, IControllerInputAdapter.Keys.A);
        controllerMap.put(Actions.MENU, IControllerInputAdapter.Keys.START);
        controllerMap.put(Actions.EXIT_MENU, IControllerInputAdapter.Keys.B);

    }

    private Point getKeyboardDirection() {
        Point direction = new Point(0, 0);

        if (keyboard.isKeyJustPressed(IKeyboardInputAdapter.Keys.LEFT_KEY)) {
            direction = direction.addX(-1);
        }
        if (keyboard.isKeyJustPressed(IKeyboardInputAdapter.Keys.UP_KEY)) {
            direction = direction.addY(1);
        }
        if (keyboard.isKeyJustPressed(IKeyboardInputAdapter.Keys.RIGHT_KEY)) {
            direction = direction.addX(1);
        }
        if (keyboard.isKeyJustPressed(IKeyboardInputAdapter.Keys.DOWN_KEY)) {
            direction = direction.addY(-1);
        }

        return direction;
    }
}
