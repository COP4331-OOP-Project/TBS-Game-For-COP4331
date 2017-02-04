package game.entities.structures;

import game.commands.Command;
import game.entities.ICombatant;
import game.entities.IMake;
import game.entities.units.Unit;
import game.gameboard.Location;

import java.util.LinkedList;

/**
 * Created by David on 2/3/2017.
 */
public class Base extends Structure implements IMake {

    private int productionRate;         // Structure production rate

    // Constructor
    public Base(Location loc, int owner){

        super(loc, owner);
        setStructureStats(10, 5, 5, 20, 1.0f, 10);
        this.productionRate=1;

    }

    // Make command
    public Object makeEntity(String entityCode) {
        return new Object();
    }

}
