import java.io.*;

public class Structure {
	public char owner;
	public Tile location;
	
	/* Accessors */
	public char getOwner(){ return this.owner; }
	public Tile getLocation(){ return this.location; }
	
	/* Mutators */
	public void setOwner(char o){ this.owner = o; }
	public void setLocation(Tile t){ this.location = t; }
	
	public Structure(){
		this.owner = 'd';
	}
	
	public Structure(Tile t){
		this.owner = 'd';
		this.location = t;
	}
}
