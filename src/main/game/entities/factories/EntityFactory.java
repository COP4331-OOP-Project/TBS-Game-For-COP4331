package game.entities.factories;

import game.entities.Army;
import game.entities.RallyPoint;
import game.entities.structures.*;
import game.entities.units.*;
import game.gameboard.GameBoard;
import game.gameboard.Location;

import java.util.ArrayList;


/**
 * Factory for creating entities
 */
public class EntityFactory {

    // Create new entity based on given entityCode for at location for player of given id
    public static Object getEntity(Location location, int playerId, String entityCode) throws UnknownEntityCodeException {

        Object entity;  // New entity to create

        // Select entity based on type
        switch (entityCode.toLowerCase()) {
            case "base":
                entity = new Base(location, playerId);
                break;
            case "melee":
                entity = new Melee(location, playerId);
                break;
            case "ranged":
                entity = new Ranged(location, playerId);
                break;
            case "worker":
                entity = new Worker(location, playerId);
                break;
            case "explorer":
                entity = new Explorer(location, playerId);
                break;
            case "colonist":
                entity = new Colonist(location, playerId);
                break;
            default:
                throw new UnknownEntityCodeException("Entity code not found!");

        }

        return entity;  // Return entity
    }

    // Create army given the location, the player id, the rally point for the army, and the list of units included
    public static Army getArmy(Location location, int playerId, RallyPoint rp, ArrayList<Unit> units) {
        return new Army(location, playerId, rp, units);
    }

    // Create new rally point given the target location and the gameboard
    public static RallyPoint getRallyPoint(Location location, GameBoard gameBoard, int ownerID) {
        return new RallyPoint(location, gameBoard, ownerID);
    }
}
