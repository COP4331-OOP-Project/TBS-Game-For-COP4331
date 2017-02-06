package game.entities;

import game.commands.AttackCommand;
import game.commands.Command;
import game.commands.DecommissionCommand;
import game.commands.DefendCommand;
import game.entities.units.Unit;
import game.gameboard.GameBoard;
import game.gameboard.Location;

import java.util.*;

public class Army{

    private int armyID;                                 // Army unique id
    private int ownerID;                                // Player id
    private int rotation = 0;
    private Location location;                          // Army location
    private RallyPoint rp;                              // Army Rally Point
    private PowerState powerState;                      // Army power state
    private float resourceCost;                         // Total army resource cost

    private ArrayList<Unit> battleGroup;                // Active battlegroup units
    private ArrayList<Unit> reinforcements;             // Reinforcing units
    private ArrayList<Unit> allUnits;                   // All Units in the army
    private Queue<Command> queue;                // Army commands

    // Constructor
    public Army(Location loc, int playerId, RallyPoint rp, ArrayList<Unit> units) {

        this.location = loc;                            // Set army location
        this.ownerID = playerId;                        // Set player id
        this.rp = rp;                                   // Set rally point
        this.queue = new LinkedList<>();
        this.battleGroup = new ArrayList<Unit>();       // Initialize battleGroup
        this.reinforcements = new ArrayList<Unit>();    // Initialize reinforcements
        this.allUnits = new ArrayList<Unit>();          // Initialize allUnits
        setPowerState(PowerState.POWERED_UP);

        for(int i=0;i<units.size();i++){
            //If unit is at rallypoint, they are in battlegroup, if not they are reinforcements. All will still be put into allUnits arraylist
            if(units.get(i).getLocation().getX()==this.rp.getLocation().getX() && units.get(i).getLocation().getY()==this.rp.getLocation().getY()){
                battleGroup.add(units.get(i));
            }
            else{
                reinforcements.add(units.get(i));
            }
            allUnits.add(units.get(i));
        }

    }

    // TODO: Setup function to filter all received commands to all units

    public void passCommandToUnit(Command command, Unit unit){
        command.setDuration(0);
        for(int i = 0; i<allUnits.size(); i++){
            if(allUnits.get(i).getUnitID()==unit.getUnitID()) {
                allUnits.get(i).cancelQueuedCommands();
                allUnits.get(i).addCommandToQueue(command);
            }
        }

    }

    public void disbandArmy(){
        setPowerState(PowerState.STANDBY);
    }

    // Add one new unit to the reinforcements array
    public void addSingleReinforcement(Unit reinforcement) {
        this.reinforcements.add(reinforcement);
        this.allUnits.add(reinforcement);
    }

    // Add a group of new units to reinforcements array
    public void addListOfReinforcements(ArrayList<Unit> reinforcements) {
        this.reinforcements.addAll(reinforcements);
        this.allUnits.addAll(reinforcements);
    }

    // Add one new unit to the reinforcements array
    public void addSingleBattleGroupMember(Unit battleGroupMember) {
        this.battleGroup.add(battleGroupMember);
        this.allUnits.add(battleGroupMember);
    }

    // Add a group of new units to reinforcements array
    public void addListToBattleGroup(ArrayList<Unit> battleGroupMembers) {
        this.battleGroup.addAll(battleGroupMembers);
        this.allUnits.addAll(battleGroupMembers);
    }

    // Update battleGroup and reinforcements to make sure all units in correct spot
    public void updateArmy(){
        for(Iterator<Unit> iter = battleGroup.iterator(); iter.hasNext();){
            //If battlegroup unit not on rallypoint move to reinforcements
            Unit checkUnit = iter.next();
            if(!checkUnit.getLocation().equals(rp.getLocation())){
                reinforcements.add(checkUnit);
                iter.remove();
            }
        }

        for(Iterator<Unit> iter = reinforcements.iterator(); iter.hasNext();){
            //If reinforcement unit already on rallypoint move to battlegroup
            Unit checkUnit = iter.next();
            if(checkUnit.getLocation().equals(rp.getLocation())){
                battleGroup.add(checkUnit);
                iter.remove();
            }
        }

        //Recalculate power states for units and total resource cost
        setPowerState(this.powerState);
    }

