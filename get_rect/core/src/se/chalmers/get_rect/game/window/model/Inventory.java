package se.chalmers.get_rect.game.window.model;

import se.chalmers.get_rect.game.entities.item.WeaponRepository;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.utilities.Point;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory extends AbstractGridModel {
    private Player player;
    private Map<Point, IWeapon> itemsMap;
    private static final int COLUMNS = 4;

    public Inventory(Player player, WeaponRepository weaponRepository) {
        this.player = player;
        itemsMap = new HashMap<>();
        try {
            fillGrid(weaponRepository.get(player));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        activateButton(new Point());
    }

    @Override
    public void reset() {

    }

    @Override
    public boolean isAllowingRegularInput() {
        return true;
    }

    public void fillGrid(List<IWeapon> weaponsList) {
        Point placement = new Point(0, 0);


        for (IWeapon weapon : weaponsList) {
            if (placement.getX() == COLUMNS) {
                placement = placement.addY(1).setX(0);
            }

            addToMap(placement, () -> player.addNewWeapon(weapon));

            itemsMap.put(placement, weapon);

            if (!weapon.isFound()) {
                disableButton(placement);
            }

            placement = placement.addX(1);
        }
    }

    @Override
    public void setIndex(Point point) {
        if (itemsMap.get(point).isFound()) {
            activateButton(point);
        }
        super.setIndex(point);
    }

    public Map<Point, IWeapon> getItemsMap() {
        return itemsMap;
    }
}
