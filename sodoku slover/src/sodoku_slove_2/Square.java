package sodoku_slove_2;

public class Square {
	public int [][] theSquare;
	boolean full;
	public Square(int [][] arr){
		this.theSquare = arr;
		full = false;
	}
	
	/*******************************************
	 * findNum
	 * @param n the num we look for
	 * @return the Coordination of it's position
	 *******************************************/
	public Coordination findNum(int n) {
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				if(theSquare[i][j]==n)
					return new Coordination(i, j);
		return new Coordination(-1, -1);		
	}
	
	/********************************************
	 * haveNum
	 * @param n look if a num is in the Squere
	 * @return 
	 ********************************************/
	public boolean haveNum(int n) {
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				if(theSquare[i][j]==n)
					return true;
		return false;		
	}
	
	public Coordination whereNum(int n) {
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				if(theSquare[i][j]==n)
					return new Coordination(i, j);
		return new Coordination(-1, -1);		
	}
	
	/********************************************
	 * getEmptyCell
	 * @return the Coordination of an empty cell
	 ********************************************/
	public Coordination getEmptyCell() {
		for(int y=0;y<3;y++) {
				for(int x=0;x<3;x++) {
					if(theSquare[y][x]==0) {
						return new Coordination(y, x);
					}
				}
		}
		return null;
	}
	
	/********************************************
	 * howManyNum
	 * @return the count of not empty cell
	 ********************************************/
	public int howManyNum() {
		int count =0;
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				if(theSquare[i][j]!=0)
					count++;
		return count;
	}
	
	public boolean[][] whereCanPutAnNum(){
		boolean [][] toReturn = new boolean [3][3];
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(theSquare[i][j]!=0) {
					toReturn[i][j] = false;
				}
				else {
					toReturn[i][j] = true;
				}
			}
		}
		return toReturn;
	}
	
	public String toString() {
		String toRreturn = "";
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				toRreturn+= "\t"+this.theSquare[i][j];
			}
			toRreturn+= "\n";
		}
		return toRreturn;
	}
}
