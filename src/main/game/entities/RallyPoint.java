package game.entities;

import java.util.*;

import game.commands.Command;
import game.commands.MakeCommand;
import game.commands.MoveCommand;
import game.entities.units.Unit;
import game.gameboard.GameBoard;
import game.gameboard.Location;

public class RallyPoint {
	private UUID rallyID;
	private Location location;
	private Army army;
	private GameBoard gameBoard;
	
	public RallyPoint(Location location, GameBoard gameBoard) {
		this.rallyID = UUID.randomUUID();
		this.location = location;
		this.gameBoard = gameBoard;
	}

	public void PathToRallyPoint(){
		Location reference;
		//For all units
		for(int i = 0; i<army.getAllUnits().size(); i++){
			//Not at rallypoint. Give next path command to rally point
			if(!army.getAllUnits().get(i).getLocation().equals(location)){
				Command nextMove = pathAlgorithm(army.getAllUnits().get(i).getLocation(),location, army.getAllUnits().get(i));
				army.passCommandToUnit(nextMove, army.getAllUnits().get(i));
			}
		}
	}

	public Command pathAlgorithm(Location from, Location to, Unit unit){
		//Dereference parameter input
		Location current=new Location(from.xIndex,from.yIndex);
		//Visited locations
		Set<Location> closed = new HashSet<Location>();
		closed.add(current);
		//Visited Directions
		Map<Location,Integer> directionMap = new HashMap<Location,Integer>();
		directionMap.put(current,-1);
		//Breath first search queue
		Queue<Location> q = new LinkedList<Location>();
		q.add(current);
		//While location not yet found and still have locations in queue
		while(!current.equals(to)&&!q.isEmpty()){
			current=q.poll();
			/**Check 8 spaces around to see if not visited yet. If not check if it is a valid move, if valid move
			*   update the direction map
			**/
			if(current.xIndex>=0 && current.yIndex-1>=0){
				Location up = current.directionLocation(0);
				if(closed.contains(up)){
					closed.add(up);
					if(checkMovable(up)){
						q.add(up);
						directionMap.put(up,0);
					}
				}
			}
			if(current.xIndex+1>=0 && current.yIndex-1>=0){
				Location upright = current.directionLocation(45);
				if(closed.contains(upright)){
					closed.add(upright);
					if(checkMovable(upright)){
						q.add(upright);
						directionMap.put(upright,45);
					}
				}
			}
			if(current.xIndex+1>=0 && current.yIndex>=0){
				Location right = current.directionLocation(90);
				if(closed.contains(right)){
					closed.add(right);
					if(checkMovable(right)){
						q.add(right);
						directionMap.put(right,90);
					}
				}
			}
			if(current.xIndex+1>=0 && current.yIndex+1>=0){
				Location downright = current.directionLocation(135);
				if(closed.contains(downright)){
					closed.add(downright);
					if(checkMovable(downright)){
						q.add(downright);
						directionMap.put(downright,135);
					}
				}
			}
			if(current.xIndex>=0 && current.yIndex+1>=0){
				Location down = current.directionLocation(180);
				if(closed.contains(down)){
					closed.add(down);
					if(checkMovable(down)){
						q.add(down);
						directionMap.put(down,180);
					}
				}
			}
			if(current.xIndex-1>=0 && current.yIndex+1>=0){
				Location downleft = current.directionLocation(225);
				if(closed.contains(downleft)){
					closed.add(downleft);
					if(checkMovable(downleft)){
						q.add(downleft);
						directionMap.put(downleft,225);
					}
				}
			}
			if(current.xIndex-1>=0 && current.yIndex>=0){
				Location left = current.directionLocation(270);
				if(closed.contains(left)){
					closed.add(left);
					if(checkMovable(left)){
						q.add(left);
						directionMap.put(left,270);
					}
				}
			}
			if(current.xIndex-1>=0 && current.yIndex-1>=0){
				Location upleft = current.directionLocation(315);
				if(closed.contains(upleft)){
					closed.add(upleft);
					if(checkMovable(upleft)){
						q.add(upleft);
						directionMap.put(upleft,315);
					}
				}
			}
		}

		//Check if location was found
		if(directionMap.containsKey(to)){
			//Found
			//Back track to the first
			Location prev=null;
			current=to;
			//TODO possibly create all the commands for the path and give to unit?
			while(!current.equals(from)){
				prev=current;
				current=current.directionLocation((directionMap.get(current)+180)%360);
			}

			//Create Command for next move and return it
			return new MoveCommand(gameBoard,unit,directionMap.get(prev),0);
		}
		else{
			//Not Found
			return null;
		}
	}
	//Check if unit can move on tile
	public boolean checkMovable(Location location){
		/**Return true if tile is not a river tile and
		 * 				There are no enemies on it
		 */
		if(gameBoard.gameMap[location.xIndex][location.yIndex].getTileType()!=2 ){
			if(!gameBoard.gameMap[location.xIndex][location.yIndex].hasEnemyUnit(army.getOwnerID())){
				return true;
			}
		}
		return false;
	}
	
	public void setArmy(Army army) {
		this.army = army;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public Location getLocation() {
		return location;
	}
}
