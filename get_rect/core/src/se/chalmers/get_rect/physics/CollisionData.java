package se.chalmers.get_rect.physics;


import se.chalmers.get_rect.utilities.Side;

public class CollisionData {
    private boolean top;
    private boolean left;
    private boolean right;
    private boolean bottom;
    private IRectangleAdapter overlap;

    public void set(Side side) {
        switch (side) {
            case TOP: top = true; break;
            case LEFT: left = true; break;
            case RIGHT: right = true; break;
            case BOTTOM: bottom = true; break;
        }
    }

    public void set(CollisionData data) {
        if (data.top) top = true;
        if (data.left) left = true;
        if (data.right) right = true;
        if (data.bottom) bottom = true;
        overlap = data.getOverlap();
    }

    public void setSolidOverlap(IRectangleAdapter overlap) {
        this.overlap = overlap;
    }

    public IRectangleAdapter getOverlap() {
        return overlap;
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
