package game.entities;

import game.commands.Command;
import game.commands.MoveCommand;
import game.entities.units.Unit;
import game.gameboard.GameBoard;
import game.gameboard.Location;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;


/**
 * Created by David on 2/4/2017.
 */
public class RallyPointTest {
    public GameBoard gBoard;
    public MoveCommand command;

    /*@Before
    public void setUp(){
        gBoard = new GameBoard(null);
    }

    @Test
    public void TestPathAlgorithm1(){
        Location from = new Location(13,10);
        RallyPoint rp = new RallyPoint(new Location(15,10), gBoard);
        ArrayList<Unit> empty = new ArrayList<Unit>();
        rp.setArmy(new Army(null,0,null,empty));
        command=rp.pathAlgorithm(from,rp.getLocation(), null);
        Assert.assertEquals(command.getDirection(), 45);
    }
    @Test
    public void TestPathAlgorithm2(){
        Location from = new Location(23,1);
        RallyPoint rp = new RallyPoint(new Location(23,10), gBoard);
        ArrayList<Unit> empty = new ArrayList<Unit>();
        rp.setArmy(new Army(null,0,null,empty));
        command=rp.pathAlgorithm(from,rp.getLocation(), null);
        Assert.assertEquals(command.getDirection(), 90);
    }
    @Test
    public void TestPathAlgorithm3(){
        Location from = new Location(8,10);
        RallyPoint rp = new RallyPoint(new Location(12,6), gBoard);
        ArrayList<Unit> empty = new ArrayList<Unit>();
        rp.setArmy(new Army(null,0,null,empty));
        command=rp.pathAlgorithm(from,rp.getLocation(), null);
        Assert.assertEquals(command.getDirection(), 135);
    }
    @Test
    public void TestPathAlgorithm4(){
        Location from = new Location(4,1);
        RallyPoint rp = new RallyPoint(new Location(10,1), gBoard);
        ArrayList<Unit> empty = new ArrayList<Unit>();
        rp.setArmy(new Army(null,0,null,empty));
        command=rp.pathAlgorithm(from,rp.getLocation(), null);
        Assert.assertEquals(command.getDirection(), 90);
    }

    @Test
    public void TestPathAlgorithmNoPath(){
        Location from = new Location(4,1);
        RallyPoint rp = new RallyPoint(new Location(0,0), gBoard);
        ArrayList<Unit> empty = new ArrayList<Unit>();
        rp.setArmy(new Army(null,0,null,empty));
        command=rp.pathAlgorithm(from,rp.getLocation(), null);
        Assert.assertEquals(command,null);
    }
    */

}
