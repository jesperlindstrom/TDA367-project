package se.chalmers.get_rect.game.entities;


public class DialogRepository extends AbstractRepository<DialogDataStore, String> {

    public DialogRepository() {
        super("dialog", DialogDataStore.class);
    }

    @Override
    protected String makeFromDataStore(DialogDataStore data) {
        return data.getDialog();
    }
}

