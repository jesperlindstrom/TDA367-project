package se.chalmers.get_rect.states;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class StateManagerTest {
    private StateManager<StateStub> stateManager;

    @Before
    public void setup() {
        stateManager = new StateManager<>();
    }

    @Test
    public void testSetState() {
        StateStub state1 = mock(StateStub.class);
        StateStub state2 = mock(StateStub.class);
        StateStub state3 = mock(StateStub.class);

        stateManager.add("state1", state1);
        stateManager.add("state2", state2);
        stateManager.add("state3", state3);

        stateManager.set("state1");

        verify(state1, times(1)).enteringState(null);
        verify(state1, never()).leavingState(null);

        assertEquals(stateManager.getState(), state1);
    }

    @Test
    public void testChangeCurrentState() {
        StateStub state1 = mock(StateStub.class);
        StateStub state2 = mock(StateStub.class);

        stateManager.add("state1", state1);
        stateManager.add("state2", state2);

        verify(state1, never()).enteringState(null);
        verify(state1, never()).leavingState(null);
        verify(state2, never()).enteringState(null);
        verify(state2, never()).leavingState(null);

        stateManager.set("state1");

        verify(state1, times(1)).enteringState(null);
        verify(state1, never()).leavingState(null);
        verify(state2, never()).enteringState(null);
        verify(state2, never()).leavingState(null);

        stateManager.set("state2");

        verify(state1, times(1)).enteringState(null);
        verify(state1, times(1)).leavingState("state2");
        verify(state2, times(1)).enteringState("state1");
        verify(state2, never()).leavingState(null);
    }

    @Test(expected = StateNotFoundException.class)
    public void testChangeToInvalidState() {
        stateManager.set(null);
    }
}