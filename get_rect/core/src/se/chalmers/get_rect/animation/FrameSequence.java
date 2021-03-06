package se.chalmers.get_rect.animation;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.utilities.Point;

import java.util.ArrayList;
import java.util.List;

public class FrameSequence {
    private List<Frame> frames;
    private int currentIndex = 0;
    private int ticks = 0;

    /**
     * Create a new empty frame sequence
     */
    public FrameSequence() {
        frames = new ArrayList<>();
    }

    /**
     * Create a new frame sequence with frames
     * @param frames The frames
     */
    public FrameSequence(List<Frame> frames) {
        this.frames = frames;
    }

    /**
     * Add a frame after initialization
     * @param frame The frame
     */
    public void addFrame(Frame frame) {
        frames.add(frame);
    }

    /**
     * Draw the current frame
     * @param graphics Graphics adapter
     * @param position The position to drawIcon at
     */
    public void draw(IGraphicsAdapter graphics, Point position, boolean flip) {
        getCurrentFrame().draw(graphics, position, flip);
        enqueueNextFrame();
    }

    private Frame getCurrentFrame() {
        return frames.get(currentIndex);
    }

    private void enqueueNextFrame() {
        if (ticks == getCurrentFrame().getDuration()) {
            currentIndex = getNextFrameIndex();
            ticks = 0;
        } else {
            ticks++;
        }
    }

    private int getNextFrameIndex() {
        int lastIndex = frames.size() - 1;

        // Increment or start from the beginning
        return currentIndex == lastIndex ? 0 : currentIndex + 1;
    }
}
