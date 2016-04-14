package se.chalmers.get_rect.game.entities.npc;

import se.chalmers.get_rect.utilities.Point;

public class NpcDataStore {
    private int x;
    private int y;

    public Point getPosition() {
        return new Point(x ,y);
    }

}
