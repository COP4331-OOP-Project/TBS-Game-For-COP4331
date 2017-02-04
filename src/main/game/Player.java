package game;
import game.entities.Army;
import game.entities.structures.Base;
import game.entities.units.Unit;
import game.entities.units.Melee;
import game.entities.units.Ranged;
import game.entities.units.Explorer;
import game.entities.units.Worker;
import game.entities.units.Colonist;
import game.entities.structures.Structure;

import java.awt.font.NumericShaper;
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
	
	private ArrayList<Army> armies;
	private ArrayList<Melee> melees;
	private ArrayList<Ranged> ranges;
	private ArrayList<Explorer> explorers;
	private ArrayList<Colonist> colonists;
	private ArrayList<Worker> workers;
	private ArrayList<Structure> structures;
	private ArrayList<Base> bases;
	private ArrayList<Object> rallyPoints;
	
	private ArrayList<Unit> totalUnits;
	
	//Constructor
	public Player(int id){
		this.playerID = id;
		armies = new ArrayList<Army>();
		melees = new ArrayList<Melee>();
		ranges = new ArrayList<Ranged>();
		explorers = new ArrayList<Explorer>();
		colonists = new ArrayList<Colonist>();
		workers = new ArrayList<Worker>();
		structures = new ArrayList<Structure>();
		bases = new ArrayList<Base>();
		rallyPoints = new ArrayList<Object>();

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

	public int getBaseCount(){
		return bases.size();
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


	//Unit ID:
	/*
	 * Melee:	 01
	 * Range:	 02
	 * Explorer: 03
	 * colonists:04
	 * Worker:	 05
	 */
	public void addUnit(Unit unit)
	{
		switch(unit.getUnitType())
		{
			case 1:
				melees.add((Melee)unit);
				break;
			case 2:
				ranges.add((Ranged)unit);
				break;
			case 3:
				explorers.add((Explorer)unit);
				break;
			case 4:
				colonists.add((Colonist)unit);
				break;
			case 5:
				workers.add((Worker)unit);
			default:
				break;

		}
		totalUnits.add(unit);
	}

	public void addStructure(Structure structure)
	{
		//structures.add(1);
		bases.add((Base)structure);
		structures.add(structure);
	}
	
	public void addArmy(Army army)
	{
		armies.add(army);

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
