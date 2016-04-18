package se.chalmers.get_rect.game.entities.npc;

import se.chalmers.get_rect.utilities.Point;

public class NpcDataStore {
    private int x;
    private int y;
    private String type;

    public Point getPosition() {
        return new Point(x ,y);
    }

    public String getType() {
        return type;
    }
}
