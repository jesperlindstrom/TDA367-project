package se.chalmers.get_rect.game.entities.npc.view;

import se.chalmers.get_rect.adapters.IAudioManagerAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractAnimatedView;
import se.chalmers.get_rect.game.entities.npc.model.SawmillExpress;
import se.chalmers.get_rect.utilities.Point;

public class SawmillView extends AbstractAnimatedView {
    private static final int DRAW_PRIORITY = 0;
    private static final int DEFAULT = 1;
    private static final int FLYING = 2;
    private SawmillExpress model;
    private IAudioManagerAdapter audioManager;
    private boolean questTaken = false;

    public SawmillView(SawmillExpress model, IAudioManagerAdapter audioManager) {
        super(model, DEFAULT);
        this.model = model;
        super.setDrawOffset(new Point(0, -100));
        addAnimationFrame(DEFAULT, "img/entities/sawmill/sawmill-express.png");
        addAnimationFrame(FLYING, "img/entities/sawmill/sawmill-express-waaah.png");
        this.audioManager = audioManager;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        if (model.isInteractedWith() && !questTaken) {
            audioManager.playMusic("SawmillUppdrag");
            questTaken = true;
        }
        if (model.isFlying()) {
            addAnimationFrame(0, "img/interact/exclamation.png");
            playSequence(FLYING);
        } else {
            playSequence(DEFAULT);
        }
        super.draw(graphics);
    }

    @Override
    public int getDrawPriority() {
        return DRAW_PRIORITY;
    }
}
