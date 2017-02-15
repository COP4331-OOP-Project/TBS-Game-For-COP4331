package game.gameboard;

import org.junit.Test;

/**
 * Created by Alex on 2/5/17.
 */
public class TileTest {

    private Tile tile = new Tile(TerrainEnum.WATER, new Location(0, 0));

    @Test
    public void testTimeImpassable() {
        assert(tile.isImpassable());
    }

}
