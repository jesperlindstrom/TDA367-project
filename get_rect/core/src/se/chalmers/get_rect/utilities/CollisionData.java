package se.chalmers.get_rect.utilities;


import se.chalmers.get_rect.physics.IRectangleAdapter;

import java.util.ArrayList;
import java.util.List;

public class CollisionData {
    private boolean top;
    private boolean left;
    private boolean right;
    private boolean bottom;
    private List<IRectangleAdapter> overlapList;

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
        overlapList = data.getOverlapList();
    }

    public void addSolidOverlap(IRectangleAdapter overlap) {
        if (overlapList == null)
            overlapList = new ArrayList<>();

        overlapList.add(overlap);
    }

    public List<IRectangleAdapter> getOverlapList() {
        return overlapList;
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
