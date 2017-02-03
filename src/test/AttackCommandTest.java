package test;

import game.Player;
import game.commands.AttackCommand;
import game.entities.units.Unit;
import game.gameboard.GameBoard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Alex on 2/2/17.
 */
class AttackCommandTest {

    AttackCommand<Unit> atkCmd;

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