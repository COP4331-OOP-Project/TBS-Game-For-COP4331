package game.entities;


import game.entities.units.Unit;
import game.gameboard.Location;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class Army
{
    public int ownerID;
    public ArrayList<Unit> soldiers;
    private int armyID;

    public Army()
    {
      this.soldiers = new ArrayList<Unit>();
    }

    public Army(Unit u)
    {
        this.ownerID = u.getOwnerID();
        this.soldiers = new ArrayList<Unit>();
        soldiers.add(u);
    }

    public void addSoldier(Unit u)
    {
        this.soldiers.add(u);
    }

    public void printArmy()
    {
        Unit temp;
        Iterator<? super Unit> i = this.soldiers.iterator();
        while(i.hasNext()){
            temp = (Unit)i.next();
            System.out.println("    Soldier: " + temp.getUnitType());
        }
    }

    public Location getLocation()
    {
        return soldiers.get(0).getLocation();
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
