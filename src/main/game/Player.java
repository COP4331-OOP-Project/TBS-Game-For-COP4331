package game;
import game.entities.Army;
import game.entities.RallyPoint;
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
	private int playerID;
	
	private int resourceCount;

	// TODO: Make unit ID's an enum
	//Unit ID:
	/*
	 * Melee:	 01
	 * Range:	 02
	 * Explorer: 03
	 * colonists:04
	 * Worker:	 05
	 */

	private boolean isUnitListFull;

	private int meleeCount;
	private int rangeCount;
	private int armiesCount;
	private int explorersCount;
	private int colonistsCount;
	private int workersCount;
	private int structuresCount;
	private int totalUnitsCount;
	private int basesCount;
	private int rallyPointsCount;


	private ArrayList<Army> armies;
	private ArrayList<Melee> melees;
	private ArrayList<Ranged> ranges;
	private ArrayList<Explorer> explorers;
	private ArrayList<Colonist> colonists;
	private ArrayList<Worker> workers;
	private ArrayList<Structure> structures;

	private ArrayList<Base> bases;

	private ArrayList<RallyPoint> rallyPoints;
	
	private ArrayList<Unit> totalUnits;
	
	//Constructor
	public Player(int id){
		this.playerID = id;
		init();
	}

	private void init()
	{
		armies = new ArrayList<Army>();
		melees = new ArrayList<Melee>();
		ranges = new ArrayList<Ranged>();
		explorers = new ArrayList<Explorer>();
		colonists = new ArrayList<Colonist>();
		workers = new ArrayList<Worker>();
		structures = new ArrayList<Structure>();
		bases = new ArrayList<Base>();
		rallyPoints = new ArrayList<RallyPoint>();
		totalUnits = new ArrayList<Unit>();

		meleeCount = 0;
		rangeCount = 0;
		armiesCount = 0;
		explorersCount = 0;
		colonistsCount = 0;
		workersCount = 0;
		structuresCount = 0;
		totalUnitsCount = 0;
		basesCount = 0;
		rallyPointsCount = 0;

		for(int i = 0; i<10;i++)
		{
			armies.add(null);
			melees.add(null);
			ranges.add(null);
			explorers.add(null);
			colonists.add(null);
			workers.add(null);
			structures.add(null);
			bases.add(null);
			rallyPoints.add(null);
		}
		for(int i = 0; i<25;i++)
		{
			totalUnits.add(null);
		}
	}
	public int getPlayerID() { return this.playerID; }
	//accessors
	public int getResourceCount()
	{
		return resourceCount;
	}

	public int getUnitCount(int unitType)
	{
		switch(unitType) {
		case 1: return meleeCount;
		case 2: return rangeCount;
		case 3: return explorersCount;
		case 4: return colonistsCount;
		case 5: return workersCount;
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
		return totalUnits.size();
	}
	public ArrayList<Object> getArmyRallyPoint()
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
		if(totalUnitsCount>=25)
		{
			System.out.println("Unit list full");
		}
		else {
			switch (unit.getUnitType()) {
				case 1: {
					boolean isUnitAdded = false;
					for (int i = 0; i < melees.size(); i++) {
						if (melees.get(i) == null) {
							melees.set(i, (Melee) unit);
							unit.setUnitID(i);
							isUnitAdded = true;
							meleeCount++;
							break;
						}
					}
					if (!isUnitAdded)
						System.out.println("Melee list full");
					break;
				}
				case 2: {
					boolean isUnitAdded = false;
					for (int i = 0; i < ranges.size(); i++) {
						if (ranges.get(i) == null) {
							ranges.set(i, (Ranged) unit);
							unit.setUnitID(i);
							isUnitAdded = true;
							rangeCount++;
							break;
						}
					}
					if (!isUnitAdded)
						System.out.println("Range list full");
					break;
				}
				case 3: {
					boolean isUnitAdded = false;
					for (int i = 0; i < explorers.size(); i++) {
						if (explorers.get(i) == null) {
							explorers.set(i, (Explorer) unit);
							unit.setUnitID(i);
							isUnitAdded = true;
							explorersCount++;
							break;
						}
					}
					if (!isUnitAdded)
						System.out.println("Explorers list full");
					break;
				}
				case 4: {
					boolean isUnitAdded = false;
					for (int i = 0; i < colonists.size(); i++) {
						if (colonists.get(i) == null) {
							colonists.set(i, (Colonist) unit);
							unit.setUnitID(i);
							isUnitAdded = true;
							colonistsCount++;
							break;
						}
					}
					if (!isUnitAdded)
						System.out.println("Colonists list full");
					break;
				}
				case 5: {
					boolean isUnitAdded = false;
					for (int i = 0; i < workers.size(); i++) {
						if (workers.get(i) == null) {
							workers.set(i, (Worker) unit);
							unit.setUnitID(i);
							isUnitAdded = true;
							workersCount++;
							break;
						}
					}
					if (!isUnitAdded)
						System.out.println("Worker list full");
					break;
				}
				default:
					break;
			}
			for (int i = 0; i < totalUnits.size(); i++) {
				if (totalUnits.get(i) == null) {
					totalUnits.add(unit);
					totalUnitsCount++;
					break;
				}
			}
		}
	}

	public void removeUnit(Unit unit) {
		switch(unit.getUnitType()) {
			case 1: {
				melees.set(unit.getUnitID(),null);
				break;
			}
			case 2: {
				ranges.set(unit.getUnitID(),null);
				break;
			}
			case 3: {
				explorers.set(unit.getUnitID(),null);
				break;
			}
			case 4: {
				colonists.set(unit.getUnitID(),null);
				break;
			}
			case 5: {
				workers.set(unit.getUnitID(),null);
				break;
			}
			default:
				break;

		}
		for(int i = 0;i<totalUnits.size();i++) {
			if(totalUnits.get(i)!=null) {
				if((totalUnits.get(i).getUnitID() == unit.getUnitID())&&(totalUnits.get(i).getUnitType() == unit.getUnitType()))
				{
					totalUnits.set(i,null);
				}
			}
		}
	}

	public void addStructure(Structure structure) {
		//structures.add(1);
		for(int i = 0;i<structures.size();i++)
		{
			if(structures.get(i)==null){
				structures.set(i, structure);
				structure.setStructureID(i);
				bases.set(i,(Base)structure);
			}
		}
	}

	public void removeStructure(Structure strucutre) {
		structures.set(strucutre.getStructureID(),null);
	}

	public void addArmy(Army army) {
		for(int i = 0;i<armies.size();i++) {
			if (armies.get(i)==null) {
				armies.set(i,army);
				army.setArmyID(i);
				break;
			}
		}
	}
	
	public void addRallyPoint(RallyPoint rallyPoint)
	{
		for(int i = 0;i<rallyPoints.size();i++)
		{
			if(totalUnits.get(i)==null)
			{
				rallyPoints.add((rallyPoint));
				rallyPointsCount++;
				break;
			}
		}
	}

	public void addResources(int resource)
	{
		this.resourceCount = this.resourceCount+resource;
	}

    public ArrayList<Army> getArmies() {
        return armies;
    }

    public ArrayList<Melee> getMelees() {
        return melees;
    }

    public ArrayList<Ranged> getRanges() {
        return ranges;
    }

    public ArrayList<Explorer> getExplorers() {
        return explorers;
    }

    public ArrayList<Colonist> getColonists() {
        return colonists;
    }

    public ArrayList<Base> getBases() {
        return bases;
    }
	
}
