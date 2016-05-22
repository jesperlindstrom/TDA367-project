package se.chalmers.get_rect.game.entities.npc;


import se.chalmers.get_rect.game.entities.AbstractRepository;

import java.io.FileNotFoundException;
import java.util.List;

public class DialogRepository extends AbstractRepository<DialogDataStore, String> {

    public DialogRepository(String name) {
        super(name, DialogDataStore.class);
    }

    public List<String> get() throws FileNotFoundException {
        return super.get("dialogs");
    }

    public List<String> get(String path) throws FileNotFoundException {
        return get();
    }

    @Override
    protected String makeFromDataStore(DialogDataStore data) {
        return data.getDialog();
    }
}

