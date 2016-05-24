package se.chalmers.get_rect.adapters.libGDX;

import com.badlogic.gdx.controllers.*;
import se.chalmers.get_rect.adapters.IControllerInputAdapter;
import se.chalmers.get_rect.game.GameConfig;
import se.chalmers.get_rect.utilities.Point;

import java.util.HashMap;
import java.util.Map;

public class LibGDXControllerInputAdapter extends ControllerAdapter implements IControllerInputAdapter {

    private Controller controller;
    private Map<Keys, Integer> keyMap;
    private Map<Integer, Boolean> downMap;
    private Map<Integer, Boolean> releasedMap;
    private Point rightStick;

    private static final int L_VERTICAL = Xbox.L_STICK_VERTICAL_AXIS;
    private static final int L_HORIZONTAL = Xbox.L_STICK_HORIZONTAL_AXIS;
    private static final int R_VERTICAL = Xbox.R_STICK_VERTICAL_AXIS;
    private static final int R_HORIZONTAL = Xbox.R_STICK_HORIZONTAL_AXIS;
    private static final int L_TRIGGER = Xbox.L_TRIGGER;
    private static final int R_TRIGGER = Xbox.R_TRIGGER;
    private static final float SENSITIVITY = 0.8f;

    public LibGDXControllerInputAdapter() {
        controller = getXboxController();
        rightStick = new Point();
        initMaps();
    }

    private Controller getXboxController() {
        Controllers.addListener(this);
        for (Controller c : Controllers.getControllers()) {
            return c;
        }
        return null;
    }

    /**
     * Maps our Keys with the corresponding key for libGDX.
     */
    private void initMaps(){
        this.keyMap = new HashMap<>();
        keyMap.put(Keys.X, Xbox.X);
        keyMap.put(Keys.Y, Xbox.Y);
        keyMap.put(Keys.B, Xbox.B);
        keyMap.put(Keys.A, Xbox.A);
        keyMap.put(Keys.LB, Xbox.L_BUMPER);
        keyMap.put(Keys.RB, Xbox.R_BUMPER);
        keyMap.put(Keys.BACK, Xbox.BACK);
        keyMap.put(Keys.START, Xbox.START);
        keyMap.put(Keys.L_LEFT, Xbox.L_STICK_LEFT);
        keyMap.put(Keys.L_RIGHT, Xbox.L_STICK_RIGHT);
        keyMap.put(Keys.L_UP, Xbox.L_STICK_UP);
        keyMap.put(Keys.L_DOWN, Xbox.L_STICK_DOWN);
        keyMap.put(Keys.R_LEFT, Xbox.R_STICK_LEFT);
        keyMap.put(Keys.R_RIGHT, Xbox.R_STICK_RIGHT);
        keyMap.put(Keys.R_UP, Xbox.R_STICK_UP);
        keyMap.put(Keys.R_DOWN, Xbox.R_STICK_DOWN);
        keyMap.put(Keys.L_TRIGGER, Xbox.L_TRIGGER + Xbox.OFFSET*2);
        keyMap.put(Keys.R_TRIGGER, Xbox.R_TRIGGER + Xbox.OFFSET*2);
        keyMap.put(Keys.DPAD_CENTER, Xbox.DPAD_CENTER);
        keyMap.put(Keys.DPAD_UP, Xbox.DPAD_UP);
        keyMap.put(Keys.DPAD_DOWN, Xbox.DPAD_DOWN);
        keyMap.put(Keys.DPAD_RIGHT, Xbox.DPAD_RIGHT);
        keyMap.put(Keys.DPAD_LEFT, Xbox.DPAD_LEFT);



        this.downMap = new HashMap<>();
        downMap.put(keyMap.get(Keys.A), false);
        downMap.put(keyMap.get(Keys.X), false);
        downMap.put(keyMap.get(Keys.Y), false);
        downMap.put(keyMap.get(Keys.B), false);
        downMap.put(keyMap.get(Keys.LB), false);
        downMap.put(keyMap.get(Keys.RB), false);
        downMap.put(keyMap.get(Keys.BACK), false);
        downMap.put(keyMap.get(Keys.START), false);
        downMap.put(keyMap.get(Keys.L_LEFT), false);
        downMap.put(keyMap.get(Keys.L_RIGHT), false);
        downMap.put(keyMap.get(Keys.L_UP), false);
        downMap.put(keyMap.get(Keys.L_DOWN), false);
        downMap.put(keyMap.get(Keys.R_LEFT), false);
        downMap.put(keyMap.get(Keys.R_UP), false);
        downMap.put(keyMap.get(Keys.R_RIGHT), false);
        downMap.put(keyMap.get(Keys.R_DOWN), false);
        downMap.put(keyMap.get(Keys.L_TRIGGER), false);
        downMap.put(keyMap.get(Keys.R_TRIGGER), false);
        downMap.put(keyMap.get(Keys.DPAD_LEFT), false);
        downMap.put(keyMap.get(Keys.DPAD_UP), false);
        downMap.put(keyMap.get(Keys.DPAD_RIGHT), false);
        downMap.put(keyMap.get(Keys.DPAD_DOWN), false);
        downMap.put(keyMap.get(Keys.DPAD_CENTER), false);

        this.releasedMap = new HashMap<>();
        releasedMap.put(keyMap.get(Keys.A), false);
        releasedMap.put(keyMap.get(Keys.X), false);
        releasedMap.put(keyMap.get(Keys.Y), false);
        releasedMap.put(keyMap.get(Keys.B), false);
        releasedMap.put(keyMap.get(Keys.LB), false);
        releasedMap.put(keyMap.get(Keys.RB), false);
        releasedMap.put(keyMap.get(Keys.BACK), false);
        releasedMap.put(keyMap.get(Keys.START), false);
        releasedMap.put(keyMap.get(Keys.L_LEFT), false);
        releasedMap.put(keyMap.get(Keys.L_RIGHT), false);
        releasedMap.put(keyMap.get(Keys.L_UP), false);
        releasedMap.put(keyMap.get(Keys.L_DOWN), false);
        releasedMap.put(keyMap.get(Keys.R_LEFT), false);
        releasedMap.put(keyMap.get(Keys.R_UP), false);
        releasedMap.put(keyMap.get(Keys.R_RIGHT), false);
        releasedMap.put(keyMap.get(Keys.R_DOWN), false);
        releasedMap.put(keyMap.get(Keys.L_TRIGGER), false);
        releasedMap.put(keyMap.get(Keys.R_TRIGGER), false);
        releasedMap.put(keyMap.get(Keys.DPAD_LEFT), false);
        releasedMap.put(keyMap.get(Keys.DPAD_UP), false);
        releasedMap.put(keyMap.get(Keys.DPAD_RIGHT), false);
        releasedMap.put(keyMap.get(Keys.DPAD_DOWN), false);
        releasedMap.put(keyMap.get(Keys.DPAD_CENTER), false);
    }

