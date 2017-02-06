package game;

import controls.ModeController;
import controls.TypeController;
import controls.command.CommandController;
import controls.command.CommandEnum;
import controls.structure.StructureEnum;
import controls.typeInstance.TypeInstanceController;
import controls.unit.UnitEnum;
import game.commands.MakeCommand;
import game.commands.MoveCommand;
import game.entities.Army;
import game.entities.ICommandable;
import game.entities.RallyPoint;
import game.entities.structures.Structure;
import game.entities.units.Unit;
import game.gameboard.GameBoard;
import game.gameboard.Location;
import game.gameboard.Tile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class Game {

	//TODO: change player type to Player instead of Object
	private Player currentPlayer;
	private ArrayList<Player> players;
	private ModeController currentModeController;
	private GameBoard gBoard;
	private Player nextPlayer;
	private int selectedUnit = -1; //For rendering only
	private int turnNum;
	private ArrayList<MoveCommand> moveCommands;
	private Location lastMoveLocation;
	private ArrayList<Location> moveLocations;

    private Location centerCoordinates;
	private boolean centerCoordinatesUpdated;
	private boolean unitOverviewVisible = false;
	private boolean structureOverviewVisible = false;
	private boolean showingMakeDetails = false;
	private int makeOption = 0;

	private ICommandable currentSelectedEntity;
	private CommandEnum currentCommand;
    private final static Logger log = LogManager.getLogger(CommandController.class);


    Game() {
		//TODO: initialize game with players
		this.players = new ArrayList<Player>();

		Location initLocation1 = new Location(4,4);
		Location initLocation2 = new Location(4,10);
		Player player0 = new Player(0,initLocation1);
		Player player1 = new Player(1,initLocation2);
		players.add(player0);
		players.add(player1);

		this.nextPlayer = players.get(1);

		turnNum = 1;
		this.currentPlayer = player0;
		gBoard = new GameBoard(players);
        this.currentModeController = new ModeController(gBoard.getPlayer(0));
        this.centerCoordinatesUpdated = false;
        this.moveCommands = new ArrayList<>();
        this.moveLocations = new ArrayList<>();
	}

	public ArrayList<Location> getMoveLocations() {
        return this.moveLocations;
    }

	public void updateGame() { //This is called 20 times per second

	}

	public void startGame() {

	}

	public void endGame() {

	}

	// Assign showing the make details panel
	public boolean isShowingMakeDetails() { return showingMakeDetails; }
	public void setShowingMakeDetails(boolean val) {
    	this.makeOption = 0;
    	this.showingMakeDetails = val;
    }

	public boolean isFinished() {
		//TODO: determine game is finished conditions
		return false;
	}

	public void createGameBoard() {

	}

	public void endTurn(){
		Player temp = currentPlayer;
		currentPlayer = nextPlayer;
		nextPlayer = temp;
		if (currentPlayer.getPlayerID() == 0) {
            turnNum++;

            //Test army Band

			//getGameBoard().handleBandArmyCmd(players.get(0).getExplorers());
        }
        this.currentModeController = new ModeController(this.currentPlayer);

		ArrayList<ICommandable> playerEntities = new ArrayList<ICommandable>(this.currentPlayer.getAllUnit());
		playerEntities.addAll(this.currentPlayer.getArmyRallyPoint());
		playerEntities.addAll(this.currentPlayer.getBases());

		for (ICommandable commandable : playerEntities) {
		    commandable.doTurn();
        }
    }

	public int getTurnNum() {
		return turnNum;
	}
	
	public GameBoard getGameBoard() {
		return gBoard;
	}

	public void addPlayer(Player p) {
		this.players.add(p);
	}

	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}

	public Player getPlayer(int playerID) {
		return players.get(playerID);
	}

	public Enum getCurrentMode() {
		return this.currentModeController.getMode();
	}

	public Enum getCurrentType() {
		return this.currentModeController.getTypeController().getType();
	}
	
	public Enum getCurrentCommand() {
		return this.currentCommand;
	}

	public void centerOnCurrentTypeInstance() {
        TypeController typeController = this.currentModeController.getTypeController();
        TypeInstanceController typeInstanceController = typeController.getTypeInstanceController();
        ICommandable selectedEntity = typeInstanceController.getTypeInstance();
        if (selectedEntity == null) {
            log.error("Cannot center on instance because it does not exist");
            return;
        }
        Location newLocation = selectedEntity.getLocation();
        this.changeCenterCoordinates(newLocation);
        this.currentSelectedEntity = selectedEntity;
    }

    private void resetControls() {
        this.moveCommands = new ArrayList<>();
        this.moveLocations = new ArrayList<>();
        this.currentSelectedEntity = null;
        this.currentCommand = null;
    }

	protected void cycleModeForward() {
        this.resetControls();
        this.currentModeController.cycleForward();
		this.centerOnCurrentTypeInstance();
	}

	protected void cycleModeBackward() {
        this.resetControls();
        this.currentModeController.cycleBackward();
        this.centerOnCurrentTypeInstance();
    }

	protected void cycleTypeForward() {
        this.resetControls();
        this.currentModeController.getTypeController().cycleForward();
        this.centerOnCurrentTypeInstance();
    }

	protected void cycleTypeBackward() {
        this.resetControls();
        this.currentModeController.getTypeController().cycleBackward();
        this.centerOnCurrentTypeInstance();
    }

	protected void cycleCommandForward() {
        TypeController typeController = this.currentModeController.getTypeController();
        TypeInstanceController typeInstanceController = typeController.getTypeInstanceController();
        CommandController commandController = typeInstanceController.getCommandController();
        this.currentCommand = commandController.cycleForward();
        log.info("Current command = {}", this.currentCommand);
	}

	protected void cycleCommandBackward() {
        TypeController typeController = this.currentModeController.getTypeController();
        TypeInstanceController typeInstanceController = typeController.getTypeInstanceController();
        CommandController commandController = typeInstanceController.getCommandController();
        this.currentCommand = commandController.cycleBackward();
        log.info("Current command = {}", this.currentCommand);
    }

	protected void cycleTypeInstanceForward() {
	    this.resetControls();
	    TypeController typeController = this.currentModeController.getTypeController();
        TypeInstanceController typeInstanceController = typeController.getTypeInstanceController();
        Enum currentType = typeController.getType();
        ICommandable selectedEntity = typeInstanceController.cycleForward(currentType);
        this.currentSelectedEntity = selectedEntity;
        Location newLocation = selectedEntity.getLocation();
	    this.changeCenterCoordinates(newLocation);
	}

	protected void cycleTypeInstanceBackward() {
	    this.resetControls();
        TypeController typeController = this.currentModeController.getTypeController();
        TypeInstanceController typeInstanceController = typeController.getTypeInstanceController();
        Enum currentType = typeController.getType();
        ICommandable selectedEntity = typeInstanceController.cycleBackward(currentType);
        this.currentSelectedEntity = selectedEntity;
        Location newLocation = selectedEntity.getLocation();
        this.changeCenterCoordinates(newLocation);
	}

	protected void changeCenterCoordinates(Location loc) {
	    this.centerCoordinates = loc;
	    this.centerCoordinatesUpdated = true;
    }

    public Location getCenterCoordinates() {
        return centerCoordinates;
    }

    public boolean isCenterCoordinatesUpdated() {
        return centerCoordinatesUpdated;
    }

    public void setCenterCoordinatesUpdated(boolean updated) {
	    this.centerCoordinatesUpdated = updated;
    }

    public void addMoveToList(int direction) {
	    MoveCommand command = new MoveCommand<>(this.gBoard, this.currentSelectedEntity, direction, 0);
	    this.moveCommands.add(command);
	    Location newLocation = this.lastMoveLocation.directionLocation(direction);
	    this.moveLocations.add(newLocation);
	    this.lastMoveLocation = newLocation;
    }

    public void executeMoveCommand() {
	    for (MoveCommand command : this.moveCommands) {
	        this.currentSelectedEntity.addCommandToQueue(command);
        }
        this.moveCommands = new ArrayList<>();
        this.moveLocations = new ArrayList<>();

        System.out.println("Executed move command");
    }

	// Create chosen entity from selected command
    public void executeMakeCommand(Enum type, String entityCode) {

		if (type instanceof StructureEnum) {
			MakeCommand<Structure> makeCmd = new MakeCommand<Structure>(this.getGameBoard(), (Structure) this.currentSelectedEntity, 1, entityCode);
			this.currentSelectedEntity.addCommandToQueue(makeCmd);
		}
		else if (type instanceof UnitEnum) {
			MakeCommand<Unit> makeCmd = new MakeCommand<Unit>(this.getGameBoard(), (Unit) this.currentSelectedEntity, 1, entityCode);
			this.currentSelectedEntity.addCommandToQueue(makeCmd);
		}

	}

	public int getCurrentMakeOption() {
		return makeOption;
	}

	public void cycleMakeOptionUp() {
		this.makeOption--;
		if (this.makeOption < 0) this.makeOption = 3;
	}

	public void cycleMakeOptionDown() {
		this.makeOption++;
		this.makeOption%=4;
	}

