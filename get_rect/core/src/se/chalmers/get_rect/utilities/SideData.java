package se.chalmers.get_rect.utilities;


public class SideData {
    private boolean top;
    private boolean left;
    private boolean right;
    private boolean bottom;
    private boolean isSolid;

    public void set(Side side) {
        switch (side) {
            case TOP: top = true;break;
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

    public boolean isSolid() {
        return isSolid;
    }

}