    @Override
    public boolean isKeyPressed(Keys key) {
        return keyMap.containsKey(key) && controller != null && downMap.get(keyMap.get(key));
    }

    @Override
    public boolean isKeyJustPressed(Keys key) {
        boolean status = keyMap.containsKey(key) && controller != null && releasedMap.get(keyMap.get(key));
        releasedMap.put(keyMap.get(key), false);
        return status;
    }

    @Override
    public Point getDirection() {
        if (controller == null) return new Point(0, 0);
        return new Point(rightStick).multiply(new Point(1, -1));
    }

    @Override
    public void connected(Controller controller) {
        controller.addListener(this);
        this.controller = controller;
    }

    @Override
    public boolean buttonDown(Controller controller, int buttonIndex) {
        if (!GameConfig.DISABLE_ALL && GameConfig.PRINT_CONTROLLERVALUES)System.out.println("button = " + buttonIndex);
        downMap.put(buttonIndex, true);
        releasedMap.put(buttonIndex, true);
        return super.buttonDown(controller, buttonIndex);
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonIndex) {
        downMap.put(buttonIndex, false);
        return super.buttonUp(controller, buttonIndex);
    }

    @Override
    public boolean axisMoved(Controller controller, int axisIndex, float value) {
        if (axisIndex == R_HORIZONTAL) {
            rightStick = rightStick.normalize(value, rightStick.getY());
        }
        if (axisIndex == R_VERTICAL) {
            rightStick = rightStick.normalize(rightStick.getX(), value);
        }
        downMap.put(getAxisIndex(axisIndex, value), Math.abs(value) > SENSITIVITY);
        releasedMap.put(getAxisIndex(axisIndex, value), Math.abs(value) > SENSITIVITY);
        return super.axisMoved(controller, axisIndex, value);
    }

    private int getAxisIndex(int axisIndex, float value) {
        if (!GameConfig.DISABLE_ALL && GameConfig.PRINT_CONTROLLERVALUES && Math.abs(value) > SENSITIVITY)System.out.println("Axis index = " + axisIndex);
        int index = -1;
        if (axisIndex == L_HORIZONTAL) {
            index = value < 0 ? keyMap.get(Keys.L_LEFT) : keyMap.get(Keys.L_RIGHT);
        }
        if (axisIndex == L_VERTICAL) {
            index = value > 0 ? keyMap.get(Keys.L_DOWN) : keyMap.get(Keys.L_UP);
        }
        if (axisIndex == R_HORIZONTAL) {
            index = value < 0 ? keyMap.get(Keys.R_LEFT) : keyMap.get(Keys.R_RIGHT);
        }
        if (axisIndex == R_VERTICAL) {
            index = value < 0 ? keyMap.get(Keys.R_DOWN) : keyMap.get(Keys.R_UP);
        }
        if (L_TRIGGER == R_TRIGGER && R_TRIGGER == axisIndex) {
            index = value > 0 ? keyMap.get(Keys.R_TRIGGER) : keyMap.get(Keys.L_TRIGGER);
        } else {
            if (axisIndex == R_TRIGGER)
                index = value > -SENSITIVITY ? keyMap.get(Keys.R_TRIGGER) : -1;
            if (axisIndex == L_TRIGGER)
                index = value > -SENSITIVITY ? keyMap.get(Keys.L_TRIGGER) : -1;
        }
        return index;
    }

    @Override
    public boolean povMoved(Controller controller, int povIndex, PovDirection value) {
        if (!GameConfig.DISABLE_ALL && GameConfig.PRINT_CONTROLLERVALUES)System.out.println("dpad = " + value.ordinal());
        if (povIndex + Xbox.OFFSET == keyMap.get(Keys.DPAD_CENTER)) {
            for (int dpadIndex = Xbox.OFFSET+1; dpadIndex < Xbox.OFFSET+5; dpadIndex++) {
                downMap.put(dpadIndex, false);
            }
        }
        downMap.put(value.ordinal() + Xbox.OFFSET, true);
        releasedMap.put(value.ordinal() + Xbox.OFFSET, true);
        return super.povMoved(controller, povIndex, value);
    }
}
