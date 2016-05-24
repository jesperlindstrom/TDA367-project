package se.chalmers.get_rect.game.entities.item;

public class WeaponSaveDataStore {
    private String type;
    private boolean found;

    public WeaponSaveDataStore(String type, boolean found) {
        this.type = type;
        this.found = found;
    }
    public String getType() {
        return type;
    }

    public boolean isFound() {
        return found;
    }
}
