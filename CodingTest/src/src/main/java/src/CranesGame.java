package src;

import java.util.Stack;

public class CranesGame {
	
	  public static int solution(int[][] board, int[] moves) {	 
	        Stack<Integer> bottle = new Stack<>();
	        int count = 0;
	        
	        for (int i = 0; i < moves.length; i++) {
	        	int move = moves[i];
	        	int topnum = 0;
	        	int pick =0;
	        	
	        	//�ٱ��� ��� �� ��������
	        	if(bottle.size()>0) {
	        	topnum =bottle.peek();}	  
	        		               		        	
	        	for (int j = 0; j < board.length; j++) {
	        		
	        		pick = board[j][move-1];
	        		
		        	if(pick != 0) {
		        		//������ ������, Ƚ�� ����
		        		if(pick == topnum) {		
		        			bottle.pop();
		        			count = count+2;	
		        			board[j][move-1] = 0;	 
		        			break;
		        		}
		        		
		        		//�ٱ��� ���
		        		bottle.push(pick);
		        		//�ش� �迭 0 ����
		        		board[j][move-1] = 0;	        		        	
		        		break;
		        	}	        	
				}                	     			
			}	        
	        return count;
	    }

	public static void main(String[] args) {
	
		int[][] board = {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
		int[] moves = {1,5,3,5,1,2,1,4};
		
	System.out.println(solution(board,moves));

	}

}
