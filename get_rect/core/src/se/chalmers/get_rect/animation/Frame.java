package se.chalmers.get_rect.animation;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.utilities.Point;

public class Frame {
    private String image;
    private int duration;

    /**
     * Create a new animation frame
     * @param image
     * @param duration
     */
    public Frame(String image, int duration) {
        this.image = image;
        this.duration = duration;
    }

    /**
     * Draw this animation frame
     * @param graphics
     * @param position
     */
    public void draw(IGraphicsAdapter graphics, Point position) {
        graphics.draw(image, position);
    }

    /**
     * Get the duration of this frame
     * @return
     */
    public int getDuration() {
        return duration;
    }
}
