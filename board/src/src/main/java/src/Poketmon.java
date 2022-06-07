package src;

import java.util.HashSet;
import java.util.Set;

public class Poketmon {
	//[3,3,3,2,2,4] - 3	
	//[3,3,3,2,2,2] - 2	
	//[3,1,2,3] - 2
	
	  public static int solution(int[] nums) {
	        int answer = 0;
	        int len = nums.length/2;
	        Set<Integer> aa =  new HashSet<Integer>();
	        
	        for (int i = 0; i < nums.length; i++) {
	        	aa.add(nums[i]);
	        	//[3,2,4] - 3 = 3
	        	//[1,2,3] - 2 = 2
	        	//[3,2] - 3 = 2
	        	
			}
	        
	        if(aa.size()==len) {answer = len;}
    		else if(aa.size() > len) {answer = aa.size()-1;}
    		else {answer = aa.size();}
	        
	        return answer;
	    }

	public static void main(String[] args) {
		
			int[] nums = {3,1,2,3,4,5};
			System.out.println(solution(nums));
			
			
	}

}
