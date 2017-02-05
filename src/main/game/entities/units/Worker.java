package game.entities.units;


import game.gameboard.Location;

public class Worker extends Unit
{

  // Constructor
  public Worker(Location loc, int ownerID) {

    super(loc, ownerID, 5);  // Call super constructor & set stats
    setStats(1, 1, 1, 10, 1, 5, 10);

  }

}