    //Set Power states to correct units
    public void setPowerState(PowerState state) {
        this.powerState = state;            // Set state
        if(this.powerState==PowerState.POWERED_UP || this.powerState==PowerState.COMBAT){
            //Give Battlegroup combat state
            for(int i = 0; i<battleGroup.size(); i++){
                battleGroup.get(i).setPowerState(state);
            }
            //Give reinforcements normal moving state
            for(int i = 0; i<reinforcements.size(); i++){
                reinforcements.get(i).setPowerState(PowerState.POWERED_UP);
            }
        }
        //If Power down state all units go to powered down regardless of battlegroup or reinforcements
        else{
            for(int i = 0; i<allUnits.size();i++){
                allUnits.get(i).setPowerState(state);
            }
        }
    }

    //Figure out total resource cost for army
    public void totalResourceCost(){
        float total=0;
        for(int i = 0; i<allUnits.size();i++){
            total=total+allUnits.get(i).getResourceCost();
        }
        this.resourceCost=total;
    }

    // Accessors

    // Set new rally point
    public void setRallyPoint(RallyPoint rp) {
        this.rp = rp;
        this.location = this.rp.getLocation();
    }

    //Command Handling
    public void battlegroupAttack(GameBoard gameBoard, int direction) {

        // Filter army command down to each unit in battlegroup
        setPowerState(PowerState.COMBAT);
        for (Unit u : battleGroup) {
            u.cancelQueuedCommands();
            AttackCommand<Unit> bgAttackCmd = new AttackCommand<Unit>(gameBoard, u, direction, 0);
            u.addCommandToQueue(bgAttackCmd);
            u.doTurn();
        }

    }

    public void battlegroupDefend(GameBoard gameBoard, int direction){
        //Filter army commands down to each unit in battlegroup
        setPowerState(PowerState.COMBAT);
        for(Unit u : battleGroup){
            u.cancelQueuedCommands();
            DefendCommand<Unit> bgDefendCmd = new DefendCommand<Unit>(gameBoard, u, direction, 0);
            u.addCommandToQueue(bgDefendCmd);
            u.doTurn();
        }
    }

    public void groupDecomission(GameBoard gameBoard){
        for(Unit u : reinforcements){
            u.cancelQueuedCommands();
            DecommissionCommand<Unit> groupDecom = new DecommissionCommand<Unit>(gameBoard,u);
            u.addCommandToQueue(groupDecom);
            u.doTurn();
        }
    }

    public void powerUp(){
        setPowerState(PowerState.POWERED_UP);
    }

    public void powerDown(){
        setPowerState(PowerState.POWERED_DOWN);
    }


    public void doTurn() {

        updateArmy();
        //Make sure has commands and powered state is not powered down
        if (!queue.isEmpty()&&powerState.getUpkeep()!=0.25f) {
            if (peekCommand().getDuration() == 0) {                               // Test if next cmd can fire
                nextCommand().exec();                                      // Send commands to units
            }
            else peekCommand().iterateDuration();
        }

        getRp().PathToRallyPoint();

        for(int i = 0; i < reinforcements.size(); i++){
            reinforcements.get(i).doTurn();
        }

        updateArmy();


    }

    public Command nextCommand() { return queue.poll(); }                     // Pop off next command
    public Command peekCommand() { return queue.peek(); }                     // Look next command
    public boolean isQueueEmpty() { return queue.isEmpty(); }                 // check if empty
    public void addCommandToQueue(Command command){ queue.add(command); }     // Add next cmd to queue
    public void cancelQueuedCommands() { queue.clear(); }                     // Clear queue



    // Getters
    public int getOwnerID() { return ownerID; }
    public Location getLocation() { return location; }
    public RallyPoint getRp() { return rp; }
    public ArrayList<Unit> getBattleGroup() { return battleGroup; }
    public ArrayList<Unit> getReinforcements() { return reinforcements; }
    public ArrayList<Unit> getAllUnits(){return allUnits;}
    public Queue<Command> getCommands(){return queue;}
    public float getResourceCost(){return resourceCost;}

    public void setArmyID(int armyID)
    {
        this.armyID = armyID;
    }

    public int getArmyID()
    {
        return this.armyID;
    }

    public int getRotation() {
    	return rotation;
    }
    
    public void setRotation(int rotation) {
    	this.rotation = rotation;
    }

    // Print all units in an army
    public void printArmy(){
        Unit temp;
        Iterator<? super Unit> i = this.battleGroup.iterator();
        System.out.println("Owner: " + this.ownerID);
        while(i.hasNext()){
            temp = (Unit)i.next();
            System.out.println("    Soldier: " + temp.getUnitType());
        }
    }

}
