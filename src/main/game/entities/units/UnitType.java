package game.entities.units;

/**
 * Enum for Unit types
 */
public enum UnitType
{
    // Values for unit types
    MELEE (00),
    RANGED (01),
    EXPLORER (02),
    COLONIST (03),
    WORKER (04);

    private int type;   // Set value for enum type

    // Returns unit type
    public int type(){ return type; }

    // Constructor
    UnitType(int type){ this.type = type; }
}