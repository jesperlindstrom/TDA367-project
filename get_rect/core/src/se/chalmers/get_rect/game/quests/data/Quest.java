package se.chalmers.get_rect.game.quests.data;

import se.chalmers.get_rect.event.Event;

import java.util.List;

public class Quest implements IQuest {
    private int id;
    private QuestState state;
    private List<Objective> objectives;
    private String acceptText;
    private String completionText;

    public Quest(int id, QuestState state, List<Objective> objectives, String acceptText, String completionText) {
        this.id = id;
        this.state = state;
        this.objectives = objectives;
        this.acceptText = acceptText;
        this.completionText = completionText;
    }

    @Override
    public String getAcceptText() {
        return acceptText;
    }

    @Override
    public String getCompletionText() {
        return completionText;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public QuestState getState() {
        return state;
    }

    @Override
    public void interact(CompleteAction completion) {
        if (state.equals(QuestState.AVAILABLE)) {
            accept();
        } else if (state.equals(QuestState.COMPLETABLE)) {
            complete(completion);
        }
    }

    private void accept() {
        state = QuestState.IN_PROGRESS;
    }

    private void complete(CompleteAction completion) {
        state = QuestState.COMPLETED;

        if (completion != null)
            completion.complete();
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
