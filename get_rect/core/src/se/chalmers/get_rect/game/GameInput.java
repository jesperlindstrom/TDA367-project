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
    private Types active;

    public GameInput(IKeyboardInputAdapter keyboard, IControllerInputAdapter controller) {
        this.controller = controller;
        this.keyboard = keyboard;
        active = Types.KEYBOARD;
        fillKeyboardMap();
    }

    public Point getMousePosition() {
        if (keyboard.getMousePosition() == null) return new Point(0 ,0);
        return keyboard.getMousePosition();
    }

    public boolean isKeyPressed(Actions key) {
        if (active.equals(Types.KEYBOARD)) {
            if (key.equals(Actions.SHOOT))
                return !getKeyboardDirection().equals(new Point(0, 0));

            return keyboardMap.containsKey(key) && keyboard.isKeyPressed(keyboardMap.get(key));
        }
        if (active.equals(Types.CONTROLLER)) {
            if (key.equals(Actions.SHOOT))
                return !controller.getDirection().equals(new Point(0, 0));

            return controllerMap.containsKey(key) && controller.isKeyPressed(controllerMap.get(key));
        }
        return false;
    }

    public boolean isKeyJustPressed(Actions key) {
        if (active.equals(Types.KEYBOARD)) {
            if (key.equals(Actions.SHOOT)) return !getKeyboardDirection().equals(new Point(0, 0));
            return keyboardMap.containsKey(key) && keyboard.isKeyJustPressed(keyboardMap.get(key));
        }
        if (active.equals(Types.CONTROLLER)) {
            if (key.equals(Actions.SHOOT)) return !controller.getDirection().equals(new Point(0, 0));
            return controllerMap.containsKey(key) && controller.isKeyJustPressed(controllerMap.get(key));
        }
        return false;
    }

    public Point getAim() {
        if (active.equals(Types.CONTROLLER))
            return controller.getDirection();
        if (active.equals(Types.KEYBOARD))
            return getKeyboardDirection();
        return null;
    }

    public enum Actions {
        MOVE_LEFT, MOVE_UP, MOVE_RIGHT, MOVE_DOWN,
        MENU_UP, MENU_LEFT, MENU_DOWN, MENU_RIGHT,
        SWITCH_WEAPON, INTERACT, JUMP, CONFIRM, RESPAWN, MENU,
        SHOOT
    }

    private enum Types {
        KEYBOARD, CONTROLLER
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
    }

    private void fillControllerMap() {
        controllerMap = new HashMap<>();
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
