package se.chalmers.get_rect.game.entities.overlays.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractView;
import se.chalmers.get_rect.game.entities.ICamera;
import se.chalmers.get_rect.game.entities.item.WeaponFactory;
import se.chalmers.get_rect.game.entities.item.model.IMelee;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;
import se.chalmers.get_rect.game.entities.item.view.IWeaponView;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.utilities.Point;

public class PlayerWeaponSlotsView extends AbstractView {

    private Player player;
    private ICamera camera;
    private WeaponFactory weaponFactory;
    private IWeapon lastWeapon;
    private IWeaponView meleeView;
    private IWeaponView rangedView;
    private static final String pathItemSlot= "img/pauseMenu/inventory/item_slot.png";
    private static final String pathItemActive = "img/pauseMenu/inventory/item_active.png";
    private static final int DRAW_PRIORITY = 60;


    public PlayerWeaponSlotsView(Player player, ICamera camera, WeaponFactory weaponFactory){
        this.player = player;
        this.camera = camera;
        this.weaponFactory = weaponFactory;
        meleeView = weaponFactory.makeView(player.getMeleeWeapon());
        rangedView = weaponFactory.makeView(player.getRangedWeapon());
    }

    @Override
    public int getDrawPriority() {
        return DRAW_PRIORITY;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        if (!player.getActiveWeapon().equals(lastWeapon)) {
            lastWeapon = player.getActiveWeapon();
            if (lastWeapon instanceof IMelee) {
                meleeView = weaponFactory.makeView(lastWeapon);
            } else {
                rangedView = weaponFactory.makeView(lastWeapon);
            }
        }

        int x = camera.getPosition().getX() + (int)camera.getAdapter().getWidth()/2 - 226;
        int y = camera.getPosition().getY();
        Point position = new Point(x,y);
        Point primaryPos= position.add(40,20);
        Point secondaryPos  = position.add(320,20);

        graphics.draw(pathItemSlot,primaryPos);
        graphics.draw(pathItemSlot,secondaryPos);

        meleeView.drawIcon(graphics, primaryPos.add(20, 20));
        rangedView.drawIcon(graphics, secondaryPos.add(20, 20));

        if (player.getActiveWeapon() instanceof IMelee){
            graphics.draw(pathItemActive,primaryPos);
        } else {
            graphics.draw(pathItemActive,secondaryPos);

        }
    }
}
