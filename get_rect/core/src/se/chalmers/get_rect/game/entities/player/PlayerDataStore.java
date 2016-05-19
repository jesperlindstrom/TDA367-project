package se.chalmers.get_rect.game.entities.player;



public class PlayerDataStore {

    private int health;
    private boolean hasFoundHunch;
    private String melee;
    private String ranged;

    public PlayerDataStore(int health, boolean hasFoundHunch){
        this.health = health;
        this.hasFoundHunch = hasFoundHunch;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isHasFoundHunch() {
        return hasFoundHunch;
    }

    public void setHasFoundHunch(boolean hasFoundHunch) {
        this.hasFoundHunch = hasFoundHunch;
    }

    public String getMelee() {
        return melee;
    }

    public void setMelee(String melee) {
        this.melee = melee;
    }

    public String getRanged() {
        return ranged;
    }

    public void setRanged(String ranged) {
        this.ranged = ranged;
    }
}

