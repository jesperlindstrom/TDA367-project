package se.chalmers.get_rect.animation;

public class SequenceNotFoundException extends RuntimeException {
    public SequenceNotFoundException(Integer sequenceId) {
        super("There is no animation sequence with ID " + sequenceId);
    }
}
