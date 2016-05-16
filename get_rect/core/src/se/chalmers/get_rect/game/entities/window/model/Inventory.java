package se.chalmers.get_rect.game.entities.window.model;

import se.chalmers.get_rect.game.entities.item.WeaponRepository;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.utilities.Point;

import java.io.FileNotFoundException;
import java.util.List;

public class Inventory extends AbstractGridModel {

    private Player player;

    public Inventory(Player player, WeaponRepository weaponRepository) {
        this.player = player;
        try {
            fillGrid(weaponRepository.get("items", player));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void reset() {

    }

    public void fillGrid(List<IWeapon> weaponsList) {
        Point placement = new Point(0, 0);
        int collons = 4;

        for (IWeapon weapon : weaponsList) {
            if (placement.getX() > collons) {
                placement = placement.addY(1).setX(0);
            }

            addToMap(placement, () -> {
                player.addNewWeapon(weapon);
            });

            placement = placement.addX(1);
        }
    }


}
