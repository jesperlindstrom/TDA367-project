package se.chalmers.get_rect.game.input;

import se.chalmers.get_rect.adapters.IKeyboardInputAdapter;
import se.chalmers.get_rect.utilities.Point;

import java.util.HashMap;
import java.util.Map;

public class Keyboard {
    private IKeyboardInputAdapter keyboard;
    private Map<Actions, IKeyboardInputAdapter.Keys> keys;

    public Keyboard(IKeyboardInputAdapter keyboard) {
        this.keyboard = keyboard;

        keys = new HashMap<>();
        keys.put(Actions.MOVE_LEFT, IKeyboardInputAdapter.Keys.A);
        keys.put(Actions.MOVE_UP, IKeyboardInputAdapter.Keys.W);
        keys.put(Actions.MOVE_RIGHT, IKeyboardInputAdapter.Keys.D);
        keys.put(Actions.MOVE_DOWN, IKeyboardInputAdapter.Keys.S);
        keys.put(Actions.MENU_LEFT, IKeyboardInputAdapter.Keys.LEFT_KEY);
        keys.put(Actions.MENU_UP, IKeyboardInputAdapter.Keys.UP_KEY);
        keys.put(Actions.MENU_RIGHT, IKeyboardInputAdapter.Keys.RIGHT_KEY);
        keys.put(Actions.MENU_DOWN, IKeyboardInputAdapter.Keys.DOWN_KEY);
        keys.put(Actions.SWITCH_WEAPON, IKeyboardInputAdapter.Keys.Q);
        keys.put(Actions.INTERACT, IKeyboardInputAdapter.Keys.E);
        keys.put(Actions.JUMP, IKeyboardInputAdapter.Keys.SPACE);
        keys.put(Actions.CONFIRM, IKeyboardInputAdapter.Keys.ENTER);
        keys.put(Actions.MENU, IKeyboardInputAdapter.Keys.ESC);
        keys.put(Actions.EXIT_MENU, IKeyboardInputAdapter.Keys.SPACE);
    }

    public Point getMousePosition() {
        if (keyboard.getMousePosition() == null)
            return new Point(0 ,0);

        return keyboard.getMousePosition();
    }

    public boolean isKeyPressed(Actions key) {
        return keys.containsKey(key) && keyboard.isKeyPressed(keys.get(key));
    }

    public boolean isKeyJustPressed(Actions key) {
        return keys.containsKey(key) && keyboard.isKeyJustPressed(keys.get(key));
    }

    public Point getAim() {
        Point direction = new Point(0, 0);

        if (keyboard.isKeyPressed(IKeyboardInputAdapter.Keys.LEFT_KEY)) {
            direction = direction.addX(-1);
        }
        if (keyboard.isKeyPressed(IKeyboardInputAdapter.Keys.UP_KEY)) {
            direction = direction.addY(1);
        }
        if (keyboard.isKeyPressed(IKeyboardInputAdapter.Keys.RIGHT_KEY)) {
            direction = direction.addX(1);
        }
        if (keyboard.isKeyPressed(IKeyboardInputAdapter.Keys.DOWN_KEY)) {
            direction = direction.addY(-1);
        }

        return direction;
    }
}
