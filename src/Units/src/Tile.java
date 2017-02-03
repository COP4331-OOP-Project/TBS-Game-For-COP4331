public class Tile
{
	int x;
	int y;
	
	/* Accessors */
	public int getX(){ return this.x; }
	public int getY(){ return this.y; }
	
	/* Mutators */
	public void setX(int newX){ this.x = newX; }
	public void setY(int newY){ this.y = newY; }
	
	public Tile(){
		this.x = 0;
		this.y = 0;
	}
	
	public Tile(int x, int y){
		this.x = x;
		this.y = y;
	}
}
