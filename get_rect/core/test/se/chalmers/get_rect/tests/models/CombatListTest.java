package se.chalmers.get_rect.tests.models;

import org.junit.Before;
import org.junit.Test;
import se.chalmers.get_rect.game.entities.AbstractCombatModel;
import se.chalmers.get_rect.game.entities.ICombatModel;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.entities.npc.model.INpcModel;
import se.chalmers.get_rect.game.entities.overlays.model.CombatList;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CombatListTest {

    private CombatList combatList;

    @Before
    public void setup(){
        List<IModel> list = new ArrayList<>();
        IModel remove1 = mock(IModel.class);
        IModel remove2 = mock(IModel.class);
        ICombatModel iWillSurvie = mock(AbstractCombatModel.class);

        list.add(remove1);
        list.add(remove2);
        list.add(iWillSurvie);

        combatList = new CombatList(list);
    }

    @Test
    public void testGet(){
        List<IModel> list = combatList.get();
        assertEquals("List length should be 1", 1, list.size());
        assertTrue("List object shoudl be instance of INpcModel",list.get(0) instanceof AbstractCombatModel);
    }
}
