package se.chalmers.get_rect.adapters.libGDX;

import com.badlogic.gdx.utils.SharedLibraryLoader;

public class Xbox {
    public static final int OFFSET;

    // Buttons
    public static final int A;
    public static final int B;
    public static final int X;
    public static final int Y;
    public static final int GUIDE;
    public static final int L_BUMPER;
    public static final int R_BUMPER;
    public static final int BACK;
    public static final int START;
    public static final int DPAD_CENTER;
    public static final int DPAD_UP;
    public static final int DPAD_DOWN;
    public static final int DPAD_LEFT;
    public static final int DPAD_RIGHT;
    public static final int L_STICK_LEFT;
    public static final int L_STICK_RIGHT;
    public static final int L_STICK_UP;
    public static final int L_STICK_DOWN;
    public static final int R_STICK_LEFT;
    public static final int R_STICK_RIGHT;
    public static final int R_STICK_UP;
    public static final int R_STICK_DOWN;
    // Axes
    /**
     * left trigger, -1 if not pressed, 1 if pressed
     **/
    public static final int L_TRIGGER;
    /**
     * right trigger, -1 if not pressed, 1 if pressed
     **/
    public static final int R_TRIGGER;
    /**
     * left stick vertical axis, -1 if up, 1 if down
     **/
    public static final int L_STICK_VERTICAL_AXIS;
    /**
     * left stick horizontal axis, -1 if left, 1 if right
     **/
    public static final int L_STICK_HORIZONTAL_AXIS;
    /**
     * right stick vertical axis, -1 if up, 1 if down
     **/
    public static final int R_STICK_VERTICAL_AXIS;
    /**
     * right stick horizontal axis, -1 if left, 1 if right
     **/
    public static final int R_STICK_HORIZONTAL_AXIS;

    static {
        if (SharedLibraryLoader.isWindows) {
            OFFSET = 50;
            A = 0;
            B = 1;
            X = 2;
            Y = 3;
            GUIDE = -1;
            L_BUMPER = 40;
            R_BUMPER = 41;
            BACK = 6;
            START = 7;
            DPAD_CENTER = 50;
            DPAD_UP = 51;
            DPAD_DOWN = 52;
            DPAD_RIGHT = 53;
            DPAD_LEFT = 54;
            L_TRIGGER = 4;
            R_TRIGGER = 5;
            L_STICK_VERTICAL_AXIS = 0;
            L_STICK_HORIZONTAL_AXIS = 1;
            R_STICK_VERTICAL_AXIS = 2;
            R_STICK_HORIZONTAL_AXIS = 3;
            L_STICK_LEFT = 20;
            L_STICK_RIGHT = 21;
            L_STICK_UP = 22;
            L_STICK_DOWN = 23;
            R_STICK_LEFT = 30;
            R_STICK_RIGHT = 31;
            R_STICK_UP = 32;
            R_STICK_DOWN = 33;

        } else if (SharedLibraryLoader.isLinux) {
            OFFSET = 50;
            A = 0;
            B = 1;
            X = 2;
            Y = 3;
            GUIDE = -1;
            L_BUMPER = 2;
            R_BUMPER = 5;
            BACK = 6;
            START = 7;
            DPAD_CENTER = 50;
            DPAD_UP = 51;
            DPAD_DOWN = 52;
            DPAD_LEFT = 53;
            DPAD_RIGHT = 54;
            L_TRIGGER = 4;
            R_TRIGGER = 5;
            L_STICK_VERTICAL_AXIS = 1;
            L_STICK_HORIZONTAL_AXIS = 0;
            R_STICK_VERTICAL_AXIS = 4;
            R_STICK_HORIZONTAL_AXIS = 3;
            L_STICK_LEFT = 20;
            L_STICK_RIGHT = 21;
            L_STICK_UP = 22;
            L_STICK_DOWN = 23;
            R_STICK_LEFT = 30;
            R_STICK_RIGHT = 31;
            R_STICK_UP = 32;
            R_STICK_DOWN = 33;

        } else if (SharedLibraryLoader.isMac) {
            OFFSET = 51;
            A = 11;
            B = 12;
            X = 13;
            Y = 14;
            GUIDE = 10;
            L_BUMPER = 8;
            R_BUMPER = 9;
            BACK = 5;
            START = 4;
            DPAD_CENTER = -1;
            DPAD_UP = 0;
            DPAD_DOWN = 1;
            DPAD_LEFT = 2;
            DPAD_RIGHT = 3;
            L_TRIGGER = 0;
            R_TRIGGER = 1;
            L_STICK_VERTICAL_AXIS = 3;
            L_STICK_HORIZONTAL_AXIS = 2;
            R_STICK_VERTICAL_AXIS = 5;
            R_STICK_HORIZONTAL_AXIS = 4;
            L_STICK_LEFT = 20;
            L_STICK_RIGHT = 21;
            L_STICK_UP = 22;
            L_STICK_DOWN = 23;
            R_STICK_LEFT = 30;
            R_STICK_RIGHT = 31;
            R_STICK_UP = 32;
            R_STICK_DOWN = 33;

        } else {
            OFFSET = 50;
            A = -1;
            B = -1;
            X = -1;
            Y = -1;
            GUIDE = -1;
            L_BUMPER = -1;
            R_BUMPER = -1;
            L_TRIGGER = -1;
            R_TRIGGER = -1;
            BACK = -1;
            START = -1;
            DPAD_CENTER = -1;
            DPAD_UP = -1;
            DPAD_DOWN = -1;
            DPAD_LEFT = -1;
            DPAD_RIGHT = -1;
            L_STICK_VERTICAL_AXIS = -1;
            L_STICK_HORIZONTAL_AXIS = -1;
            R_STICK_VERTICAL_AXIS = -1;
            R_STICK_HORIZONTAL_AXIS = -1;
            L_STICK_LEFT = 20;
            L_STICK_RIGHT = 21;
            L_STICK_UP = 22;
            L_STICK_DOWN = 23;
            R_STICK_LEFT = 30;
            R_STICK_RIGHT = 31;
            R_STICK_UP = 32;
            R_STICK_DOWN = 33;

        }
    }
}
