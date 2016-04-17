package se.chalmers.get_rect.game.entities.enemies.zombie;


import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractAnimatedView;

public class ZombieView extends AbstractAnimatedView{
    private static final int WALKING = 1;

    public ZombieView(Zombie model){
        super(model, WALKING);

        addAnimationFrame(WALKING, "img/entities/zombies/zombie.png", 5);
        addAnimationFrame(WALKING, "img/entities/zombies/zombieOpen.png", 5);
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        super.draw(graphics);
    }
}
