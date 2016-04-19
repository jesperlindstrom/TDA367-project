package se.chalmers.get_rect.game.entities;

public interface IInteractableModel extends IPhysicsModel {
    boolean showInteractionHint();
    void onInteract(IModel model);
    void showDialog(String text);
    String getDialog();
    boolean isDialogVisible();
}
