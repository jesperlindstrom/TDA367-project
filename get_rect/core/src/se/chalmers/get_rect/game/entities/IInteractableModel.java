package se.chalmers.get_rect.game.entities;

import se.chalmers.get_rect.event.IEventSource;

public interface IInteractableModel extends IPhysicsModel, IEventSource {
    void onInteract(IModel model);
    void showDialog(String text);
    String getDialog();
    boolean isDialogVisible();
    void nextDialog();
}