//    MAKE, HEAL, ATTACK, DEFEND, POWER_UP, POWER_DOWN, CANCEL_COMMAND_QUEUE, DECOMISSION, MOVE;

    public CommandEnum executeCommand() {
	    switch(this.currentCommand) {
            case MOVE: {
                this.moveCommands = new ArrayList<>();
                this.moveLocations = new ArrayList<>();
                this.lastMoveLocation = this.currentSelectedEntity.getLocation();
                this.moveLocations.add(this.lastMoveLocation);
                return CommandEnum.MOVE;
            }
			case MAKE: {
				this.showingMakeDetails = true;
				return CommandEnum.MAKE;
			}
            default:
                return null;
        }
    }

    public void centerOnLastMoveLocation() {
	    this.changeCenterCoordinates(this.lastMoveLocation);
    }
    
    public void setSelectedUnit(int selectedUnit) {
    	this.selectedUnit = selectedUnit;
    }
    
    public int getSelectedUnit() {
    	return selectedUnit;
    }

	public void toggleUnitOverview() {
		if (structureOverviewVisible) {
			structureOverviewVisible = !structureOverviewVisible;
		}
		unitOverviewVisible = !unitOverviewVisible;
	}

	public void toggleStructureOverview() {
		if (unitOverviewVisible) {
			unitOverviewVisible = !unitOverviewVisible;
		}
		structureOverviewVisible = !structureOverviewVisible;
		
	}
	
	public boolean getUnitOverviewVisible() {
		return unitOverviewVisible;
	}
	
	public boolean getStructureOverviewVisible() {
		return structureOverviewVisible;
	}

	public void formArmy() {
	    Location currentLocation = this.currentSelectedEntity.getLocation();
	    Tile currentTile = this.gBoard.getTileWithLocation(currentLocation);
	    ArrayList<Unit> tileUnits = currentTile.getUnits();
	    RallyPoint newRallyPoint = new RallyPoint(currentLocation, this.gBoard, this.currentPlayer.getPlayerID());
	    Army newArmy = new Army(this.currentPlayer.getPlayerID(), newRallyPoint, tileUnits);
	    currentTile.removeAllUnits();
	    currentTile.addRallyPoint(newRallyPoint);
	    currentTile.addArmy(newArmy);
	    this.currentPlayer.addArmy(newArmy);
    }
}
