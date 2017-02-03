package game.entities.structures;

import game.commands.Command;
import game.entities.units.Unit;
import game.gameboard.Location;

import java.util.LinkedList;

/**
 * Created by David on 2/3/2017.
 */
public class Base extends Structure{
    private int productionRate;

    public Base(int ad, int dd, int armor, int health, int upkeep, int ownerID, Location loc, int prod){
        super(ad,dd,armor,health,upkeep,ownerID,loc);
        this.productionRate=prod;
    }

    //TODO Handle Commands
    public void ExecuteNextCommand(){

    }
    public void Attack(int direction){

    }
    public void MakeUnit(Unit unit){

    }
    public void Defend(int direction){

    }
    public void Heal(Unit unit){

    }
    public void Decomission(){

    }
    public void PowerDown(){

    }
    public void PowerUp(){

    }

}
