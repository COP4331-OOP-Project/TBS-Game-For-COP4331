package controls.TypeInstance;

import controls.Army.ArmyEnum;
import controls.Structure.StructureEnum;
import controls.TypeInstance.TypeInstanceController;
import controls.Unit.UnitEnum;
import game.Player;
import game.entities.Army;
import game.entities.RallyPoint;
import game.entities.factories.EntityFactory;
import game.entities.structures.Base;
import game.entities.structures.Structure;
import game.entities.units.*;
import game.gameboard.GameBoard;
import game.gameboard.Location;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by gavin on 2/4/17.
 */
public class TypeInstanceControllerTest {

    private TypeInstanceController typeInstanceController;
    private Player player;
    private Player player2;

    private ArrayList<Player> players;

    private RallyPoint rallyPoint;

    private Unit m1;
    private Unit m2;
    private Unit m3;

    private Unit r1;
    private Unit r2;
    private Unit r3;

    private Unit e1;
    private Unit e2;

    private Unit c1;

    private Army army1;
    private Army army2;
    private Army army3;

    private Structure base;

    @Before
    public void setUp() {
        Location loc = new Location(0,0);
        Location l = new Location(0,0);
        this.player = new Player(0,l);
        this.player2 = new Player(1,l);
        this.players = new ArrayList<>();
        this.players.add(this.player);
        this.players.add(this.player2);
        GameBoard gameBoard = new GameBoard(this.players);
        this.rallyPoint = EntityFactory.getRallyPoint(loc, gameBoard, player.getPlayerID());


        this.m1 = new Melee(loc, this.player.getPlayerID());
        this.m2 = new Melee(loc, this.player.getPlayerID());
        this.m3 = new Melee(loc, this.player.getPlayerID());

        this.r1 = new Ranged(loc, this.player.getPlayerID());
        this.r2 = new Ranged(loc, this.player.getPlayerID());
        this.r3 = new Ranged(loc, this.player.getPlayerID());

        this.e1 = new Explorer(loc, this.player.getPlayerID());
        this.e2 = new Explorer(loc, this.player.getPlayerID());

        this.c1 = new Colonist(loc, this.player.getPlayerID());

        ArrayList<Unit> army1Units = new ArrayList<Unit>();
        army1Units.add(this.e1);

        this.army1 = new Army(loc, 0, this.rallyPoint, army1Units);

        this.army1.addSingleBattleGroupMember(this.r1);
        this.army1.addSingleBattleGroupMember(this.r2);
        this.army1.addSingleBattleGroupMember(this.r3);
        this.army1.addSingleReinforcement(this.m2);
        this.army1.addSingleReinforcement(this.m3);

        ArrayList<Unit> army2Units = new ArrayList<Unit>();
        army2Units.add(this.m1);

        this.army2 = new Army(loc, 0, this.rallyPoint, army2Units);
        this.army2.addSingleBattleGroupMember(this.m2);
        this.army2.addSingleBattleGroupMember(this.m3);
        this.army2.addSingleReinforcement(this.r1);
        this.army2.addSingleReinforcement(this.r2);

        this.player.addArmy(this.army1);
        this.player.addUnit(this.m1);
        this.player.addUnit(this.m2);
        this.player.addUnit(this.m3);
        this.player.addUnit(this.r1);
        this.player.addUnit(this.r2);
        this.player.addUnit(this.r3);
        this.player.addUnit(this.e1);
        this.player.addUnit(this.e2);
        this.player.addUnit(this.c1);

        this.base = new Base(loc, 0);
        this.player.addStructure(this.base);

        this.typeInstanceController = new TypeInstanceController(this.player, UnitEnum.MELEE);
    }

