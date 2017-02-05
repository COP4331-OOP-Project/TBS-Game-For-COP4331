package game;
import game.entities.Army;
import game.entities.RallyPoint;
import game.entities.factories.EntityFactory;
import game.entities.structures.Base;

import game.entities.units.Unit;
import game.entities.units.Melee;
import game.entities.units.Ranged;
import game.entities.units.Explorer;
import game.entities.units.Worker;
import game.entities.units.Colonist;
import game.entities.structures.Structure;
import game.gameboard.Location;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.text.html.parser.Entity;
import java.awt.font.NumericShaper;
import java.util.ArrayList;

public class Player {
	private int playerID;
	
	private int resourceCount;

	// TODO: Make unit ID's an enum
	//Unit ID:
	/*
	 * Melee:	 00
	 * Range:	 01
	 * Explorer: 02
	 * colonists:03
	 * Worker:	 04
	 */

	private boolean isUnitListFull;

	private final static Logger log = LogManager.getLogger(Player.class);
	
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
	//Inital Untis
	private Colonist initColonist;
	private Explorer initExplorer1;
	private Explorer initExplorer2;

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
	public Player(int id, Location loc){
		this.playerID = id;

		//Creates initial units
		initColonist = (Colonist) EntityFactory.getEntity(loc, this.playerID, "colonist");

		Location explorer1Location = new Location(loc.xIndex+1, loc.yIndex);
		initExplorer1 = (Explorer)EntityFactory.getEntity(explorer1Location, this.playerID,"explorer");
		Location explorer2Location = new Location(loc.xIndex, loc.yIndex+1);
		initExplorer2 = (Explorer)EntityFactory.getEntity(explorer2Location, this.playerID,"explorer");

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
		colonists.set(0, initColonist);
		initColonist.setUnitID(0);
		explorers.set(0, initExplorer1);
		initExplorer1.setUnitID(0);
		explorers.set(1, initExplorer2);
		initExplorer2.setUnitID(1);
		for(int i = 0; i<25;i++)
		{
			totalUnits.add(null);
		}
		totalUnits.set(0,initColonist);
		totalUnits.set(1, initExplorer1);
		totalUnits.set(2,initExplorer2);
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
		case 0: return meleeCount;
		case 1: return rangeCount;
		case 2: return explorersCount;
		case 3: return colonistsCount;
		case 4: return workersCount;
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
		return totalUnitsCount;
	}

	//@TODO GET RALLY POINT NOT FINISHED
	public ArrayList<RallyPoint> getArmyRallyPoint()
	{
		return rallyPoints;
	}


	//Unit ID:
	/*
	 * Melee:	 00
	 * Range:	 01
	 * Explorer: 02
	 * colonists:03
	 * Worker:	 04
	 */
	public void addUnit(Unit unit)
	{
		boolean isUnitAdded = false;

		if(totalUnitsCount>=25)
		{
			log.info("Unit list is full");
			return;
		}
		switch (unit.getUnitType()) {
			case 0: {
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
					log.info("Melee list full");
				break;
			}
			case 1: {
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
					log.info("Range list full");
				break;
			}
			case 2: {
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
					log.info("Explorers list full");
				break;
			}
			case 3: {
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
					log.info("Colonists list full");
				break;
			}
			case 4: {
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
					log.info("Worker list full");
				break;
			}
			default:
				break;
		}
		for (int i = 0; i < totalUnits.size(); i++) {
			if (totalUnits.get(i) == null && isUnitAdded == true) {
				totalUnits.add(unit);
				totalUnitsCount++;
				break;
			}
		}
	}


	public void removeUnit(Unit unit) {
		switch(unit.getUnitType()) {
			case 0: {
				melees.set(unit.getUnitID(),null);
				break;
			}
			case 1: {
				ranges.set(unit.getUnitID(),null);
				break;
			}
			case 2: {
				explorers.set(unit.getUnitID(),null);
				break;
			}
			case 3: {
				colonists.set(unit.getUnitID(),null);
				break;
			}
			case 4: {
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
				structure.setStructureID(i);
				structures.set(i, structure);
				bases.set(i,(Base)structure);
			}
		}
	}

	public void removeStructure(Structure strucutre) {
		structures.set(strucutre.getStructureID(),null);
		bases.set(strucutre.getStructureID(),null);
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
		ArrayList<Army> gArmies = new ArrayList<Army>();
		for(int i = 0;i<armies.size();i++)
		{
			if(armies.get(i)!=null)
			{
				gArmies.add(armies.get(i));
			}
		}
        return gArmies;
    }

    public ArrayList<Melee> getMelees() {
		ArrayList<Melee> gMelee = new ArrayList<Melee>();
		for(int i = 0;i<melees.size();i++)
		{
			if(melees.get(i)!=null)
			{
				gMelee.add(melees.get(i));
			}
		}
		return gMelee;
    }

    public ArrayList<Ranged> getRanges() {
		ArrayList<Ranged> gRange = new ArrayList<Ranged>();
		for(int i = 0;i<ranges.size();i++)
		{
			if(ranges.get(i)!=null)
			{
				gRange.add(ranges.get(i));
			}
		}
		return gRange;
    }

    public ArrayList<Explorer> getExplorers() {
		ArrayList<Explorer> gExplorers = new ArrayList<Explorer>();
		for(int i = 0;i<explorers.size();i++)
		{
			if(explorers.get(i)!=null)
			{
				gExplorers.add(explorers.get(i));
			}
		}
		return gExplorers;
    }

    public ArrayList<Colonist> getColonists() {
		ArrayList<Colonist> gColonists = new ArrayList<Colonist>();
		for(int i = 0;i<colonists.size();i++)
		{
			if(colonists.get(i)!=null)
			{
				gColonists.add(colonists.get(i));
			}
		}
		return gColonists;
    }

    public ArrayList<Base> getBases() {
		ArrayList<Base> gBases = new ArrayList<Base>();
		for(int i = 0;i<bases.size();i++)
		{
			if(bases.get(i)!=null)
			{
				gBases.add(bases.get(i));
			}
		}
		return gBases;
    }
	
}
