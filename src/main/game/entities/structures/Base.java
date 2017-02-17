package game.entities.structures;

import game.entities.IMake;
import game.gameboard.Location;

/**
 * Created by David on 2/3/2017.
 */
public class Base extends Structure implements IMake {

    private int productionRate;         // structure production rate

    // Constructor
    public Base(Location loc, int owner) {

        super(loc, owner);
        setStats(10, 5, 5, 20, 10);
        this.productionRate = 1;

    }

    // Make command
    public Object makeEntity(String entityCode) {
        return new Object();
    }

}