    @Test
    public void cycleMeleeUnitForward() {
        Object initialUnit = this.typeInstanceController.getTypeInstance();
        Assert.assertTrue(initialUnit instanceof Melee);
        Assert.assertEquals(((Melee)initialUnit).getUnitID(), this.m1.getUnitID());

        Object result = this.typeInstanceController.cycleForward(UnitEnum.MELEE);
        Assert.assertTrue(result instanceof Melee);
        Assert.assertEquals(((Melee)result).getUnitID(), this.m2.getUnitID());

        Object result2 = this.typeInstanceController.cycleForward(UnitEnum.MELEE);
        Assert.assertTrue(result2 instanceof Melee);
        Assert.assertEquals(((Melee)result2).getUnitID(), this.m3.getUnitID());

        Object result3 = this.typeInstanceController.cycleForward(UnitEnum.MELEE);
        Assert.assertTrue(result3 instanceof Melee);
        Assert.assertEquals(((Melee)result3).getUnitID(), this.m1.getUnitID());
    }

    @Test
    public void cycleMeleeUnitBackward() {
        Object initialUnit = this.typeInstanceController.getTypeInstance();
        Assert.assertTrue(initialUnit instanceof Melee);
        Assert.assertEquals(((Melee)initialUnit).getUnitID(), this.m1.getUnitID());

        Object result = this.typeInstanceController.cycleBackward(UnitEnum.MELEE);
        Assert.assertTrue(result instanceof Melee);
        Assert.assertEquals(((Melee)result).getUnitID(), this.m3.getUnitID());

        Object result2 = this.typeInstanceController.cycleBackward(UnitEnum.MELEE);
        Assert.assertTrue(result2 instanceof Melee);
        Assert.assertEquals(((Melee)result2).getUnitID(), this.m2.getUnitID());

        Object result3 = this.typeInstanceController.cycleBackward(UnitEnum.MELEE);
        Assert.assertTrue(result3 instanceof Melee);
        Assert.assertEquals(((Melee)result3).getUnitID(), this.m1.getUnitID());
    }

    @Test
    public void cycleRangeUnitForward() {
        Object result = this.typeInstanceController.cycleForward(UnitEnum.RANGED);
        Assert.assertTrue(result instanceof Ranged);
        Assert.assertEquals(((Ranged)result).getUnitID(), this.r1.getUnitID());

        Object result2 = this.typeInstanceController.cycleForward(UnitEnum.RANGED);
        Assert.assertTrue(result2 instanceof Ranged);
        Assert.assertEquals(((Ranged)result2).getUnitID(), this.r2.getUnitID());

        Object result3 = this.typeInstanceController.cycleForward(UnitEnum.RANGED);
        Assert.assertTrue(result3 instanceof Ranged);
        Assert.assertEquals(((Ranged)result3).getUnitID(), this.r3.getUnitID());

        Object result4 = this.typeInstanceController.cycleForward(UnitEnum.RANGED);
        Assert.assertTrue(result4 instanceof Ranged);
        Assert.assertEquals(((Ranged)result4).getUnitID(), this.r1.getUnitID());
    }

