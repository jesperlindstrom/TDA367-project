package se.chalmers.get_rect.game.gui;

import se.chalmers.get_rect.utilities.Point;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractGridModel {
    public interface IActionHandler {
        void executeAction();
    }

    private Point currentButton;
    private Map<Point, IActionHandler> itemMap;

    public AbstractGridModel() {
        itemMap = new HashMap<>();
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
            currentButton = new Point(currentButton.addY(1));
        }
    }

    public void moveMarkUp() {
        if (itemMap.containsKey(currentButton.addY(-1))) {
            currentButton = new Point(currentButton.addY(-1));
        }
    }

    public void moveMarkLeft() {
        if (itemMap.containsKey(currentButton.addX(-1))) {
            currentButton = new Point(currentButton.addX(-1));
        }
    }

    public void moveMarkRight() {
        if (itemMap.containsKey(currentButton.addX(1))) {
            currentButton = new Point(currentButton.addX(1));
        }
    }

    public void setIndex(Point point) {
        if (itemMap.containsKey(point)) {
            currentButton = new Point(point);
        } else {
            System.out.println("No such index");
        }
    }

    public Point getCurrentButton() {
        return currentButton;
    }
}
