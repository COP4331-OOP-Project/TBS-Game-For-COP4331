package game.entities;

import org.junit.Test;



import static org.junit.Assert.assertEquals;

/**
 * unit test for enum PowerState in entities package
 */
public class PowerStateTest {

    @Test
    public void testGetStateValues() {
        assertEquals(1.25f, PowerState.COMBAT.getUpkeep(), 0);
        assertEquals(0.25f, PowerState.POWERED_DOWN.getUpkeep(), 0);
        assertEquals(1.00f, PowerState.POWERED_UP.getUpkeep(), 0);
        assertEquals(0.75f, PowerState.STANDBY.getUpkeep(), 0);
    }

}