    @Test
    public void cycleRangeUnitBackward() {
        Object result = this.typeInstanceController.cycleBackward(UnitEnum.RANGED);
        Assert.assertTrue(result instanceof Ranged);
        Assert.assertEquals(((Ranged)result).getUnitID(), this.r3.getUnitID());

        Object result2 = this.typeInstanceController.cycleBackward(UnitEnum.RANGED);
        Assert.assertTrue(result2 instanceof Ranged);
        Assert.assertEquals(((Ranged)result2).getUnitID(), this.r2.getUnitID());

        Object result3 = this.typeInstanceController.cycleBackward(UnitEnum.RANGED);
        Assert.assertTrue(result3 instanceof Ranged);
        Assert.assertEquals(((Ranged)result3).getUnitID(), this.r1.getUnitID());

        Object result4 = this.typeInstanceController.cycleBackward(UnitEnum.RANGED);
        Assert.assertTrue(result4 instanceof Ranged);
        Assert.assertEquals(((Ranged)result4).getUnitID(), this.r3.getUnitID());
    }

// TODO: SAME
//    @Test
//    public void cycleExplorerUnitForward() {
//        Object result = this.typeInstanceController.cycleForward(UnitEnum.EXPLORER);
//        Assert.assertTrue(result instanceof Explorer);
//        Assert.assertEquals(((Explorer)result).getUnitID(), this.e1.getUnitID());
//
//        Object result2 = this.typeInstanceController.cycleForward(UnitEnum.EXPLORER);
//        Assert.assertTrue(result2 instanceof Explorer);
//        Assert.assertEquals(((Explorer)result2).getUnitID(), this.e2.getUnitID());
//
//        Object result3 = this.typeInstanceController.cycleForward(UnitEnum.EXPLORER);
//        Assert.assertTrue(result3 instanceof Explorer);
//        Assert.assertEquals(((Explorer)result3).getUnitID(), this.e1.getUnitID());
//    }
//
//    @Test
//    public void cycleExplorerUnitBackward() {
//        Object result = this.typeInstanceController.cycleBackward(UnitEnum.EXPLORER);
//        Assert.assertTrue(result instanceof Explorer);
//        Assert.assertEquals(((Explorer)result).getUnitID(), this.e2.getUnitID());
//
//        Object result2 = this.typeInstanceController.cycleBackward(UnitEnum.EXPLORER);
//        Assert.assertTrue(result2 instanceof Explorer);
//        Assert.assertEquals(((Explorer)result2).getUnitID(), this.e1.getUnitID());
//
//        Object result3 = this.typeInstanceController.cycleBackward(UnitEnum.EXPLORER);
//        Assert.assertTrue(result3 instanceof Explorer);
//        Assert.assertEquals(((Explorer)result3).getUnitID(), this.e2.getUnitID());
//    }
//TODO:FIX TESTS FOR INITIALIZED UNITS
//    @Test
//    public void cycleColonistUnitForward() {
//        Object result = this.typeInstanceController.cycleForward(UnitEnum.COLONIST);
//        Assert.assertTrue(result instanceof Colonist);
//
//
//        System.out.println("id 1 "+((Colonist)result).getUnitID());
//        System.out.println("id 2 "+ this.c1.getUnitID());
//
//        Assert.assertEquals(((Colonist)result).getUnitID(), this.c1.getUnitID());
//
//        Object result2 = this.typeInstanceController.cycleForward(UnitEnum.COLONIST);
//        Assert.assertTrue(result2 instanceof Colonist);
//        Assert.assertEquals(((Colonist)result2).getUnitID(), this.c1.getUnitID());
//
//    }

//    @Test
//    public void cycleColonistUnitBackward() {
//        Object result = this.typeInstanceController.cycleBackward(UnitEnum.COLONIST);
//        Assert.assertTrue(result instanceof Colonist);
//        Assert.assertEquals(((Colonist)result).getUnitID(), this.c1.getUnitID());
//
//        Object result2 = this.typeInstanceController.cycleBackward(UnitEnum.COLONIST);
//        Assert.assertTrue(result2 instanceof Colonist);
//        Assert.assertEquals(((Colonist)result2).getUnitID(), this.c1.getUnitID());
//
//    }

    @Test
    public void cycleBaseStructureForward() {
        Object result = this.typeInstanceController.cycleForward(StructureEnum.BASE);
        Assert.assertTrue(result instanceof Base);
        Assert.assertEquals(((Base)result).getStructureID(), this.base.getStructureID());

        Object result2 = this.typeInstanceController.cycleForward(StructureEnum.BASE);
        Assert.assertTrue(result2 instanceof Base);
        Assert.assertEquals(((Base)result2).getStructureID(), this.base.getStructureID());
    }

