package game.factories;

import game.entities.factories.EntityFactory;
import game.entities.factories.UnknownEntityCodeException;
import game.gameboard.Location;
import org.junit.Test;

/**
 * Created by Alex on 2/5/17.
 */
public class EntityFactoryTest {

    private Location loc = new Location(0, 1);

    @Test
    public void testEntityCodeCaseSensitivity() {

        try {

            Object entity = EntityFactory.getEntity(loc, 0, "Colonist");
            assert (!entity.equals(null));
            entity = EntityFactory.getEntity(loc, 0, "colonist");
            assert (!entity.equals(null));

        } catch (UnknownEntityCodeException e) {
        }


    }

    @Test(expected = UnknownEntityCodeException.class)
    public void testUnknownEntityCode() throws UnknownEntityCodeException {
        Object entity = EntityFactory.getEntity(loc, 0, "unknownEntityType");
    }


}
