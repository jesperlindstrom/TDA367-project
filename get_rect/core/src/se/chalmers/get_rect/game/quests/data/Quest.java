package se.chalmers.get_rect.game.quests.data;

import se.chalmers.get_rect.event.Event;

import java.util.List;

public class Quest implements IQuest {
    private QuestState state;
    private List<Objective> objectives;

    public Quest(QuestState state, List<Objective> objectives) {
        this.state = state;
        this.objectives = objectives;
    }

    @Override
    public QuestState getState() {
        return state;
    }

    public void interact() {
        if (state.equals(QuestState.AVAILABLE))
            accept();
        else if (state.equals(QuestState.COMPLETABLE))
            complete();
    }

    private void accept() {
        state = QuestState.IN_PROGRESS;
    }

    private void complete() {
        state = QuestState.COMPLETED;
    }

    @Override
    public void handleEvent(Event e) {
        if (!state.equals(QuestState.IN_PROGRESS))
            return;

        boolean allCompleted = true;

        for (Objective objective : objectives) {
            if (!objective.isCompleted()) {
                objective.handleEvent(e);
                allCompleted = false;
            }
        }

        if (allCompleted) {
            state = QuestState.COMPLETABLE;
        }
    }
}
