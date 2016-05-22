package se.chalmers.get_rect.game.entities.npc.model;

import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.utilities.ListUtils;
import se.chalmers.get_rect.utilities.Point;

import java.util.List;

public class Horv extends AbstractNPCModel {
    private final int width;
    private final int height;
    private List<String> dialogList;

    public Horv(Point point, IRectangleFactoryAdapter rectangleFactory, List<String> phrases, int width, int height) {
        super(point, new Point(0, 0), false, true, rectangleFactory);
        this.width = width;
        this.height = height;
        setBoundingBox(this.width, this.height);
        this.dialogList = phrases;
    }

    public Horv(Point point, IRectangleFactoryAdapter rectangleFactory, List<String> phrases) {
        this(point, rectangleFactory, phrases, 58, 158);
    }

    @Override
    protected String getSmallTalk() {
        return ListUtils.randomItem(dialogList);
    }
}
