package game.entities.factories;

import game.entities.Army;
import game.entities.RallyPoint;
import game.entities.structures.*;
import game.entities.units.*;
import game.gameboard.Location;

import java.util.ArrayList;


/**
 * Factory for creating entities
 */
public class EntityFactory {

    // Create new entity based on given entityCode for at location for player of given id
    public static Object getEntity(Location location, int playerId, String entityCode) {

        Object entity;  // New entity to create

        // Select entity based on type
        switch (entityCode) {
            case "base":
                entity = new Base(location, playerId);
            case "melee":
                entity = new Melee();
            case "ranged":
                entity = new Ranged();
            case "worker":
                entity = new Worker();
            case "explorer":
                entity = new Explorer();
            case "colonist":
                entity = new Colonist();
//            case "rallyPoint":
//                entity = new RallyPoint();
            default:
                System.out.println("Unknown entity code specified");
                entity = null;

        }

        return entity;  // Return entity
    }

    // Create army given the location, the player id, the rally point for the army, and the list of units included
    public static Army getArmy(Location location, int playerId, RallyPoint rp, ArrayList<Unit> units) {
        return new Army(location, playerId, rp, units);
    }

}
