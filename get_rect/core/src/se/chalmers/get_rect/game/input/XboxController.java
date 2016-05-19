package se.chalmers.get_rect.game.input;

import se.chalmers.get_rect.adapters.IControllerInputAdapter;
import se.chalmers.get_rect.utilities.Point;

import java.util.HashMap;
import java.util.Map;

public class XboxController {
    private IControllerInputAdapter controller;
    private Map<Actions, IControllerInputAdapter.Keys> keys;

    public XboxController(IControllerInputAdapter controller) {
        this.controller = controller;

        keys = new HashMap<>();
        keys.put(Actions.MOVE_LEFT, IControllerInputAdapter.Keys.L_LEFT);
        keys.put(Actions.MOVE_UP, IControllerInputAdapter.Keys.L_UP);
        keys.put(Actions.MOVE_RIGHT, IControllerInputAdapter.Keys.L_RIGHT);
        keys.put(Actions.MOVE_DOWN, IControllerInputAdapter.Keys.L_DOWN);
        keys.put(Actions.MENU_LEFT, IControllerInputAdapter.Keys.DPAD_LEFT);
        keys.put(Actions.MENU_UP, IControllerInputAdapter.Keys.DPAD_UP);
        keys.put(Actions.MENU_RIGHT, IControllerInputAdapter.Keys.DPAD_RIGHT);
        keys.put(Actions.MENU_DOWN, IControllerInputAdapter.Keys.DPAD_DOWN);
        keys.put(Actions.SWITCH_WEAPON, IControllerInputAdapter.Keys.Y);
        keys.put(Actions.INTERACT, IControllerInputAdapter.Keys.A);
        keys.put(Actions.JUMP, IControllerInputAdapter.Keys.R_TRIGGER);
        keys.put(Actions.CONFIRM, IControllerInputAdapter.Keys.A);
        keys.put(Actions.MENU, IControllerInputAdapter.Keys.START);
        keys.put(Actions.EXIT_MENU, IControllerInputAdapter.Keys.B);
    }

    public boolean isKeyPressed(Actions key) {
        return keys.containsKey(key) && controller.isKeyPressed(keys.get(key));
    }

    public boolean isKeyJustPressed(Actions key) {
        return keys.containsKey(key) && controller.isKeyJustPressed(keys.get(key));
    }

    public Point getAim() {
        return controller.getDirection().normalize();
    }
}
