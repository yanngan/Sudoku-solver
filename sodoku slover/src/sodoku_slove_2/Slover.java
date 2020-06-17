package sodoku_slove_2;

public class Slover {
	
	public static Board slove(Board board) {
		return run(board,board.count);
		
	}
	
	public static Board run (Board board,int level) {
		System.out.println("level = "+level);
		if (level == 81) {
			return board;
		}
		Coordination coor_board = null;
		Coordination coor_square = null;
		for(int y = 0;y<3;y++) {
			boolean flag = false;
			for(int x = 0;x<3;x++) {
				coor_square = board.square[y][x].getEmptyCell();
				if(coor_square!=null) {
					coor_board = new Coordination(y,x);
					flag = true;
					break;
				}
			}
			if(flag) {
				break;
			}
		}
		if(coor_board == null) {
			return null;
		}
		
		Board []  arr = new Board[9];
		for(int i = 0; i<9;i++) {
			arr[i] = null;
		}
		for(int num = 1;num<10;num++){
			if(board.canIPutTheNumHere(coor_board, coor_square, num)) {
				//System.out.println("coor_board = "+coor_board+"coor_square = "+coor_square +" num = "+num);
				arr[num-1] = board.copy();
				arr[num-1].square[coor_board.y][coor_board.x].theSquare[coor_square.y][coor_square.x] = num;
				arr[num-1].count++;
				arr[num-1].solveBord();
				arr[num-1] = run(arr[num-1],arr[num-1].count);
			}
		}
		
		for(int i=0;i<9;i++) {
			if(arr[i]!=null) {
				return arr[i];
			}
		}
		return null;
	}

}






















