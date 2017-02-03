package game.entities;
import game.entities.units.Unit;

import java.util.*;

public class Army
{
    public char owner;
    public ArrayList<Unit> soldiers;
    public String uuid;

    public Army()
    {
        this.uuid = UUID.randomUUID().toString();
    }

    public Army(Unit u)
    {
        this.owner = u.owner;
        soldiers.add(u);
        soldiers = new ArrayList<Unit>();
        this.uuid = UUID.randomUUID().toString();
    }

    public void addSoldier(Unit u)
    {
        soldiers.add(u);
    }

    public void printArmy()
    {
        Unit temp;
        Iterator<? super Unit> i = this.soldiers.iterator();
        System.out.println("Owner: " + this.owner);
        while(i.hasNext()){
            temp = (Unit)i.next();
            System.out.println("    Soldier: " + temp.getUnitType());
        }
    }
}