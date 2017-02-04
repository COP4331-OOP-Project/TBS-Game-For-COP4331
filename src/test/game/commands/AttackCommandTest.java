package game.commands;


import game.entities.units.Unit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


class AttackCommandTest {

    private AttackCommand<Unit> atkCmd;

    @BeforeEach
    void setUp() {
        atkCmd = new AttackCommand<Unit>(null, null, 5, 5);
    }

    @Test
    void getDuration() {
        assertEquals(atkCmd.getDuration(), 5);
    }

    @Test
    void iterateDuration() {
        atkCmd.iterateDuration();
        assertEquals(atkCmd.getDuration(), 4);
    }

}