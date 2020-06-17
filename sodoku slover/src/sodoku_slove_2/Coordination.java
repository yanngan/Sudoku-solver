package sodoku_slove_2;

public class Coordination {
	public int y;
	public int x;
	public Coordination(int y,int x) {
		this.y = y;
		this.x = x;
	}
	public String toString() {
		return "("+x+","+y+")";
	}
}
