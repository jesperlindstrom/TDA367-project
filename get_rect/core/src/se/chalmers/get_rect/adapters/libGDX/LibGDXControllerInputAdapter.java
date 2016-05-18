package se.chalmers.get_rect.adapters.libGDX;

import com.badlogic.gdx.controllers.*;
import se.chalmers.get_rect.adapters.IControllerInputAdapter;
import se.chalmers.get_rect.utilities.Point;

import java.util.HashMap;
import java.util.Map;

public class LibGDXControllerInputAdapter extends ControllerAdapter implements IControllerInputAdapter {

    private Controller controller;
    private Map<Keys, Integer> keyMap;
    private Map<Integer, Boolean> downMap;
    private Map<Integer, Boolean> releasedMap;
    private static final int OFFSET = 50;
    private Point rightStick;
    private static final int L_VERTICAL = 0;
    private static final int L_HORIZONTAL = 1;
    private static final int R_VERTICAL = 2;
    private static final int R_HORIZONTAL = 3;

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
        keyMap.put(Keys.X, 2);
        keyMap.put(Keys.Y, 3);
        keyMap.put(Keys.B, 1);
        keyMap.put(Keys.A, 0);
        keyMap.put(Keys.LB, 4);
        keyMap.put(Keys.RB, 5);
        keyMap.put(Keys.SELECT, 6);
        keyMap.put(Keys.START, 7);
        keyMap.put(Keys.L_LEFT, 20);
        keyMap.put(Keys.L_RIGHT, 21);
        keyMap.put(Keys.L_UP, 22);
        keyMap.put(Keys.L_DOWN, 23);
        keyMap.put(Keys.R_LEFT, 30);
        keyMap.put(Keys.R_RIGHT, 31);
        keyMap.put(Keys.R_UP, 32);
        keyMap.put(Keys.R_DOWN, 33);
        keyMap.put(Keys.L_BUMPER, 40);
        keyMap.put(Keys.R_BUMPER, 41);
        keyMap.put(Keys.DPAD_CENTER, 0+OFFSET);
        keyMap.put(Keys.DPAD_UP, 1+OFFSET);
        keyMap.put(Keys.DPAD_DOWN, 2+OFFSET);
        keyMap.put(Keys.DPAD_RIGHT, 3+OFFSET);
        keyMap.put(Keys.DPAD_LEFT, 4+OFFSET);



        this.downMap = new HashMap<>();
        downMap.put(keyMap.get(Keys.A), false);
        downMap.put(keyMap.get(Keys.X), false);
        downMap.put(keyMap.get(Keys.Y), false);
        downMap.put(keyMap.get(Keys.B), false);
        downMap.put(keyMap.get(Keys.LB), false);
        downMap.put(keyMap.get(Keys.RB), false);
        downMap.put(keyMap.get(Keys.SELECT), false);
        downMap.put(keyMap.get(Keys.START), false);
        downMap.put(keyMap.get(Keys.L_LEFT), false);
        downMap.put(keyMap.get(Keys.L_RIGHT), false);
        downMap.put(keyMap.get(Keys.L_UP), false);
        downMap.put(keyMap.get(Keys.L_DOWN), false);
        downMap.put(keyMap.get(Keys.R_LEFT), false);
        downMap.put(keyMap.get(Keys.R_UP), false);
        downMap.put(keyMap.get(Keys.R_RIGHT), false);
        downMap.put(keyMap.get(Keys.R_DOWN), false);
        downMap.put(keyMap.get(Keys.L_BUMPER), false);
        downMap.put(keyMap.get(Keys.R_BUMPER), false);
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
        releasedMap.put(keyMap.get(Keys.SELECT), false);
        releasedMap.put(keyMap.get(Keys.START), false);
        releasedMap.put(keyMap.get(Keys.L_LEFT), false);
        releasedMap.put(keyMap.get(Keys.L_RIGHT), false);
        releasedMap.put(keyMap.get(Keys.L_UP), false);
        releasedMap.put(keyMap.get(Keys.L_DOWN), false);
        releasedMap.put(keyMap.get(Keys.R_LEFT), false);
        releasedMap.put(keyMap.get(Keys.R_UP), false);
        releasedMap.put(keyMap.get(Keys.R_RIGHT), false);
        releasedMap.put(keyMap.get(Keys.R_DOWN), false);
        releasedMap.put(keyMap.get(Keys.L_BUMPER), false);
        releasedMap.put(keyMap.get(Keys.R_BUMPER), false);
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
        return new Point(rightStick);
    }

    @Override
    public void connected(Controller controller) {
        controller.addListener(this);
        this.controller = controller;
    }

    @Override
    public boolean buttonDown(Controller controller, int buttonIndex) {
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
        downMap.put(getAxisIndex(axisIndex, value), Math.abs(value) > 0.7);
        releasedMap.put(getAxisIndex(axisIndex, value), Math.abs(value) > 0.7);
        return super.axisMoved(controller, axisIndex, value);
    }

    private int getAxisIndex(int axisIndex, float value) {
        if (axisIndex == L_HORIZONTAL) {
            return value < 0 ? keyMap.get(Keys.L_LEFT) : keyMap.get(Keys.L_RIGHT);
        }
        if (axisIndex == L_VERTICAL) {
            return value > 0 ? keyMap.get(Keys.L_DOWN) : keyMap.get(Keys.L_UP);
        }
        if (axisIndex == R_HORIZONTAL) {
            return value < 0 ? keyMap.get(Keys.R_LEFT) : keyMap.get(Keys.R_RIGHT);
        }
        if (axisIndex == R_VERTICAL) {
            return value > 0 ? keyMap.get(Keys.R_DOWN) : keyMap.get(Keys.R_UP);
        }
        return value < 0 ? keyMap.get(Keys.R_BUMPER) : keyMap.get(Keys.L_BUMPER);
    }

    @Override
    public boolean xSliderMoved(Controller controller, int sliderIndex, boolean value) {
        System.out.println("xslide moved = " + sliderIndex);
        return super.xSliderMoved(controller, sliderIndex, value);
    }

    @Override
    public boolean ySliderMoved(Controller controller, int sliderIndex, boolean value) {
        System.out.println("Y slider = " + sliderIndex);
        return super.ySliderMoved(controller, sliderIndex, value);
    }

    @Override
    public boolean povMoved(Controller controller, int povIndex, PovDirection value) {
        if (povIndex + OFFSET == keyMap.get(Keys.DPAD_CENTER)) {
            for (int dpadIndex = OFFSET+1; dpadIndex < OFFSET+5; dpadIndex++) {
                downMap.put(dpadIndex, false);
            }
        }
        downMap.put(value.ordinal() + OFFSET, true);
        releasedMap.put(value.ordinal() + OFFSET, true);
        return super.povMoved(controller, povIndex, value);
    }
}
