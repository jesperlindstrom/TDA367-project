package se.chalmers.get_rect.adapters.libGDX;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.mappings.Xbox;
import se.chalmers.get_rect.adapters.IControllerInputAdapter;
import se.chalmers.get_rect.utilities.Point;

import java.util.HashMap;
import java.util.Map;

public class LibGDXControllerInputAdapter implements IControllerInputAdapter {

    private Map<Keys, Integer> keyMap;
    private Controller controller;


    public LibGDXControllerInputAdapter() {

        controller = getXboxController();
        initMaps();
    }

    private Controller getXboxController() {
        for (Controller c : Controllers.getControllers()) {
            if (Xbox.isXboxController(c)) {
                return c;
            }
        }
        return null;
    }

    public boolean isTranslatable(Keys key) {
        if (keyMap.get(key) != null) {
            return true;
        }
        return false;
    }



    /**
     * Maps our Keys with the corresponding key for libGDX.
     */
    private void initMaps(){
        this.keyMap = new HashMap<>();
        keyMap.put(Keys.A, Xbox.A);
        keyMap.put(Keys.X, Xbox.X);
        keyMap.put(Keys.Y, Xbox.Y);
        keyMap.put(Keys.B, Xbox.B);
        keyMap.put(Keys.LEFT, Xbox.DPAD_LEFT);
        keyMap.put(Keys.UP, Xbox.DPAD_UP);
        keyMap.put(Keys.RIGHT, Xbox.DPAD_RIGHT);
        keyMap.put(Keys.DOWN, Xbox.DPAD_DOWN);

    }

    @Override
    public boolean isKeyPressed(Keys key) {
        if (keyMap.containsKey(key))
            return controller.getButton(keyMap.get(key));
        return false;
    }

    @Override
    public boolean isKeyJustPressed(Keys key) {
        return false;
    }

    @Override
    public Point getDirection() {
        return new Point().normalize(controller.getAxis(Xbox.L_STICK_HORIZONTAL_AXIS), controller.getAxis(Xbox.L_STICK_VERTICAL_AXIS));
    }

}
