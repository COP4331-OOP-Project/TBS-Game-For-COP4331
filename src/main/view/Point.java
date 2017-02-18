package view;

public class Point {
	public int x;
	public int y;
	
	/**
	 * This is a simple struct that holds
	 * public unrestricted X and Y values to 
	 * prevent use overuse of seperate ints
	 * for x and y values.
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point() {
		this.x = 0;
		this.y = 0;
	}
	
	@Override
	public String toString() {
		return "X: " + x + " Y: " + y;
	}
}
