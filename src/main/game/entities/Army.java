package game.entities;

import game.commands.Command;
import game.entities.units.Unit;
import game.gameboard.Location;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

public class Army {

    private int armyID;
    private UUID id;                                    // Army unique id
    private int ownerID;                                // Player id
    private Location location;                          // Army location
    private RallyPoint rp;                              // Army Rally Point

    private ArrayList<Unit> battleGroup;                // Active battlegroup units
    private ArrayList<Unit> reinforcements;             // Reinforcing units

    private ArrayList<Command> commands;                // Army commands

    // Constructor
    public Army(Location loc, int playerId, RallyPoint rp, ArrayList<Unit> units) {

        this.id = UUID.randomUUID();                    // Army unique id
        this.location = loc;                            // Set unit location
        this.ownerID = playerId;                        // Set player id
        this.rp = rp;                                   // Set rally point

        this.battleGroup = units;                       // Initialize units
        this.reinforcements = new ArrayList<Unit>();    // Initialize reinforcements

    }

    // TODO: Setup function to filter all received commands to all units

    // TODO: Setup function to disband the army

    // Add one new unit to the reinforcements array
    public void addSingleReinforcement(Unit reinforcement) {
        this.reinforcements.add(reinforcement);
    }

    // Add a group of new units to reinforcements array
    public void addReinforcements(ArrayList<Unit> reinforcements) {
        for (Unit u : reinforcements) {
            this.reinforcements.add(u);
        }
    }

    // Print all units in an army
    public void printArmy() {
        Unit temp;
        Iterator<? super Unit> i = this.battleGroup.iterator();
        System.out.println("Owner: " + this.ownerID);
        while(i.hasNext()){
            temp = (Unit)i.next();
            System.out.println("    Soldier: " + temp.getUnitType());
        }
    }

    // Accessors

    // Set new rally point
    public void setRallyPoint(RallyPoint rp) {
    	this.rp = rp;
    }

    // Getters
    public UUID getId() { return id; }
    public int getOwnerID() { return ownerID; }
    public Location getLocation() { return location; }
    public RallyPoint getRp() { return rp; }
    public ArrayList<Unit> getBattleGroup() { return battleGroup; }
    public ArrayList<Unit> getReinforcements() { return reinforcements; }
    public RallyPoint getRallyPoint() {
		return rp;

    }

    public void setArmyID(int armyID)
    {
        this.armyID = armyID;
    }

    public int getArmyID()
    {
        return this.armyID;
    }

}
