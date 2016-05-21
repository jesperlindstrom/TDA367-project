package se.chalmers.get_rect.game.entities.npc.model;

import se.chalmers.get_rect.game.quests.data.QuestState;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.ICombatModel;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.utilities.Point;

import java.util.List;
import java.util.Random;

public class Horv extends AbstractNPCModel {
    private final int width;
    private final int height;
    private List<String> dialogList;
    private int dialogNr = 0;

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
    public void update(double delta) {
        super.update(delta);
    }

    @Override
    public QuestState getQuestState() {
        return QuestState.AVAILABLE;
    }

    @Override
    public void onInteract(IModel model) {

        if (!isDialogVisible()) {
            Random random = new Random();
            showDialog(dialogList.get(dialogNr));
            dialogNr =+ random.nextInt(2)+1;
        } else {
            nextDialog();
        }

        if (model instanceof ICombatModel){
            ((ICombatModel) model).addHealth(((ICombatModel) model).getMaxHealth());
        }
    }

}
