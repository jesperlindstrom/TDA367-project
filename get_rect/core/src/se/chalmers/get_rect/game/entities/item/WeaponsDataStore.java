package se.chalmers.get_rect.game.entities.item;

public class WeaponsDataStore {

    private String type;
    private int width;
    private int height;
    private int damage;
    private int cooldown;     //Amount of frames during which the weapon is used.
    private int speed;
    private float swingDegrees;  //Default is 180

    public String getType() {
        return type;
    }

    public int getDamage() {
        return damage;
    }

    public int getCooldown() {
        return cooldown;
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

    public float getSwingDegrees() {
        return swingDegrees;
    }
}
