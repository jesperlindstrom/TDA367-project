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
        for (int i = 1; i < itemMap.size(); i++) {
            if (itemMap.containsKey(currentButton.addY(i)) && !disabled.contains(currentButton.addY(i))) {
                currentButton = new Point(currentButton.addY(i));
                return;
            }
        }
    }

    public void moveMarkUp() {
        for (int i = 1; i < itemMap.size(); i++) {
            if (itemMap.containsKey(currentButton.addY(-i)) && !disabled.contains(currentButton.addY(-i))) {
                currentButton = new Point(currentButton.addY(-i));
                return;
            }
        }
    }

    public void moveMarkLeft() {
        for (int i = 1; i < itemMap.size(); i++) {
            if (itemMap.containsKey(currentButton.addX(-i)) && !disabled.contains(currentButton.addX(-i))) {
                currentButton = new Point(currentButton.addX(-i));
                return;
            }
        }
    }

    public void moveMarkRight() {
        for (int i = 1; i < itemMap.size(); i++) {
            if (itemMap.containsKey(currentButton.addX(i)) && !disabled.contains(currentButton.addX(i))) {
                currentButton = new Point(currentButton.addX(i));
                return;
            }
        }
    }

    public void setIndex(Point point) {
        currentButton = new Point(point);
        if (!itemMap.containsKey(point)) {
            System.out.println("no button at " + point);
            moveMarkDown();
            moveMarkRight();
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
