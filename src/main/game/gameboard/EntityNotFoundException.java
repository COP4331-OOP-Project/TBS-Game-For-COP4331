package game.gameboard;

/**
 * Exception for use for finding entity by id from tile
 */
public class EntityNotFoundException extends Exception {

    // Constructor
    public EntityNotFoundException() {
    }

    // Constructor w/ Message
    public EntityNotFoundException(String msg) {
        super(msg);
    }

}
