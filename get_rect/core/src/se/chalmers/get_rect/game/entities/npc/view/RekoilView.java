package se.chalmers.get_rect.game.entities.npc.view;

import se.chalmers.get_rect.adapters.IAudioManagerAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractAnimatedView;
import se.chalmers.get_rect.game.entities.npc.model.Rekoil;
import se.chalmers.get_rect.utilities.Point;

public class RekoilView extends AbstractAnimatedView {
    private static final int DEFAULT = 1;
    private static final int SHOW_ARCH = 2;
    private Rekoil model;
    private IAudioManagerAdapter audioManager;
    private boolean hasPlayed = false;
    private boolean questTaken = false;

    public RekoilView(Rekoil model, IAudioManagerAdapter audioManager) {
        super(model, DEFAULT);
        this.model = model;
        super.setDrawOffset(new Point(0, 0));
        addAnimationFrame(DEFAULT, "img/entities/rekoil/rekoil1.png");
        addAnimationFrame(SHOW_ARCH, "img/entities/rekoil/rekoil2.png");
        this.audioManager = audioManager;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        if (model.isInteractedWith() && !questTaken) {
            audioManager.playMusic("RKLUppdrag");
            questTaken = true;
        }
        if (model.showArch()) {
            if (!hasPlayed) {
                audioManager.playMusic("RKLUppdragKlart");
                hasPlayed = true;
            }
            addAnimationFrame(0, "img/interact/exclamation.png");
            playSequence(SHOW_ARCH);
        } else {
            playSequence(DEFAULT);
        }
        super.draw(graphics);
    }
}
