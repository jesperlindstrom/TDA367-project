package se.chalmers.get_rect.physics;

import se.chalmers.get_rect.utilities.Side;

public class SolidCollision {
    private boolean top;
    private boolean left;
    private boolean right;
    private boolean bottom;

    public void set(Side side) {
        switch (side) {
            case TOP: top = true; break;
            case LEFT: left = true; break;
            case RIGHT: right = true; break;
            case BOTTOM: bottom = true; break;
        }
    }

    public boolean top() {
        return top;
    }

    public boolean left() {
        return left;
    }

    public boolean right() {
        return right;
    }

    public boolean bottom() {
        return bottom;
    }
}
