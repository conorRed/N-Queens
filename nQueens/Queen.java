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
		 
		this.placeQueensBacktrack(n);
/*		for(int j =0; j< NUM_QUEEN; j++){
			this.makeSteepestMove(queens, j);
		}
		
		printQueens();*/
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
					printQueens();
				}
				else{
						backtrack(row+1);
					}
			
			}
		}
	}

	public void printQueens(){
		
		for(int j =0;j<NUM_QUEEN;j++){
					board[j][queens[j]] = 1;
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
	private void makeSteepestMove(int[] q,int row){
		int htobeat = getH(q,row);
		int[] copy = new int[NUM_QUEEN];
		HashMap<Integer,Integer> possibleMoves = new HashMap<Integer,Integer>();
		int [] bestMoves = new int[NUM_QUEEN];
		copy = q;
		for(int col = 0; col<NUM_QUEEN;col++){
			if(isIn(col,row,q))
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
	      int bcount = 0;
	    while(iterator1.hasNext()) {
	    	Entry<Integer,Integer> pentry = iterator1.next();
	    	Integer ph = pentry.getValue();
	    	Integer pc = pentry.getKey();
	    	  if(ph == htobeat){
	    		  bestMoves[bcount] = pc;
	    		  bcount++;
				}
	    }

		//int max = NUM_QUEEN-row;
	    if(row == NUM_QUEEN || bestMoves.length == 0){
	    	System.out.println("No Solution");
	    			return;
	    }
		int rand = (int) Math.random() * (bcount);//(row+(Math.random() * (max)));
		q[row] = bestMoves[rand];
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

			for(int i = 0; i<row;i++){
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