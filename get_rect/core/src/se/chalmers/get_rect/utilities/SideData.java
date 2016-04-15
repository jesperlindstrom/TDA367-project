package se.chalmers.get_rect.utilities;

import java.util.ArrayList;
import java.util.List;

public class SideData {
    private boolean top;
    private boolean left;
    private boolean right;
    private boolean bottom;
    private List<Side> solidEdge = new ArrayList<>();

    public void set(Side side, boolean isSolid) {
        if(isSolid) {
            solidEdge.add(side);
        }
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

    public List<Side> getSolidEdge() {
        return solidEdge;
    }
}
