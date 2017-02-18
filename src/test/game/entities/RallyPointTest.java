package game.entities;

import game.commands.MoveCommand;
import game.gameboard.GameBoard;


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
        rallyPoint rp = new rallyPoint(new Location(15,10), gBoard);
        ArrayList<unit> empty = new ArrayList<unit>();
        rp.setArmy(new army(null,0,null,empty));
        command=rp.pathAlgorithm(from,rp.getLocation(), null);
        Assert.assertEquals(command.getDirection(), 45);
    }
    @Test
    public void TestPathAlgorithm2(){
        Location from = new Location(23,1);
        rallyPoint rp = new rallyPoint(new Location(23,10), gBoard);
        ArrayList<unit> empty = new ArrayList<unit>();
        rp.setArmy(new army(null,0,null,empty));
        command=rp.pathAlgorithm(from,rp.getLocation(), null);
        Assert.assertEquals(command.getDirection(), 90);
    }
    @Test
    public void TestPathAlgorithm3(){
        Location from = new Location(8,10);
        rallyPoint rp = new rallyPoint(new Location(12,6), gBoard);
        ArrayList<unit> empty = new ArrayList<unit>();
        rp.setArmy(new army(null,0,null,empty));
        command=rp.pathAlgorithm(from,rp.getLocation(), null);
        Assert.assertEquals(command.getDirection(), 135);
    }
    @Test
    public void TestPathAlgorithm4(){
        Location from = new Location(4,1);
        rallyPoint rp = new rallyPoint(new Location(10,1), gBoard);
        ArrayList<unit> empty = new ArrayList<unit>();
        rp.setArmy(new army(null,0,null,empty));
        command=rp.pathAlgorithm(from,rp.getLocation(), null);
        Assert.assertEquals(command.getDirection(), 90);
    }

    @Test
    public void TestPathAlgorithmNoPath(){
        Location from = new Location(4,1);
        rallyPoint rp = new rallyPoint(new Location(0,0), gBoard);
        ArrayList<unit> empty = new ArrayList<unit>();
        rp.setArmy(new army(null,0,null,empty));
        command=rp.pathAlgorithm(from,rp.getLocation(), null);
        Assert.assertEquals(command,null);
    }
    */

}
