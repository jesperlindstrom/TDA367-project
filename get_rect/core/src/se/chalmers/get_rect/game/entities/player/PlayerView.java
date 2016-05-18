package se.chalmers.get_rect.game.entities.player;

import se.chalmers.get_rect.adapters.IAssetManagerAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.IMusicAdapter;
import se.chalmers.get_rect.adapters.ISoundAdapter;
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
    private static final int RIDING = 3;
    private static final int DRAW_PRIORITY = 5;
    private ISoundAdapter walkingSound;
    private ISoundAdapter ridingSound;
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
        addAnimationFrame(RIDING, "img/entities/hunchen/player_hunchen1.png", 5);
        addAnimationFrame(RIDING, "img/entities/hunchen/player_hunchen2.png", 5);
    }

    private int getSequence() {
        if (player.isRiding())
            return RIDING;
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

    /**
     * initializes sounds since they can't be initalized while creating player
     * @param graphics
     */

    @Override
    public void draw(IGraphicsAdapter graphics) {
        if (walkingSound == null) {
            walkingSound = assetManager.getSound("sounds/walkingSound.mp3");
        }
        if (ridingSound == null) {
            ridingSound = assetManager.getSound("sounds/ridingSound.mp3");
        }
        if (player.getActiveWeapon() != null && !player.getActiveWeapon().equals(activeWeapon)) {
            activeWeapon = player.getActiveWeapon();
            weaponView = itemFactory.makeView(activeWeapon);
        }

        setFlip(player.getVelocity().getX() < 0);
        playSequence(getSequence());

        if (getSequence() == RIDING) {

        } else {
            ridingSound.play();
        }
        if (getSequence() == WALKING) {

        } else {
            walkingSound.play();
        }

        // Tell abstract parent to drawIcon the animation
        super.draw(graphics);
        if (weaponView != null) weaponView.draw(graphics);
    }
}