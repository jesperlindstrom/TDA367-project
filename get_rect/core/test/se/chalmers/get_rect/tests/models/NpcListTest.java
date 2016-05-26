package se.chalmers.get_rect.tests.models;

import org.junit.Before;
import org.junit.Test;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.entities.npc.model.INpcModel;
import se.chalmers.get_rect.game.entities.overlays.model.NpcList;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class NpcListTest {

    private NpcList npcList;

    @Before
    public void setup(){
        List<IModel> list = new ArrayList<>();
        IModel remove1 = mock(IModel.class);
        IModel remove2 = mock(IModel.class);
        INpcModel iWillSurvie = mock(INpcModel.class);

        list.add(remove1);
        list.add(remove2);
        list.add(iWillSurvie);

        npcList = new NpcList(list);
    }

    @Test
    public void testGet(){
        List<IModel> list = npcList.get();
        assertEquals("List length should be 1", 1, list.size());
        assertTrue("List object shoudl be instance of INpcModel",list.get(0) instanceof INpcModel);
    }
}
