package se.chalmers.get_rect.game.entities.player;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.animation.AnimationCoordinator;
import se.chalmers.get_rect.game.entities.AbstractAnimatedView;

class PlayerView extends AbstractAnimatedView {
    private static final int STAND_STILL = 0;
    private static final int WALKING = 1;
    private static final int JUMPING = 2;
    private static final int DRAW_PRIORITY = 5;

    private Player player;

    public PlayerView(Player player) {
        super(player, STAND_STILL);
        this.player = player;

        addAnimationFrame(STAND_STILL, "img/entities/player/playerTwoLeg.png");
        addAnimationFrame(JUMPING, "img/entities/player/playerTwoLeg.png");
        addAnimationFrame(WALKING, "img/entities/player/playerOneLeg.png", 5);
        addAnimationFrame(WALKING, "img/entities/player/playerTwoLeg.png", 5);
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
        playSequence(getSequence());
        System.out.println("player draw");
        // Tell abstract parent to draw the animation
        super.draw(graphics);
    }
}