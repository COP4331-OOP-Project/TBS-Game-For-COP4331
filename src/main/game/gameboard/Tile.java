package game.gameboard;

import game.entities.Army;
import game.entities.RallyPoint;
import game.entities.structures.Structure;
import game.entities.units.Unit;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by David on 2/1/2017.
 */
public class Tile {
    //0 Grass, 1 Sand, 2 Rivers(Impassable)
    private int Terrain;
    //Leave out AreaOfEffect, Resources, Item for iteration 1
    private ArrayList<Unit> units;
    private ArrayList<Army> armies;
    private ArrayList<RallyPoint> rallyPoints;
    private Structure structure;
    private Location location;
    public boolean containsUnit;
    public boolean containsArmy;
    public boolean containsRallyPoint;
    public boolean containsStructure;

    private int ownerID;

    //Constructors
    Tile(int tileType, Location location){
        Terrain=tileType;
        units=new ArrayList<Unit>();
        armies=new ArrayList<Army>();
        rallyPoints = new ArrayList<RallyPoint>();
        containsStructure=false;
        containsRallyPoint=false;
        containsUnit=false;
        containsArmy=false;
        structure=null;
        this.location=location;
    }

    //Setters, getters, removers
    public Location getlocation(){
        return location;
    }

    public void addUnit(Unit unit){
        units.add(unit);
        containsUnit=true;
    }

    public int getTileType()
    {
        return Terrain;
    }

    public void removeUnit(int unitID){
        units.remove(unitID);
        if(units.isEmpty()){
            containsUnit=false;
        }
    }

    public ArrayList<RallyPoint> getRallyPoints(){return rallyPoints;}

    public void addRallyPoint(RallyPoint rp){
        rallyPoints.add(rp);
        containsRallyPoint=true;
    }

    public void removeRallyPoint(RallyPoint rp){
        for(int i = 0; i<rallyPoints.size(); i++){
            if(rallyPoints.get(i).getRallyID()==rp.getRallyID()){
                rallyPoints.remove(i);
                break;
            }
        }
        if(rallyPoints.isEmpty()){
            containsRallyPoint=false;
        }
    }


    public ArrayList<Unit> getUnits(){
        return units;
    }

    public void addArmy(Army army){
        armies.add(army);
        containsArmy=true;
    }

    public void removeArmy(int armyID){
        for(int i = 0; i<armies.size(); i++){
            if(armies.get(i).getArmyID()==armyID){
                armies.remove(i);
                break;
            }
        }
        if(armies.isEmpty()){
            containsArmy=false;
        }
    }

    public ArrayList<Army> getArmies(){
        return armies;
    }

    public void setStructure(Structure structure){
        this.structure=structure;
        containsStructure=true;
    }

    public void removeStructure(){
        this.structure=null;
        containsStructure=false;
    }

    public Structure getStructure(){
        return structure;
    }

    //TODO Command Handling
    public void attackOccupants(){

    }

    public void healOccupants(){

    }

    //Set and get the owner id of the tile
    public void setOwnerID(int playerID)
    {
        this.ownerID = playerID;
    }
    public int getOwnerID()
    {
        return this.ownerID;
    }

    //Check whether this tile is occupied by enemy unit
    public boolean hasEnemyUnit(int playerID)
    {
        if (playerID!= this.ownerID) {
            return true;
        }
        else if (playerID==this.ownerID)
        {
            return false;
        }

        return false;
    }
}
