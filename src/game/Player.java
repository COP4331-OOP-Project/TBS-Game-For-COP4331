package game;
import java.util.ArrayList;

public class Player {
	public int playerID;
	
	private int resourceCount;
	
	//Unit ID:
	/*
	 * Melee:	 01
	 * Range:	 02
	 * Explorer: 03
	 * colonists:04
	 * Worker:	 05
	 */
	
	private ArrayList<Object> armies; 
	private ArrayList<Object> melees;
	private ArrayList<Object> ranges;
	private ArrayList<Object> explorers;
	private ArrayList<Object> colonists;
	private ArrayList<Object> workers;
	private ArrayList<Object> structures;
	private ArrayList<Object> rallyPoints;
	
	private ArrayList<Object> totalUnits;
	
	//Constructor
	public Player(int id){
		this.playerID = id;
	}
	
	//accessors
	public int getResourceCount()
	{
		return resourceCount;
	}
	public int getUnitCount(int unitID)
	{
		switch(unitID) {
		case 1: return melees.size();
		case 2: return ranges.size();
		case 3: return explorers.size();
		case 4: return colonists.size();
		case 5: return workers.size();
		default: return 0;
		}
	}
	public int getStructureCount()
	{
		return structures.size();
	}
	public int getArmyCount()
	{
		return armies.size();
	}
	public int getTotalUnitCount()
	{
		int sum = melees.size()+ ranges.size()+explorers.size()+colonists.size()+workers.size();
		return sum;
	}
	public Object getArmyRallyPoint()
	{
		return rallyPoints;
	}
	
	public void addUnit(int unitID)
	{
		switch(unitID){
		
		case 1: melees.add(1);
		break;
		case 2: ranges.add(1);
		break;
		case 3: explorers.add(1);
		break;
		case 4: colonists.add(1);
		break;
		case 5: workers.add(1);
		break;
		default:
		break;
		}
	}
	
	public void addStructure()
	{
		structures.add(1);
	}
	
	public void addArmy(int armyID)
	{
		armies.add(1);
	}
	
	public void addRallyPoint(Object rallyPoint)
	{
		rallyPoints.add(1);
	}
	public void addResources(int resource)
	{
		this.resourceCount = this.resourceCount+resource;
	}
}
