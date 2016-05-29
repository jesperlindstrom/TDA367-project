package se.chalmers.get_rect.game.entities.enemies.view;

import se.chalmers.get_rect.adapters.IAudioManagerAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractAnimatedView;
import se.chalmers.get_rect.game.entities.enemies.model.Demon;
import se.chalmers.get_rect.utilities.Point;

public class DemonView extends AbstractAnimatedView {
    private static final int DRAW_PRIORITY = 4;
    private static final int FLYING = 1;
    private static final int ATTACK = 2;
    private Demon model;
    private Point offset = new Point(250, 0);
    private IAudioManagerAdapter audioManager;

    public DemonView(Demon model, IAudioManagerAdapter audioManager){
        super(model, FLYING);
        this.model = model;
        int changeFrame = model.getRandFlap()/3;
        this.audioManager = audioManager;

        addAnimationFrame(FLYING, "img/entities/demons/mjolnir1.png", changeFrame);
        addAnimationFrame(FLYING, "img/entities/demons/mjolnir2.png", changeFrame);
        addAnimationFrame(ATTACK, "img/entities/demons/mjolnir_attack.png", changeFrame);
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {

        if (model.getVelocity().getX() > -3 && model.getVelocity().getX() < 3) {
            setFlip(true, offset);
        } else {
            setFlip(false, new Point(0, 0));
        }
        playSequence(getSequence());
        super.draw(graphics);
        audioManager.playMusic("mjolnirLaugh");

    }
    private int getSequence() {
        if (!model.isAttacking()) {
            return FLYING;
        } else {
            return ATTACK;
        }
    }

    @Override
    public int getDrawPriority() {
        return DRAW_PRIORITY;
    }
}