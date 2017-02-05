package game.entities.factories;

/**
 *  Exception thrown when the entity code is not known and nothing can be made
 */
public class UnknownEntityCodeException extends Exception {

    // Constructor
    public UnknownEntityCodeException() {}

    // Constructor w/ message
    public UnknownEntityCodeException(String msg) {
        super(msg);
    }

}
