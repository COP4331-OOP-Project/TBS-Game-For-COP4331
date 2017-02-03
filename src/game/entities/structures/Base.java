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

    public Base( int prod, Location loc, int owner){
        super(loc,owner);
        this.setAttackDamage(10);
        this.setDefenseDamage(5);
        this.setArmor(5);
        this.setHealth(50);
        this.setUpkeep(2.0f);
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