    @Test
    public void cycleBaseStructureBackward() {
        Object result = this.typeInstanceController.cycleBackward(StructureEnum.BASE);
        Assert.assertTrue(result instanceof Base);
        Assert.assertEquals(((Base)result).getStructureID(), this.base.getStructureID());

        Object result2 = this.typeInstanceController.cycleBackward(StructureEnum.BASE);
        Assert.assertTrue(result2 instanceof Base);
        Assert.assertEquals(((Base)result2).getStructureID(), this.base.getStructureID());
    }

    @Test
    public void cycleEntireArmyForward() {
        this.typeInstanceController.setCurrentArmy(this.army1);

        Object result = this.typeInstanceController.cycleForward(ArmyEnum.ENTIRE_ARMY);
        Assert.assertTrue(result instanceof Unit);
        Assert.assertEquals(((Unit)result).getUnitID(), this.e1.getUnitID());

        Object result2 = this.typeInstanceController.cycleForward(ArmyEnum.ENTIRE_ARMY);
        Assert.assertTrue(result2 instanceof Unit);
        Assert.assertEquals(((Unit)result2).getUnitID(), this.r1.getUnitID());

        Object result3 = this.typeInstanceController.cycleForward(ArmyEnum.ENTIRE_ARMY);
        Assert.assertTrue(result3 instanceof Unit);
        Assert.assertEquals(((Unit)result3).getUnitID(), this.r2.getUnitID());

        Object result4 = this.typeInstanceController.cycleForward(ArmyEnum.ENTIRE_ARMY);
        Assert.assertTrue(result4 instanceof Unit);
        Assert.assertEquals(((Unit)result4).getUnitID(), this.r3.getUnitID());

        Object result5 = this.typeInstanceController.cycleForward(ArmyEnum.ENTIRE_ARMY);
        Assert.assertTrue(result5 instanceof Unit);
        Assert.assertEquals(((Unit)result5).getUnitID(), this.m2.getUnitID());

        Object result6 = this.typeInstanceController.cycleForward(ArmyEnum.ENTIRE_ARMY);
        Assert.assertTrue(result6 instanceof Unit);
        Assert.assertEquals(((Unit)result6).getUnitID(), this.m3.getUnitID());

        Object result7 = this.typeInstanceController.cycleForward(ArmyEnum.ENTIRE_ARMY);
        Assert.assertTrue(result7 instanceof Unit);
        Assert.assertEquals(((Unit)result7).getUnitID(), this.e1.getUnitID());
    }

    @Test
    public void cycleEntireArmyBackward() {
        this.typeInstanceController.setCurrentArmy(this.army1);

        Object result2 = this.typeInstanceController.cycleBackward(ArmyEnum.ENTIRE_ARMY);
        Assert.assertTrue(result2 instanceof Unit);
        Assert.assertEquals(((Unit)result2).getUnitID(), this.m3.getUnitID());

        Object result3 = this.typeInstanceController.cycleBackward(ArmyEnum.ENTIRE_ARMY);
        Assert.assertTrue(result3 instanceof Unit);
        Assert.assertEquals(((Unit)result3).getUnitID(), this.m2.getUnitID());

        Object result4 = this.typeInstanceController.cycleBackward(ArmyEnum.ENTIRE_ARMY);
        Assert.assertTrue(result4 instanceof Unit);
        Assert.assertEquals(((Unit)result4).getUnitID(), this.r3.getUnitID());

        Object result5 = this.typeInstanceController.cycleBackward(ArmyEnum.ENTIRE_ARMY);
        Assert.assertTrue(result5 instanceof Unit);
        Assert.assertEquals(((Unit)result5).getUnitID(), this.r2.getUnitID());

        Object result6 = this.typeInstanceController.cycleBackward(ArmyEnum.ENTIRE_ARMY);
        Assert.assertTrue(result6 instanceof Unit);
        Assert.assertEquals(((Unit)result6).getUnitID(), this.r1.getUnitID());

        Object result7 = this.typeInstanceController.cycleBackward(ArmyEnum.ENTIRE_ARMY);
        Assert.assertTrue(result7 instanceof Unit);
        Assert.assertEquals(((Unit)result7).getUnitID(), this.e1.getUnitID());
    }

