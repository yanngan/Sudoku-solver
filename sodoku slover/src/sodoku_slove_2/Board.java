package sodoku_slove_2;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Board {
	Square [][] square;
	int count;
	public Board(int[][][][] square){
		count =0;
		this.square = new Square[3][3];
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				this.square[i][j] = new Square(square[i][j]);
				count+=this.square[i][j].howManyNum();
//				System.out.println("i="+i+" j="+j);
//				System.out.println(""+square[i][j]);
			}
		}
		//printBbord();
	}
	
	
	public Board copy(){
		int[][][][] temp_square = new int [3][3][3][3];
		for(int i = 0;i<3;i++) {
			for(int j = 0;j<3;j++) {
				for(int k = 0;k<3;k++) {
					for(int g = 0;g<3;g++) {
						temp_square[i][j][k][g] = square[i][j].theSquare[k][g];
					}
				}
			}
		}
		
		return new Board(temp_square);
	}
	
	public void solveBord(){//return: 1-successes  0- fail   2-need more num
		System.out.println("count 1 = "+count);
		int temp = count;
		int time = 0;
		while(count<81 && time<10) {
			for(int y=0;y<3;y++) {
				for(int x=0;x<3;x++) {
					slovesquare(y,x);
				}
			}
			time++;
		}
		System.out.println("count 2 = "+count);
		if(count == 81) {
			printBbord();
		}
	}
	
	private void slovesquare(int y,int x) {
		for(int i=1;i<10;i++) {//make only 9 try for each square
			for(int j=1;j<10;j++) {
				if(square[y][x].haveNum(j)) {
					continue;
				}
				else {
					boolean[][] tempBool = square[y][x].whereCanPutAnNum();
					//look for the num in the x and fill he bool array
//					System.out.println("********j="+j+"***********\n "+Arrays.deepToString(tempBool));
					for(int k=1;k<3;k++) {
						Coordination coorX = square[y][(x+k)%3].whereNum(j);
//						System.out.println("x\n"+square[y][(x+k)%3]);
//						System.out.println("1. y="+coorX.y+" x="+coorX.x);
						if(coorX.x!=-1) {
							for(int g=0;g<3;g++) {
								tempBool[coorX.y][g] = false;
							}
						}
					}
//					System.out.println("x bool"+Arrays.deepToString(tempBool));
					//look for the num in the y and fill the bool array
					for(int k=1;k<3;k++) {
						Coordination coorX = square[(y+k)%3][x].whereNum(j);
//						System.out.println("y\n"+square[(y+k)%3][x]);
//						System.out.println("2. y="+coorX.y+" x="+coorX.x);
						if(coorX.x!=-1) {
							for(int g=0;g<3;g++) {
								tempBool[g][coorX.x] = false;
							}
						}
					}
//					System.out.println(Arrays.deepToString(tempBool));
					//check if I have only one place to put the num, and its in the correct position
					int howManyPosition = 0;
					for(int k=0;k<3;k++) {
						for(int n=0;n<3;n++) {
							if(tempBool[k][n]) {
								howManyPosition++;
							}
						}
					}
					
					if(howManyPosition==1) {
						for(int k=0;k<3;k++) {
							for(int n=0;n<3;n++) {
								if(tempBool[k][n]) {
									square[y][x].theSquare[k][n]=j;
									count++;	
//									System.out.println("\nx="+x+" y="+y+"\n"+square[x][y]);
								}
							}
						}
					}
					/*else if(howManyPosition==0) {
						return false;
						
					}*/
				}
			}
				
		}
	}
	
	/*public boolean sloveRecursive(int k) {
		System.out.println("k = "+k);
		if(solveBord()) {
			return true;
		}
		else if( k>81){
			return false;
		}
		else {
			Coordination temp = new Coordination(-1,-1);
			int i = 0;
			int j = 0;
			for(;i<3;i++) {
				for(;j<3;j++) {
					temp = square[i][j].getEmptyCell();
					if(temp.x!=-1) {
						break;
					}
				}
				if(temp.x!=-1) {
					break;
				}
			}
			if(temp.x!=-1) {
				for(int g = 1;g<10;g++) {
					if(canIPutTheNumHere(i,j,temp,g)) {
						square[i][j].theSquare[temp.x][temp.y] = g;
						System.out.println("put i = "+i+" j = "+j+" g = "+g);
						printBbord();
						if(sloveRecursive(k+1)) {
							return true;
						}
						else {
							square[i][j].theSquare[temp.x][temp.y] = 0;
						}
					}
				}
				return false;
			}
		}
		return true;
	}*/
	public void printBbord() {
		for(int i =0;i<3;i++) {
			System.out.println("row-"+i);
			for(int j=0;j<3;j++) {
				System.out.println("\n\n"+square[i][j]);
			}
		}
	}
	
	
	public boolean canIPutTheNumHere(Coordination coor_board,Coordination coor_square, int num) {
		//System.out.println("coor_board = "+coor_board+"coor_square = "+coor_square);
		if(square[coor_board.x][coor_board.y].haveNum(num)) {
			return false;
		}
		else {
			//check the y Coordination all the board
			/*Coordination temp  =null;
			temp = square[coor_board.y][(coor_board.x+1)%3].whereNum(num);
			System.out.println("board = ("+(coor_board.x+1)%3+","+coor_board.y+") temp = "+temp);
			*/
			if((square[coor_board.y][(coor_board.x+1)%3].whereNum(num)).y == coor_square.y) {
				return false;
			}
			if((square[coor_board.y][(coor_board.x+2)%3].whereNum(num)).y == coor_square.y) {
				return false;
			}
			if((square[(coor_board.y+1)%3][coor_board.x].whereNum(num)).x == coor_square.x) {
				return false;
			}
			if((square[(coor_board.y+2)%3][coor_board.x].whereNum(num)).x == coor_square.x) {
				return false;
			}
		}
		return true;
	}
}




















