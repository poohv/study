package src;

import java.util.ArrayList;
import java.util.List;

public class MathFailer {
	
	 public int[] solution(int[] counts) {
	        int[] count = {0,0,0};
	        int[] one = {1,2,3,4,5};
	        int[] two = {2,1,2,3,2,4,2,5};
	        int[] three = {3,3,1,1,2,2,4,4,5,5};
	        int[] answer = {};
	        
	        for(int i=0; i<counts.length; i++){
	            if(one[i%one.length]==counts[i]){count[0]++; }
	            if(two[i%two.length]==counts[i]){count[1]++; }
	            if(three[i%three.length]==counts[i]){count[2]++;}            
	        }
	        	        
	        
	        ArrayList<Integer> list = new ArrayList<Integer>();
		        
	        int maxscore =  Math.max(Math.max(count[0], count[1]), count[2]);
	        
	        for (int i = 0; i < count.length; i++) {
				if(maxscore == count[i]) {
					list.add(i+1); 						
				}
			}
	        
	        answer = new int[list.size()];
	        
	        for (int i = 0; i < answer.length; i++) {
				 answer[i]=list.get(i);
			}
	        	        
	        return answer;
	    }

	public static void main(String[] args) {
	MathFailer a = new MathFailer();
	int[] params = {1, 3, 3, 4, 5};
	int[]two = a.solution(params);	
	for (int i = 0; i < two.length; i++) {
	System.out.print(two[i]);	
	}
	
	

	}

}
