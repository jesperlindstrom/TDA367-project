package se.chalmers.get_rect.game.entities.window.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractView;
import se.chalmers.get_rect.game.entities.ICamera;
import se.chalmers.get_rect.game.entities.item.ItemFactory;
import se.chalmers.get_rect.game.entities.item.view.IWeaponView;
import se.chalmers.get_rect.game.entities.window.model.Inventory;
import se.chalmers.get_rect.utilities.Point;

import java.util.HashMap;
import java.util.Map;

public class InventoryView extends AbstractView {

    private ICamera camera;
    private Inventory inventory;
    private Map<Point, IWeaponView> weaponViewMap;
    private Point windowPosition;
    private static final int offset = 65;

    private static final String IMG_PATH = "img/window/";

    public InventoryView(Inventory model, ICamera camera, ItemFactory itemFactory) {
        this.camera = camera;
        this.inventory = model;
        this.weaponViewMap = new HashMap<>();
        inventory.getItemsMap().forEach((K, V) -> weaponViewMap.put(K, itemFactory.makeView(V)));
        windowPosition = new Point((int)camera.getAdapter().getWidth()*3/4 - 554/2, (int)camera.getAdapter().getWidth()/2 - 668/2);

    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        weaponViewMap.forEach((K, V) -> V.draw(graphics, getRealPosition(K)));
        graphics.draw(IMG_PATH+"chezzt.png", camera.getPosition().add(windowPosition));
    }

    public Point getRealPosition(Point gridPosition) {
        return windowPosition.add(60, 180).add(gridPosition.multiply(offset));
    }
}
