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

    /**
     * Create a new animated view
     * @param model The model to use for positioning
     * @param defaultSequence The default sequence ID
     */
    protected AbstractAnimatedView(IModel model, int defaultSequence) {
        super(model);
        animation = new AnimationCoordinator(defaultSequence);
        this.model = model;
        offsetPoint = new Point(0 ,0);
    }

    /**
     * Add an animation frame to a sequence
     * @param id Sequence ID
     * @param name Image path
     */
    protected void addAnimationFrame(int id, String name) {
        addAnimationFrame(id, name, 0);
    }

    /**
     * Add an animation frame to a sequence
     * @param id Sequence ID
     * @param image Image path
     * @param duration Duration in ticks
     */
    protected void addAnimationFrame(int id, String image, int duration) {
        Frame newFrame = new Frame(image, duration);
        getOrCreateSequence(id).addFrame(newFrame);
    }


    public void setDrawOffset(Point offsetPoint) {
        this.offsetPoint = offsetPoint;
    }

    /**
     * Play a frame sequence by name
     * @param id Sequence ID
     */
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
}
