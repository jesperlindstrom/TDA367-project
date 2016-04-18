package se.chalmers.get_rect.game.scenes.menu.menuEntities;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.utilities.Point;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractGridModel {

    private Point currentButton;
    private Map<Point, IButton> itemMap;

    public AbstractGridModel() {
        itemMap = new HashMap<>();
    }

    protected void addToMap(Point point, IButton item) {
        itemMap.put(point, item);
    }

    protected void addToMap(int x, int y, IButton item) {
        addToMap(new Point(x, y), item);
    }

    public void draw(IGraphicsAdapter graphics) {
        for (IButton b : itemMap.values()) {
            graphics.draw(b.getImgPath(), b.getPosition());
        }
        graphics.draw("img/pauseMenu/buttons/pause_menu_button_overlay.png", getCurrentlyMarkedButton().getPosition());
    }

    public IButton getCurrentlyMarkedButton() {
        return itemMap.get(currentButton);
    }

    public void moveMarkDown() {
        if (itemMap.containsKey(currentButton.addY(1))) {
            currentButton = new Point(currentButton.addY(1));
        } else {
            //Wat to do..
        }
    }

    public void moveMarkUp() {
        if (itemMap.containsKey(currentButton.addY(-1))) {
            currentButton = new Point(currentButton.addY(-1));
        } else {
            // wat to do
        }
    }

    public void moveMarkLeft() {
        if (itemMap.containsKey(currentButton.addX(-1))) {
            currentButton = new Point(currentButton.addX(-1));
        } else {
            // wat to do
        }
    }

    public void moveMarkRight() {
        if (itemMap.containsKey(currentButton.addX(1))) {
            currentButton = new Point(currentButton.addX(1));
        } else {
            // wat to do
        }
    }

    public void setIndex(Point point) {
        if (itemMap.containsKey(point)) {
            currentButton = new Point(point);
        } else {
            System.out.println("No such index");
        }
    }

    public Map<Point, IButton> getItemMap() {
        return itemMap;
    }
}
