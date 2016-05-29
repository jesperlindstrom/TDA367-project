package se.chalmers.get_rect.game.entities.npc.view;

import se.chalmers.get_rect.adapters.IAudioManagerAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractAnimatedView;
import se.chalmers.get_rect.game.entities.npc.model.Fishur;

public class FishurView extends AbstractAnimatedView{
    private static final int DEFAULT = 1;
    private IAudioManagerAdapter audioManager;
    private Fishur model;
    private boolean questTaken = false;

    public FishurView(Fishur model, IAudioManagerAdapter audioManager) {
        super(model, DEFAULT);
        addAnimationFrame(DEFAULT, "img/entities/fishur/fishur.png");
        this.model = model;
        this.audioManager = audioManager;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        super.draw(graphics);
        if (model.isInteractedWith() && !questTaken) {
            audioManager.playMusic("fishurUppdrag");
            questTaken = true;
        }
    }
}
