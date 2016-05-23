package se.chalmers.get_rect.game.entities;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.animation.AnimationCoordinator;
import se.chalmers.get_rect.animation.Frame;
import se.chalmers.get_rect.animation.FrameSequence;
import se.chalmers.get_rect.utilities.Point;

public abstract class AbstractAnimatedView extends AbstractView {
    private AnimationCoordinator animation;
    private IModel model;
    private Point offsetPoint;
    private boolean flip = false;

    protected AbstractAnimatedView(IModel model, int defaultSequence) {
        super(model);
        animation = new AnimationCoordinator(defaultSequence);
        this.model = model;
        offsetPoint = new Point(0 ,0);
    }

    protected void addAnimationFrame(int id, String name) {
        addAnimationFrame(id, name, 0);
    }

    protected void addAnimationFrame(int id, String image, int duration) {
        Frame newFrame = new Frame(image, duration);
        getOrCreateSequence(id).addFrame(newFrame);
    }


    public void setDrawOffset(Point offsetPoint) {
        this.offsetPoint = offsetPoint;
    }

    protected void playSequence(int id) {
        animation.playSequence(id);
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        animation.draw(graphics, model.getPosition().add(offsetPoint), flip);
    }

    private FrameSequence getOrCreateSequence(int sequence) {
        FrameSequence frameSequence = animation.getSequence(sequence);

        if (frameSequence == null) {
            frameSequence = new FrameSequence();
            animation.addSequence(sequence, frameSequence);
        }

        return frameSequence;
    }

    public void setFlip(boolean flip) {
        this.flip = flip;
    }

    public void setFlip(boolean flip, Point offset) {
        setDrawOffset(offset);
        setFlip(flip);
    }

    protected IModel getModel() {
        return model;
    }

    protected boolean isFlip() {
        return flip;
    }
}
