package game.entities;


import game.entities.units.Unit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class Army
{
    public int ownerID;
    public ArrayList<Unit> soldiers;
    public UUID uuid;

    public Army()
    {
      this.soldiers = new ArrayList<Unit>();
      this.uuid = UUID.randomUUID();
    }

    public Army(Unit u)
    {
        this.ownerID = u.getOwnerID();
        this.soldiers = new ArrayList<Unit>();
        soldiers.add(u);
        this.uuid = UUID.randomUUID();
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
}
