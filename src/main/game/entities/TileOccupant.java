package game.entities;

import game.gameboard.Location;

/**
 * Created by gavin on 2/4/17.
 */
public abstract class TileOccupant {
    private Location location;

    public Location getLocation() {
        return this.location;
    }
}
