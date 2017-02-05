package controls.TypeInstance;

import controls.Army.ArmyEnum;
import controls.RallyPoint.RallyPointEnum;
import controls.Structure.StructureEnum;
import controls.Unit.UnitEnum;
import game.Player;
import game.entities.Army;
import game.entities.RallyPoint;
import game.entities.TileOccupant;
import game.entities.structures.Base;
import game.entities.structures.Structure;
import game.entities.units.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * Created by gavin on 2/3/17.
 */
public class TypeInstanceController {
    private final static Logger log = LogManager.getLogger(TypeInstanceController.class);

    private Object currentTypeInstance;
    private Player currentPlayer;
    private Army currentArmy;
    private ArrayList<Unit> currentArmyUnits;
    private Integer currentArmyUnit;
    private ArrayList<Unit> currentArmyBattleGroups;
    private Integer currentArmyBattleGroup;
    private ArrayList<Unit> currentArmyReinforcements;
    private Integer currentArmyReinforcement;
    private ArrayList<Melee> melees;
    private Integer currentMelee;
    private ArrayList<Ranged> ranges;
    private Integer currentRanged;
    private ArrayList<Explorer> explorers;
    private Integer currentExplorer;
    private ArrayList<Colonist> colonists;
    private Integer currentColonist;
    private ArrayList<Base> bases;
    private Integer currentBase;
    private ArrayList<RallyPoint> rallyPoints;
    private Integer currentRallyPoint;


    public TypeInstanceController(Player p, Enum currentType) {
        this.currentArmyUnit = -1;
        this.currentArmyBattleGroup = -1;
        this.currentArmyReinforcement = -1;
        this.melees = p.getMelees();
        this.currentMelee = -1;
        this.ranges = p.getRanges();
        this.currentRanged = -1;
        this.explorers = p.getExplorers();
        this.currentExplorer = -1;
        this.colonists = p.getColonists();
        this.currentColonist = -1;
        this.bases = p.getBases();
        this.currentBase = -1;
        this.rallyPoints = p.getArmyRallyPoint();
        this.currentRallyPoint = -1;

        this.cycleForward(currentType);
    }

    public void setCurrentArmy(Army army) {
        this.currentArmy = army;
        this.currentArmyReinforcements = this.currentArmy.getReinforcements();
        this.currentArmyBattleGroups = this.currentArmy.getBattleGroup();
        this.currentArmyUnits = new ArrayList<Unit>(this.currentArmyBattleGroups);
        this.currentArmyUnits.addAll(this.currentArmyReinforcements);

        this.currentArmyUnit = -1;
        this.currentArmyBattleGroup = -1;
        this.currentArmyReinforcement = -1;
    }

    public Object getTypeInstance() {
        return this.currentTypeInstance;
    }

    public TileOccupant cycleForward(Enum currentType) {
        if (currentType instanceof UnitEnum) {
            UnitEnum unitEnum = (UnitEnum) currentType;
            return cycleUnitEnum(unitEnum, true);
        } else if (currentType instanceof StructureEnum) {
            StructureEnum structureEnum = (StructureEnum) currentType;
            return cycleStructureEnum(structureEnum, true);
        } else if (currentType instanceof ArmyEnum) {
            ArmyEnum armyEnum = (ArmyEnum) currentType;
            return cycleArmyEnum(armyEnum, true);
        } else if (currentType instanceof RallyPointEnum) {
            return cycleRallyPoint(true);
        }
        log.error("Enum: " + currentType + " not cast to type. Could not cycle forward.");
        return null;
    }

    public TileOccupant cycleBackward(Enum currentType) {
        if (currentType instanceof UnitEnum) {
            UnitEnum unitEnum = (UnitEnum) currentType;
            return cycleUnitEnum(unitEnum, false);
        } else if (currentType instanceof StructureEnum) {
            StructureEnum structureEnum = (StructureEnum) currentType;
            return cycleStructureEnum(structureEnum, false);
        } else if (currentType instanceof ArmyEnum) {
            ArmyEnum armyEnum = (ArmyEnum) currentType;
            return cycleArmyEnum(armyEnum, false);
        } else if (currentType instanceof RallyPointEnum) {
            return cycleRallyPoint(false);
        }
        log.error("Enum: " + currentType + " not cast to type. Could not cycle backward.");
        return null;
    }

    private ItemWithIndex getNextInArrayList(ListWithIndex itemWithIndex) {
        ArrayList<?> list = itemWithIndex.getList();
        Integer index = itemWithIndex.getIndex();
        if (list.isEmpty()) return null;
        Integer newIndex = index + 1;
        if (newIndex > list.size() - 1) {
            return new ItemWithIndex(list.get(0), 0);
        }
        return new ItemWithIndex(list.get(newIndex), newIndex);
    }

