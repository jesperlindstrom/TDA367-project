package se.chalmers.get_rect.game.entities;


import java.io.FileNotFoundException;
import java.util.List;

public class DialogRepository extends AbstractRepository<DialogDataStore, String> {

    public DialogRepository() {
        super("dialog", DialogDataStore.class);
    }

    public DialogRepository(String npcName) {
        super(npcName, DialogDataStore.class);
    }

    @Override
    public List<String> get(String folderName) throws FileNotFoundException {
        return super.get("dialogs/" + folderName);
    }

    @Override
    protected String makeFromDataStore(DialogDataStore data) {
        return data.getDialog();
    }
}

