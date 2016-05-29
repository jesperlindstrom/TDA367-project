package se.chalmers.get_rect.game.entities.npc.view;

import se.chalmers.get_rect.adapters.IAudioManagerAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractAnimatedView;
import se.chalmers.get_rect.game.entities.npc.model.Horv;

public class HorvView extends AbstractAnimatedView{
    private static final int DEFAULT = 1;
    private IAudioManagerAdapter audioManager;
    private Horv model;
    private boolean questTaken = false;

    public HorvView(Horv model, IAudioManagerAdapter audioManager) {
        super(model, DEFAULT);
        addAnimationFrame(DEFAULT, "img/entities/horv/horv.png");
        this.model = model;
        this.audioManager = audioManager;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        super.draw(graphics);
        if (model.isInteractedWith() && !questTaken) {
            audioManager.playMusic("HorvProcessor");
            questTaken = true;
        }
    }
}