    private ItemWithIndex getPreviousInArrayList(ListWithIndex itemWithIndex) {
        ArrayList<?> list = itemWithIndex.getList();
        Integer index = itemWithIndex.getIndex();
        if (list.isEmpty()) return null;
        Integer newIndex = index - 1;
        if (newIndex < 0) {
            return new ItemWithIndex(list.get(list.size()-1), list.size()-1);
        }
        return new ItemWithIndex(list.get(newIndex), newIndex);
    }

    private Unit cycleUnitEnum(UnitEnum unit, boolean forward) {
        switch(unit) {
            case MELEE: {
                ItemWithIndex item;
                if (forward) {
                    item = getNextInArrayList(new ListWithIndex(melees, currentMelee));
                } else {
                    item = getPreviousInArrayList(new ListWithIndex(melees, currentMelee));
                }
                if (item == null) return null;
                this.currentMelee = item.getIndex();
                this.currentTypeInstance = item.getItem();
                return (Melee) this.currentTypeInstance;
            }
            case RANGED: {
                ItemWithIndex item;
                if (forward) {
                    item = getNextInArrayList(new ListWithIndex(ranges, currentRanged));
                } else {
                    item = getPreviousInArrayList(new ListWithIndex(ranges, currentRanged));
                }
                if (item == null) return null;
                this.currentRanged = item.getIndex();
                this.currentTypeInstance = item.getItem();
                return (Ranged) this.currentTypeInstance;
            }
            case EXPLORER: {
                ItemWithIndex item;
                if (forward) {
                    item = getNextInArrayList(new ListWithIndex(explorers, currentExplorer));
                } else {
                    item = getPreviousInArrayList(new ListWithIndex(explorers, currentExplorer));
                }
                if (item == null) return null;
                this.currentExplorer = item.getIndex();
                this.currentTypeInstance = item.getItem();
                return (Explorer) this.currentTypeInstance;
            }
            case COLONIST: {
                ItemWithIndex item;
                if (forward) {
                    item = getNextInArrayList(new ListWithIndex(colonists, currentColonist));
                } else {
                    item = getPreviousInArrayList(new ListWithIndex(colonists, currentColonist));
                }
                if (item == null) return null;
                this.currentColonist = item.getIndex();
                this.currentTypeInstance = item.getItem();
                return (Colonist) this.currentTypeInstance;
            }
        }
        return null;
    }

    private Structure cycleStructureEnum(StructureEnum structure, boolean forward) {
        switch(structure) {
            case BASE: {
                ItemWithIndex item;
                if (forward) {
                    item = getNextInArrayList(new ListWithIndex(bases, currentBase));
                } else {
                    item = getPreviousInArrayList(new ListWithIndex(bases, currentBase));
                }
                if (item == null) return null;
                this.currentBase = item.getIndex();
                this.currentTypeInstance = item.getItem();
                return (Base) this.currentTypeInstance;
            }
        }
        return null;
    }

    private Unit cycleArmyEnum(ArmyEnum army, boolean forward) {
        switch(army) {
            case ENTIRE_ARMY: {
                ItemWithIndex item;
                if (forward) {
                    item = getNextInArrayList(new ListWithIndex(currentArmyUnits, currentArmyUnit));
                } else {
                    item = getPreviousInArrayList(new ListWithIndex(currentArmyUnits, currentArmyUnit));
                }
                if (item == null) return null;
                this.currentArmyUnit = item.getIndex();
                this.currentTypeInstance = item.getItem();
                return (Unit) this.currentTypeInstance;
            }
            case BATTLE_GROUP: {
                ItemWithIndex item;
                if (forward) {
                    item = getNextInArrayList(new ListWithIndex(currentArmyBattleGroups, currentArmyBattleGroup));
                } else {
                    item = getPreviousInArrayList(new ListWithIndex(currentArmyBattleGroups, currentArmyBattleGroup));
                }
                if (item == null) return null;
                this.currentArmyBattleGroup = item.getIndex();
                this.currentTypeInstance = item.getItem();
                return (Unit) this.currentTypeInstance;
            }
            case REINFORCEMENTS: {
                ItemWithIndex item;
                if (forward) {
                    item = getNextInArrayList(new ListWithIndex(currentArmyReinforcements, currentArmyReinforcement));
                } else {
                    item = getPreviousInArrayList(new ListWithIndex(currentArmyReinforcements, currentArmyReinforcement));
                }
                if (item == null) return null;
                this.currentArmyReinforcement = item.getIndex();
                this.currentTypeInstance = item.getItem();
                return (Unit) this.currentTypeInstance;
            }
        }
        return null;
    }


    private RallyPoint cycleRallyPoint(boolean forward) {
        ItemWithIndex item;
        if (forward) {
            item = getNextInArrayList(new ListWithIndex(rallyPoints, currentRallyPoint));
        } else {
            item = getPreviousInArrayList(new ListWithIndex(rallyPoints, currentRallyPoint));
        }
        if (item == null) return null;
        this.currentRallyPoint = item.getIndex();
        this.currentTypeInstance = item.getItem();
        return (RallyPoint) this.currentTypeInstance;
    }
}
