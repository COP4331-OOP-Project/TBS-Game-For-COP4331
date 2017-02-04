package game.commands;


import game.entities.units.Unit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class AttackCommandTest {

    private AttackCommand<Unit> atkCmd;

    @Before
    public void setUp() {
        atkCmd = new AttackCommand<Unit>(null, null, 5, 5);
    }

    @Test
    public void getDuration() {
        Assert.assertEquals(atkCmd.getDuration(), 5);
    }

    @Test
    public void iterateDuration() {
        atkCmd.iterateDuration();
        Assert.assertEquals(atkCmd.getDuration(), 4);
    }

}