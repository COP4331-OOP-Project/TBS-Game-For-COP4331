package controls;

import controls.Army.ArmyEnum;
import controls.Structure.StructureEnum;
import controls.Structure.StructureType;
import controls.Unit.UnitEnum;
import game.Player;
import game.entities.structures.Structure;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

/**
 * Created by gavin on 2/4/17.
 */
public class TypeControllerTest {

    public TypeController typeController;
    public ModeEnum mode;
    public Player player;

    @Before
    public void setUp() {
        this.mode = ModeEnum.STRUCTURE;
        this.player = new Player(0);
        this.typeController = new TypeController(this.mode, this.player);
    }

    @Test
    public void constructor() {
        Assert.assertTrue(this.typeController.getType() instanceof StructureEnum);
    }

    @Test
    public void setModeStructure() {
        this.typeController.setMode(ModeEnum.STRUCTURE);
        Assert.assertTrue(this.typeController.getType() instanceof StructureEnum);
        Assert.assertEquals(this.typeController.getType(), StructureEnum.BASE);
    }

    @Test
    public void setModeUnit() {
        this.typeController.setMode(ModeEnum.UNIT);
        Assert.assertTrue(this.typeController.getType() instanceof UnitEnum);
        Assert.assertEquals(this.typeController.getType(), UnitEnum.EXPLORER);

    }

    @Test
    public void setModeArmy() {
        this.typeController.setMode(ModeEnum.ARMY);
        Assert.assertTrue(this.typeController.getType() instanceof ArmyEnum);
        Assert.assertEquals(this.typeController.getType(), ArmyEnum.ENTIRE_ARMY);
    }

    @Test
    public void setModeRallyPoint() {
        this.typeController.setMode(ModeEnum.RALLY_POINT);
        // There are no rally point types
        Assert.assertNull(this.typeController.getType());
    }

    @Test
    public void cycleStructureTypesForward() {
        this.typeController.cycleForward();
        Assert.assertEquals(this.typeController.getType(), StructureEnum.BASE);

        this.typeController.cycleForward();
        Assert.assertEquals(this.typeController.getType(), StructureEnum.BASE);
    }

    @Test
    public void cycleStructureTypesBackward() {
        this.typeController.cycleBackward();
        Assert.assertEquals(this.typeController.getType(), StructureEnum.BASE);

        this.typeController.cycleBackward();
        Assert.assertEquals(this.typeController.getType(), StructureEnum.BASE);
    }

    @Test
    public void cycleUnitTypesForward() {
        this.typeController.setMode(ModeEnum.UNIT);
        this.typeController.cycleForward();
        Assert.assertEquals(this.typeController.getType(), UnitEnum.COLONIST);

        this.typeController.cycleForward();
        Assert.assertEquals(this.typeController.getType(), UnitEnum.MELEE);

        this.typeController.cycleForward();
        Assert.assertEquals(this.typeController.getType(), UnitEnum.RANGED);

        this.typeController.cycleForward();
        Assert.assertEquals(this.typeController.getType(), UnitEnum.EXPLORER);
    }

    @Test
    public void cycleUnitTypesBackward() {
        this.typeController.setMode(ModeEnum.UNIT);
        this.typeController.cycleBackward();
        Assert.assertEquals(this.typeController.getType(), UnitEnum.RANGED);

        this.typeController.cycleBackward();
        Assert.assertEquals(this.typeController.getType(), UnitEnum.MELEE);

        this.typeController.cycleBackward();
        Assert.assertEquals(this.typeController.getType(), UnitEnum.COLONIST);

        this.typeController.cycleBackward();
        Assert.assertEquals(this.typeController.getType(), UnitEnum.EXPLORER);
    }

    @Test
    public void cycleArmyTypesForward() {
        this.typeController.setMode(ModeEnum.ARMY);
        this.typeController.cycleForward();
        Assert.assertEquals(this.typeController.getType(), ArmyEnum.BATTLE_GROUP);

        this.typeController.cycleForward();
        Assert.assertEquals(this.typeController.getType(), ArmyEnum.REINFORCEMENTS);

        this.typeController.cycleForward();
        Assert.assertEquals(this.typeController.getType(), ArmyEnum.ENTIRE_ARMY);
    }

    @Test
    public void cycleArmyTypesBackward() {
        this.typeController.setMode(ModeEnum.ARMY);
        this.typeController.cycleBackward();
        Assert.assertEquals(this.typeController.getType(), ArmyEnum.REINFORCEMENTS);

        this.typeController.cycleBackward();
        Assert.assertEquals(this.typeController.getType(), ArmyEnum.BATTLE_GROUP);

        this.typeController.cycleBackward();
        Assert.assertEquals(this.typeController.getType(), ArmyEnum.ENTIRE_ARMY);
    }

    @Test
    public void cannotCycleForwardNullType() {
        this.typeController.setMode(ModeEnum.RALLY_POINT);
        Assert.assertNull(this.typeController.cycleForward());
    }

    @Test
    public void cannotCycleBackwardNullType() {
        this.typeController.setMode(ModeEnum.RALLY_POINT);
        Assert.assertNull(this.typeController.cycleBackward());
    }
}