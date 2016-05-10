package se.chalmers.get_rect.game.entities;

public interface IInteractableModel extends IPhysicsModel {
    void onInteract(IModel model);
    void showDialog(String text);
    String getDialog();
    boolean isDialogVisible();
    void nextDialog();
}
