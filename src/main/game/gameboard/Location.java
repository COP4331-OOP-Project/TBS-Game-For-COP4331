package game.gameboard;
/**
 * Created by David on 2/1/2017.
 */
public class Location {
    private int xIndex;
    private int yIndex;

    public Location(int xIndex, int yIndex){
        this.xIndex=xIndex;
        this.yIndex=yIndex;
    }

    public int getX() {
        return this.xIndex;
    }

    public int getY() {
        return this.yIndex;
    }

    public int hashCode() {
        return (100 * xIndex) + yIndex;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location loc = (Location) o;
        return xIndex == loc.xIndex && yIndex == loc.yIndex;
    }

    public Location directionLocation(int direction){
        switch (direction){
            case 0:
                return new Location(xIndex, yIndex - 1);

            case 45:
                return new Location(xIndex + 1, yIndex - 1);

            case 135:
                return new Location(xIndex + 1, yIndex);

            case 180:
                return new Location(xIndex, yIndex + 1);

            case 225:
                return new Location(xIndex - 1, yIndex + 1);

            case 315:
                return new Location(xIndex - 1, yIndex);
        }
        return this;
    }

}
