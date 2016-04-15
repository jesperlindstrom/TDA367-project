package se.chalmers.get_rect.utilities;


public class SideData {
    private boolean top;
    private boolean left;
    private boolean right;
    private boolean bottom;

    public void set(Side side) {
        switch (side) {
            case TOP: top = true;break;
            case LEFT: left = true; break;
            case RIGHT: right = true; break;
            case BOTTOM: bottom = true; break;
        }
    }

    public void set(SideData data) {
        if (data.top) top = true;
        if (data.left) left = true;
        if (data.right) right = true;
        if (data.bottom) bottom = true;
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
