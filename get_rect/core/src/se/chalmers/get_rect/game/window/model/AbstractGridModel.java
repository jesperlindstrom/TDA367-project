package se.chalmers.get_rect.game.window.model;

import se.chalmers.get_rect.utilities.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractGridModel {
    public interface IActionHandler {
        void executeAction();
    }

    private Point currentButton;
    private Map<Point, IActionHandler> itemMap;
    private List<Point> disabled;

    public AbstractGridModel() {
        itemMap = new HashMap<>();
        disabled = new ArrayList<>();
        currentButton = new Point(0, 0);
    }

    protected void addToMap(Point point, IActionHandler item) {
        itemMap.put(point, item);
    }

    protected void addToMap(int x, int y, IActionHandler item) {
        addToMap(new Point(x, y), item);
    }

    public IActionHandler getCurrentlyMarkedButton() {
        return itemMap.get(currentButton);
    }

    public void moveMarkDown() {
        if (itemMap.containsKey(currentButton.addY(1))) {
            setIndex(currentButton.addY(1));
        }
    }

    public void moveMarkUp() {
        if (itemMap.containsKey(currentButton.addY(-1))) {
            setIndex(currentButton.addY(-1));
        }
    }

    public void moveMarkLeft() {
        if (itemMap.containsKey(currentButton.addX(-1))) {
            setIndex(currentButton.addX(-1));
        }
    }

    public void moveMarkRight() {
        if (itemMap.containsKey(currentButton.addX(1))) {
            setIndex(currentButton.addX(1));
        }
    }

    public void setIndex(Point point) {
        if (itemMap.containsKey(point)) {
            currentButton = new Point(point);
        }
    }

    public void disableButton(Point index) {
        if (!disabled.contains(index)) {
            disabled.add(index);
        }
    }

    public void activateButton(Point index) {
        if (disabled.contains(index)) {
            disabled.remove(index);
        }
    }

    public void activateAll() {
        disabled.clear();
    }

    public boolean isButtonDisabled(Point index) {
        return disabled.contains(index);
    }

    public Point getCurrentlyMarked() {
        return currentButton;
    }

    public abstract void reset();

    public abstract boolean isAllowingRegularInput();
}
