package game.entities.units;


import game.commands.Command;
import game.entities.structures.Structure;
import game.gameboard.Location;

import java.util.UUID;

public class Melee extends Unit
{
    public Melee(){}

    public Melee(Location loc, int ownerID) {

        super(loc, ownerID, 1);  // Call super constructor
        this.setUuid(UUID.randomUUID());
        setUnitStats(7, 7, 7, 10, 1.0f, 10, 3, 1);

     }

}
