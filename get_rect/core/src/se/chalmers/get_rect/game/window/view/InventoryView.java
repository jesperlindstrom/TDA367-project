package se.chalmers.get_rect.game.window.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractView;
import se.chalmers.get_rect.game.entities.ICamera;
import se.chalmers.get_rect.game.entities.item.WeaponFactory;
import se.chalmers.get_rect.game.entities.item.WeaponRepository;
import se.chalmers.get_rect.game.entities.item.view.IWeaponView;
import se.chalmers.get_rect.game.window.model.Inventory;
import se.chalmers.get_rect.utilities.Point;

import java.util.HashMap;
import java.util.Map;

public class InventoryView implements IWindowView {

    private static final int WIDTH = 554;
    private static final int HEIGHT = 668;
    private static final Point ITEM_FIRST_OFFSET = new Point(60, HEIGHT-234);
    private static final Point ITEM_OFFSET = new Point(122, -122);
    private static final Point MARK_FIRST_OFFSET = new Point(45, HEIGHT-255);
    private static final Point MARK_OFFSET = new Point(120, -122);
    private ICamera camera;
    private Inventory inventory;
    private Map<Point, IWeaponView> weaponViewMap;
    private Point windowPosition;
    private boolean isSetup = false;
    private static final String IMG_PATH = "img/window/";
    private WeaponFactory weaponFactory;

    public InventoryView(Inventory model, ICamera camera, WeaponFactory weaponFactory) {
        this.camera = camera;
        this.inventory = model;
        this.weaponFactory = weaponFactory;
    }

    private void setup() {
        this.weaponViewMap = new HashMap<>();
        inventory.getItemsMap().forEach((K, V) -> weaponViewMap.put(K, weaponFactory.makeView(V)));
        windowPosition = new Point((int)camera.getAdapter().getWidth()/4 - WIDTH/2, (int)camera.getAdapter().getHeight()/2 - HEIGHT/2);
        isSetup = true;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        if (!isSetup)
            setup();

        graphics.draw(IMG_PATH+"chezzt.png", camera.getPosition().add(windowPosition));
        weaponViewMap.forEach((K, V) -> {
            V.drawIcon(graphics, getRealPosition(K, ITEM_FIRST_OFFSET, ITEM_OFFSET));
        });

        graphics.draw("img/pauseMenu/inventory/" + (inventory.isButtonDisabled(inventory.getCurrentlyMarked()) ? "overlay_disabled.png" : "item_active.png"), getRealPosition(inventory.getCurrentlyMarked(), MARK_FIRST_OFFSET, MARK_OFFSET));
    }

    private Point getRealPosition(Point gridPosition, Point firstOffset, Point offset) {
        return camera.getPosition().add(windowPosition).add(firstOffset).add(gridPosition.multiply(offset));
    }
}
