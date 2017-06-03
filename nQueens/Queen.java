package nQueens;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Queen{
	private static int NUM_QUEEN;
	//private static  int BOARD_SIZE = 8;
	private int[][] board;
	private int[] queens;
	public Queen(int n){
		NUM_QUEEN = n;
		queens = new int[NUM_QUEEN];
		for(int j =0;j<NUM_QUEEN;j++){
			queens[j] = 0;
		}
		board = new int[NUM_QUEEN][NUM_QUEEN];
		this.makeSteepestMove(queens);
		for(int j =0;j<NUM_QUEEN;j++){
			queens[j] = ((int)Math.random()*(NUM_QUEEN-1));
		}
		this.makeSteepestMove(queens);
		//this.placeQueensBacktrack(n);
		
	}

	public Queen(){
		this(8); // default value
	}
	private boolean canPlace(int r,int c){
		for(int i = 0; i<r; i++){
			if (c == queens[i] || (i - r) == (c-queens[i]) ||(i-r) == (queens[i] - c)) 
            {
                return false;
            }
		
		}                  
		return true;
	}
	public void backtrack(int row){

	for(int i = 0; i<NUM_QUEEN; i++){
			if(canPlace(row,i)){
				queens[row] =  i;
				if(row == NUM_QUEEN-1){
					printQueens(queens);
				}
				else{
						backtrack(row+1);
					}
			
			}
		}
	}

	public void printQueens(int[] q){
		
		for(int j =0;j<NUM_QUEEN;j++){
					board[j][q[j]] = 1;
		}	
		System.out.println();
		for(int j =0;j<NUM_QUEEN;j++){
		 for(int i =0;i<NUM_QUEEN;i++){
			System.out.print(board[j][i]+ " ");
		 }	
		 System.out.println();
		}
		 System.out.println();
		 	
		 // clear board
	 for(int j =0;j<NUM_QUEEN;j++){
			 for(int i =0;i<NUM_QUEEN;i++){
				board[j][i] = 0;
			 }	
		 }
	
	}
	private void makeSteepestMove(int[] q){
		int[] copy = q;
		HashMap<Integer,Integer> possibleMoves = new HashMap<Integer,Integer>();
		int [] bestMoves = new int[NUM_QUEEN];
		for(int row =0;row<NUM_QUEEN;row++){
			int htobeat = getH(q,row);
			possibleMoves.put(q[row], getH(copy,row));
			for(int col =0;col<NUM_QUEEN;col++){
				if(q[row] == col)
					continue;
				copy[row] = col;
				possibleMoves.put(col, getH(copy,row));
			}
			Iterator<Entry<Integer, Integer>> iterator1 =  possibleMoves.entrySet().iterator();
			Iterator<Entry<Integer, Integer>> iterator =  possibleMoves.entrySet().iterator();
			while(iterator.hasNext()) {
		    	  Integer pentry = (Integer)iterator.next().getValue();
		    	  if(pentry < htobeat){
						htobeat = pentry;
					}
		      }
			int bcount =0;
			while(iterator1.hasNext()) {
		    	Entry<Integer,Integer> pentry = iterator1.next();
		    	Integer ph = pentry.getValue();
		    	Integer pc = pentry.getKey();
		    	  if(ph == htobeat){
		    		  bestMoves[bcount] = pc;
		    		  bcount++;
					}
		    }
			
				
			int rand = (int) Math.random() * (bcount);
			q[row] = bestMoves[rand];
			if(row == NUM_QUEEN-1){
				printQueens(q);
				return;
			}
				
		
		}
		System.out.print("No Solution");
	
	}
	
	private boolean isIn(int col,int row, int[] qu){
		for(int i = 0; i< row;i++){
			if(col == qu[i]){
				return true;
			}
		}
		return false;
	}
	private int getH(int[] q,int row){
		int h =0,offset=0;

			for(int i = 0; i<NUM_QUEEN;i++){
				if(i == row)
					continue;
				if(q[row] == q[i]){ 
					h+=1;
				}
			offset = i-row;
			if(q[row]-q[i]==  offset || q[i]-q[row]== offset){
				h+=1;
			}
		}
		return h;
	}
	private void placeQueensBacktrack(int n){
			backtrack(0);
	}
	// getters and setters
	public static void main(String[] args){
		Queen q = new Queen(4);
	}
	
	}