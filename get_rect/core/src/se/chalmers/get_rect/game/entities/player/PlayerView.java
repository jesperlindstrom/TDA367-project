package se.chalmers.get_rect.game.entities.player;

import se.chalmers.get_rect.adapters.IAssetManagerAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractAnimatedView;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.entities.item.ItemFactory;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;
import se.chalmers.get_rect.game.entities.item.view.OpAxeView;
import se.chalmers.get_rect.game.entities.item.view.OpSwordView;
import se.chalmers.get_rect.game.entities.item.view.PistolView;

class PlayerView extends AbstractAnimatedView {
    private static final int STAND_STILL = 0;
    private static final int WALKING = 1;
    private static final int JUMPING = 2;
    private static final int DRAW_PRIORITY = 5;
    private IView weaponView;
    private IWeapon activeWeapon;
    private IAssetManagerAdapter assetManager;
    private ItemFactory itemFactory;

    private Player player;

    public PlayerView(Player player, IAssetManagerAdapter assetManager, ItemFactory itemFactory) {
        super(player, STAND_STILL);
        this.player = player;
        this.assetManager = assetManager;
        this.itemFactory = itemFactory;

        addAnimationFrame(STAND_STILL, "img/entities/player/player_still.png");
        addAnimationFrame(JUMPING, "img/entities/player/player_still.png");
        addAnimationFrame(WALKING, "img/entities/player/player_still.png", 5);
        addAnimationFrame(WALKING, "img/entities/player/player_walking_1.png", 5);
        addAnimationFrame(WALKING, "img/entities/player/player_walking_3.png", 5);
        addAnimationFrame(WALKING, "img/entities/player/player_walking_2.png", 5);
    }

    private int getSequence() {
        if (!player.canJump()) {
            return JUMPING;
        }

        if (player.isWalking()) {
            return WALKING;
        }

        return STAND_STILL;
    }

    @Override
    public int getDrawPriority() {
        return DRAW_PRIORITY;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        if (player.getActiveWeapon() != null && !player.getActiveWeapon().equals(activeWeapon)) {
            activeWeapon = player.getActiveWeapon();
            weaponView = itemFactory.makeView(activeWeapon);
        }

        setFlip(player.getVelocity().getX() < 0);
        playSequence(getSequence());


        // Tell abstract parent to drawIcon the animation
        super.draw(graphics);
        if (weaponView != null) weaponView.draw(graphics);
    }


}