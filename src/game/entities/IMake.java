package game.entities;

/**
 * Interface for entities capable of entity creation
 */
public interface IMake {

    Object makeEntity(String entityCode);     // Make entity from given code

}
