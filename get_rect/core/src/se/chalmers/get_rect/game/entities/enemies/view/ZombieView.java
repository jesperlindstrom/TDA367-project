package se.chalmers.get_rect.game.entities.enemies.view;


import se.chalmers.get_rect.adapters.IAudioManagerAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.event.Event;
import se.chalmers.get_rect.event.IEventListener;
import se.chalmers.get_rect.game.entities.AbstractAnimatedView;
import se.chalmers.get_rect.game.entities.enemies.model.Zombie;

public class ZombieView extends AbstractAnimatedView implements IEventListener {
    private static final int DRAW_PRIORITY = 4;
    private static final int WALKING = 1;
    private IAudioManagerAdapter audioManager;

    public ZombieView(Zombie model, IAudioManagerAdapter audioManager){
        super(model, WALKING);
        this.audioManager = audioManager;

        addAnimationFrame(WALKING, "img/entities/zombies/zombie.png", 5);
        addAnimationFrame(WALKING, "img/entities/zombies/zombieOpen.png", 5);

        model.addListener(this);
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        super.draw(graphics);
    }

    @Override
    public void handleEvent(Event e) {
        if (e.getType().equals("zombie") && e.getAction().equals("died")) {
            audioManager.playSound("scream");
        }
    }

    @Override
    public int getDrawPriority() {
        return DRAW_PRIORITY;
    }
}
