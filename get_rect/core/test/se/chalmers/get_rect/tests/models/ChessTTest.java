package se.chalmers.get_rect.tests.models;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import se.chalmers.get_rect.game.entities.npc.model.ChessT;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.game.entities.worldObjects.model.BoundingBox;
import se.chalmers.get_rect.game.window.controller.IWindowController;
import se.chalmers.get_rect.physics.CollisionData;
import se.chalmers.get_rect.physics.IRectangleAdapter;
import se.chalmers.get_rect.states.IState;
import se.chalmers.get_rect.states.StateManager;
import se.chalmers.get_rect.tests.physics.RectangleAdapterStub;
import se.chalmers.get_rect.tests.physics.RectangleFactoryAdapterStub;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.utilities.Side;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ChessTTest {

    private Player player;
    private ChessT chessT;
    private RectangleAdapterStub recPlay;
    private RectangleFactoryAdapterStub recFac;



    @Before
    public void setup(){
        this.player = mock(Player.class);
        StateManager<IWindowController> stateManager = mock(StateManager.class);
        recFac = new RectangleFactoryAdapterStub();
        recPlay = Mockito.mock(RectangleAdapterStub.class);
        this.chessT = new ChessT(new Point(), recFac, stateManager,player);
    }

    @Test
    public void testOnInteract(){
        int oldState = chessT.getState();
        chessT.onInteract(player);
        assertNotEquals("Should have new state", oldState, chessT.getState());
        assertEquals("Should be 3", 3, chessT.getState());
    }

    @Test
    public void testUpdate(){
        when(player.getBoundingBox()).thenReturn(recPlay);
        when(recPlay.intersects(chessT.getBoundingBox())).thenReturn(null);
        chessT.update(16);
        assertEquals("Current state should be 1", 1, chessT.getState());
        CollisionData data = new CollisionData();
        data.set(Side.LEFT);
        when(recPlay.intersects(any(IRectangleAdapter.class))).thenReturn(data);
        chessT.update(16);
        assertEquals("Current state should be 2", 2, chessT.getState());

    }
}
