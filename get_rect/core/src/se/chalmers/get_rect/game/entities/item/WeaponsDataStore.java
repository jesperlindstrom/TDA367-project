package se.chalmers.get_rect.game.entities.item;

public class WeaponsDataStore {

    private String type;
    private int width;
    private int height;
    private int damage;
    private int frames;     //Amount of frames during which the weapon is used.
    private int speed;

    public String getType() {
        return type;
    }

    public int getDamage() {
        return damage;
    }

    public int getFrames() {
        return frames;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getSpeed() {
        return speed;
    }
}
