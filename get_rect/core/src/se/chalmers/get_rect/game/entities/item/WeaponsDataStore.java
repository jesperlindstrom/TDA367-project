package se.chalmers.get_rect.game.entities.item;

public class WeaponsDataStore {

    private String type;
    private int reach;
    private int height;
    private int damage;
    private int cooldown;     //Amount of frames during which the weapon is used.
    private int speed;
    private float swingDegrees;  //Default is 180
    private int startTilt;  //Default is 35

    public String getType() {
        return type;
    }

    public int getDamage() {
        return damage;
    }

    public int getCooldown() {
        return cooldown;
    }

    public int getReach() {
        return reach;
    }

    public int getSpeed() {
        return speed;
    }

    public float getSwingDegrees() {
        return swingDegrees;
    }

    public int getStartTilt() {
        return startTilt;
    }
}