    @Test
    public void cycleBattleGroupForward() {
        this.typeInstanceController.setCurrentArmy(this.army2);

        Object result = this.typeInstanceController.cycleForward(ArmyEnum.BATTLE_GROUP);
        Assert.assertTrue(result instanceof Unit);
        Assert.assertEquals(((Unit)result).getUnitID(), this.m1.getUnitID());

        Object result2 = this.typeInstanceController.cycleForward(ArmyEnum.BATTLE_GROUP);
        Assert.assertTrue(result2 instanceof Unit);
        Assert.assertEquals(((Unit)result2).getUnitID(), this.m2.getUnitID());

        Object result3 = this.typeInstanceController.cycleForward(ArmyEnum.BATTLE_GROUP);
        Assert.assertTrue(result3 instanceof Unit);
        Assert.assertEquals(((Unit)result3).getUnitID(), this.m3.getUnitID());

        Object result6 = this.typeInstanceController.cycleForward(ArmyEnum.BATTLE_GROUP);
        Assert.assertTrue(result6 instanceof Unit);
        Assert.assertEquals(((Unit)result6).getUnitID(), this.m1.getUnitID());
    }

    @Test
    public void cycleBattleGroupBackward() {
        this.typeInstanceController.setCurrentArmy(this.army2);

        Object result4 = this.typeInstanceController.cycleBackward(ArmyEnum.BATTLE_GROUP);
        Assert.assertTrue(result4 instanceof Unit);
        Assert.assertEquals(((Unit)result4).getUnitID(), this.m3.getUnitID());

        Object result5 = this.typeInstanceController.cycleBackward(ArmyEnum.BATTLE_GROUP);
        Assert.assertTrue(result5 instanceof Unit);
        Assert.assertEquals(((Unit)result5).getUnitID(), this.m2.getUnitID());

        Object result6 = this.typeInstanceController.cycleBackward(ArmyEnum.BATTLE_GROUP);
        Assert.assertTrue(result6 instanceof Unit);
        Assert.assertEquals(((Unit)result6).getUnitID(), this.m1.getUnitID());
    }

    @Test
    public void cycleReinforcementsForward() {
        this.typeInstanceController.setCurrentArmy(this.army2);

        Object result4 = this.typeInstanceController.cycleForward(ArmyEnum.REINFORCEMENTS);
        Assert.assertTrue(result4 instanceof Unit);
        Assert.assertEquals(((Unit)result4).getUnitID(), this.r1.getUnitID());

        Object result5 = this.typeInstanceController.cycleForward(ArmyEnum.REINFORCEMENTS);
        Assert.assertTrue(result5 instanceof Unit);
        Assert.assertEquals(((Unit)result5).getUnitID(), this.r2.getUnitID());

        Object result6 = this.typeInstanceController.cycleForward(ArmyEnum.REINFORCEMENTS);
        Assert.assertTrue(result6 instanceof Unit);
        Assert.assertEquals(((Unit)result6).getUnitID(), this.r1.getUnitID());
    }

    @Test
    public void cycleReinforcementsBackward() {
        this.typeInstanceController.setCurrentArmy(this.army2);

        Object result5 = this.typeInstanceController.cycleBackward(ArmyEnum.REINFORCEMENTS);
        Assert.assertTrue(result5 instanceof Unit);
        Assert.assertEquals(((Unit)result5).getUnitID(), this.r2.getUnitID());

        Object result6 = this.typeInstanceController.cycleBackward(ArmyEnum.REINFORCEMENTS);
        Assert.assertTrue(result6 instanceof Unit);
        Assert.assertEquals(((Unit)result6).getUnitID(), this.r1.getUnitID());
    }
}
