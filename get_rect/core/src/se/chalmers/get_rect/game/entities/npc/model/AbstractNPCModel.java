package se.chalmers.get_rect.game.entities.npc.model;

import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.quests.data.IQuest;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.AbstractInteractableModel;
import se.chalmers.get_rect.game.quests.data.QuestState;
import se.chalmers.get_rect.utilities.Point;

public abstract class AbstractNPCModel extends AbstractInteractableModel implements INpcModel {
    private IQuest quest;

    protected AbstractNPCModel(Point position, Point velocity, boolean solid, boolean affectedByGravity, IRectangleFactoryAdapter rectangleFactory) {
        super(position, velocity, solid, affectedByGravity, rectangleFactory);
    }

    protected AbstractNPCModel(Point position, boolean affectedByGravity, IRectangleFactoryAdapter rectangleFactory) {
        this(position, new Point(0, 0), false, affectedByGravity, rectangleFactory);
    }

    protected void setQuest(IQuest quest) {
        this.quest = quest;
    }

    protected IQuest getQuest() {
        return quest;
    }

    @Override
    public void onInteract(IModel model) {
        if (isDialogVisible()) {
            nextDialog();
        } else {
            nextPhrase();
        }

        quest.interact(() -> onQuestCompletion(model));
    }

    private void nextPhrase() {
        String text = getSpeechDialog();

        if (text != null)
            showDialog(text);
    }

    private String getSpeechDialog() {
        if (quest.getState().equals(QuestState.AVAILABLE))
            return quest.getAcceptText();

        if (quest.getState().equals(QuestState.COMPLETABLE))
            return quest.getCompletionText();

        return getSmallTalk();
    }

    protected void onQuestCompletion(IModel model) { }

    protected String getSmallTalk() {
        return null;
    }

    @Override
    public QuestState getQuestState() {
        if (quest != null)
            return quest.getState();

        return QuestState.UNAVAILABLE;
    }
}
