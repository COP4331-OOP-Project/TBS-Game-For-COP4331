package game.entities.structures;

import game.Player;
import game.commands.PowerDownCommand;
import game.entities.PowerState;
import game.gameboard.GameBoard;
import game.gameboard.Location;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Alex on 2/4/17.
 */
public class StructureEntityTest {

    private Location location = new Location(0, 1); // Dummy location
    Base base = new Base(location, 0);


    @Test
    public void testGetLocation() {

        assertEquals(0, base.getLocation().xIndex);
        assertEquals(1, base.getLocation().yIndex);

    }

    @Test
    public void testGetOwnerId() {
        assertEquals(0, base.getOwnerID());
    }

    @Test
    public void testDoTurn() {

        base.powerUp();
        assert(base.getPowerState() == PowerState.POWERED_UP);
        PowerDownCommand<Structure> pdc = new PowerDownCommand<Structure>(new GameBoard(new ArrayList<Player>()), base);
        base.addCommandToQueue(pdc);
        base.doTurn();
        assert(base.getPowerState() == PowerState.POWERED_DOWN);

    }

    @Test
    public void testIteratingNoCommands() {

        PowerDownCommand<Structure> pdc = new PowerDownCommand<Structure>(new GameBoard(new ArrayList<Player>()), base);
        base.addCommandToQueue(pdc);
        base.doTurn();
        base.doTurn();
        return;

    }

    @Test
    public void testClearingUnitCommandQueue() {

        PowerDownCommand<Structure> pdc = new PowerDownCommand<Structure>(new GameBoard(new ArrayList<Player>()), base);
        base.addCommandToQueue(pdc);
        assert(!base.isQueueEmpty());
        base.cancelQueuedCommands();
        assert(base.isQueueEmpty());

    }

    @Test
    public void testGetStats() {
        assertEquals(10, base.getAttackDamage());
        assertEquals(5, base.getDefenseDamage());
        assertEquals(5, base.getArmor());
        assertEquals(10, base.getBaseResourceCost());
        assertEquals(20, base.getHealth());
    }


    @Test
    public void testStateChanging() {

        assert(base.getPowerState() == PowerState.POWERED_UP);
        assertEquals(10f, base.getResourceCost(), 0);
        base.powerDown();
        assert(base.getPowerState() == PowerState.POWERED_DOWN);
        assertEquals(2.5f, base.getResourceCost(), 0);
        base.powerUp();
        assert(base.getPowerState() == PowerState.POWERED_UP);
        assertEquals(10f, base.getResourceCost(), 0);
        base.combatState();
        assert(base.getPowerState() == PowerState.COMBAT);
        assertEquals(12.5f, base.getResourceCost(), 0);
        base.standby();
        assert(base.getPowerState() == PowerState.STANDBY);
        assertEquals(7.5f, base.getResourceCost(), 0);

    }
}
