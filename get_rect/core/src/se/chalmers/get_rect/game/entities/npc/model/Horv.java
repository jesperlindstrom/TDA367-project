package se.chalmers.get_rect.game.entities.npc.model;

import se.chalmers.get_rect.game.entities.IRepository;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.ICombatModel;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.quests.QuestState;
import se.chalmers.get_rect.utilities.Point;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Horv extends AbstractNPCModel {
    private final int speed;
    private final int width;
    private final int height;
    private boolean isJumping;
    private IRepository<String> dialogRepository;
    private List<String> dialogList;
    private int dialogNr = 0;

    public Horv(Point point, IRectangleFactoryAdapter rectangleFactory, IRepository dialogRepository, int speed, int width, int height) {
        super(point, new Point(0, 0), false, rectangleFactory);
        this.speed = speed;
        this.width = width;
        this.height = height;
        setBoundingBox(this.width, this.height);
        this.dialogRepository = dialogRepository;

        dialogList = new ArrayList<>();

        try {
            dialogList = dialogRepository.get("horv");
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    public Horv(Point point, IRectangleFactoryAdapter rectangleFactory, IRepository dialogRepository) {
        this(point, rectangleFactory, dialogRepository, 50, 150, 300);
    }
    @Override
    public void update(double delta) {
        super.update(delta);
    }

    @Override
    public QuestState getQuestState() {
        return isJumping ? QuestState.UNAVAILABLE : QuestState.AVAILABLE;
    }

    @Override
    public void onInteract(IModel model) {

        if (!isDialogVisible()) {
            showDialog(dialogList.get(dialogNr));
            dialogNr =+ 1;
        } else {
            nextDialog();
        }

        if (model instanceof ICombatModel){
            ((ICombatModel) model).addHealth(((ICombatModel) model).getMaxHealth());
        }
    }

}
