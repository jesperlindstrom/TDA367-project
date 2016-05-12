package se.chalmers.get_rect.animation;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.utilities.Point;

import java.util.HashMap;
import java.util.Map;

public class AnimationCoordinator {
    private Map<Integer, FrameSequence> sequences;
    private Integer activeSequence;

    /**
     * Create a new sequence animation coordinator
     * @param defaultSequence The starting sequence name
     */
    public AnimationCoordinator(int defaultSequence) {
        activeSequence = defaultSequence;
        sequences = new HashMap<>();
    }

    /**
     * Add a new frame sequence
     * @param id The sequence id
     * @param frame The frame sequence to add
     */
    public void addSequence(Integer id, FrameSequence frame) {
        sequences.put(id, frame);
    }

    /**
     * Get an existing sequence
     * @param id The sequence id
     * @return The frame sequence object
     */
    public FrameSequence getSequence(Integer id) {
        return sequences.get(id);
    }

    /**
     * Play a frame sequence
     * @param id Sequence id
     */
    public void playSequence(Integer id) {
        FrameSequence newSequence = sequences.get(id);

        if (newSequence == null) {
            throw new SequenceNotFoundException(id);
        }

        activeSequence = id;
    }

    /**
     * Draw the active sequence frame
     * @param graphics Graphics adapter
     * @param position The position to draw at
     */
    public void draw(IGraphicsAdapter graphics, Point position, boolean flip) {
        if (activeSequence != null) {
            sequences.get(activeSequence).draw(graphics, position, flip);
        }
    }
}
