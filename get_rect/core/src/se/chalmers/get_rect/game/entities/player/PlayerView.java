package se.chalmers.get_rect.game.entities.player;

import se.chalmers.get_rect.adapters.*;
import se.chalmers.get_rect.game.entities.AbstractAnimatedView;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.entities.item.WeapomFactory;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;

class PlayerView extends AbstractAnimatedView {
    private static final int STAND_STILL = 0;
    private static final int WALKING = 1;
    private static final int JUMPING = 2;
    private static final int RIDING = 3;
    private static final int DRAW_PRIORITY = 5;
    private IView weaponView;
    private IWeapon activeWeapon;
    private IAudioManagerAdapter audioManager;
    private WeapomFactory weapomFactory;

    private Player player;

    public PlayerView(Player player, IAudioManagerAdapter audioManager, WeapomFactory weapomFactory) {
        super(player, STAND_STILL);
        this.player = player;
        this.audioManager= audioManager;
        this.weapomFactory = weapomFactory;

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
        if (player.getActiveWeapon() != null && !player.getActiveWeapon().equals(activeWeapon)) {
            activeWeapon = player.getActiveWeapon();
            weaponView = weapomFactory.makeView(activeWeapon);
        }

        setFlip(player.getVelocity().getX() == 0 ? isFlip() : player.getVelocity().getX() < 0);
        playSequence(getSequence());

        if (getSequence() == WALKING) {
            audioManager.playMusic("walkingSound");
        } else {
            audioManager.stopMusic("walkingSound");
        }
        if (getSequence() == RIDING) {
            audioManager.playMusic("ridingSound");
        } else {
            audioManager.stopMusic("ridingSound");
        }


        // Tell abstract parent to drawIcon the animation
        super.draw(graphics);
        if (weaponView != null) weaponView.draw(graphics);
    }
}