package se.chalmers.get_rect.game.entities.player;



public class PlayerDataStore {

    private int health;
    private boolean hasFoundHunch;
    private String melee;
    private String ranged;

    public PlayerDataStore(int health, boolean hasFoundHunch, String melee, String ranged) {
        this.health = health;
        this.hasFoundHunch = hasFoundHunch;
        this.melee = melee;
        this.ranged = ranged;
    }

    public int getHealth() {
        return health;
    }

    public boolean isHasFoundHunch() {
        return hasFoundHunch;
    }

    public String getMelee() {
        return melee;
    }

    public String getRanged() {
        return ranged;
    }
}

