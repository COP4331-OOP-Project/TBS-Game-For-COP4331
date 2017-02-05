package game.entities.units;

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
public class UnitEntityTest {

    private Location location = new Location(0, 1); // Dummy location
    Melee melee = new Melee(location, 0);


    @Test
    public void testGetLocation() {

        assertEquals(0, melee.getLocation().getX());
        assertEquals(1, melee.getLocation().getY());

    }

    @Test
    public void testGetOwnerId() {
        assertEquals(0, melee.getOwnerID());
    }

    @Test
    public void testDoTurn() {

        melee.powerUp();
        PowerDownCommand<Unit> pdc = new PowerDownCommand<Unit>(new GameBoard(new ArrayList<Player>()), melee);
        melee.addCommandToQueue(pdc);
        melee.doTurn();
        assert(melee.getPowerState() == PowerState.POWERED_DOWN);

    }

    @Test
    public void testIteratingNoCommands() {

        PowerDownCommand<Unit> pdc = new PowerDownCommand<Unit>(new GameBoard(new ArrayList<Player>()), melee);
        melee.addCommandToQueue(pdc);
        melee.doTurn();
        melee.doTurn();
        return;

    }

    @Test
    public void testClearingUnitCommandQueue() {

        PowerDownCommand<Unit> pdc = new PowerDownCommand<Unit>(new GameBoard(new ArrayList<Player>()), melee);
        melee.addCommandToQueue(pdc);
        assert(!melee.isQueueEmpty());
        melee.cancelQueuedCommands();
        assert(melee.isQueueEmpty());

    }

    @Test
    public void testGetStats() {
        assertEquals(7, melee.getAttackDamage());
        assertEquals(7, melee.getDefenseDamage());
        assertEquals(7, melee.getArmor());
        assertEquals(10, melee.getBaseResourceCost());
        assertEquals(10, melee.getHealth());
        assertEquals(3, melee.getSpeed());
    }

    @Test
    public void setUnitOrientation() {
        melee.setOrientation(2);
        assertEquals(2, melee.getOrientation());
    }

    @Test
    public void testStateChanging() {

        assert(melee.getPowerState() == PowerState.POWERED_UP);
        assertEquals(10f, melee.getResourceCost(), 0);
        melee.powerDown();
        assert(melee.getPowerState() == PowerState.POWERED_DOWN);
        assertEquals(2.5f, melee.getResourceCost(), 0);
        melee.powerUp();
        assert(melee.getPowerState() == PowerState.POWERED_UP);
        assertEquals(10f, melee.getResourceCost(), 0);
        melee.combatState();
        assert(melee.getPowerState() == PowerState.COMBAT);
        assertEquals(12.5f, melee.getResourceCost(), 0);
        melee.standby();
        assert(melee.getPowerState() == PowerState.STANDBY);
        assertEquals(7.5f, melee.getResourceCost(), 0);

    }

}
