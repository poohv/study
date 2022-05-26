package src;

import java.util.ArrayList;
import java.util.List;

public class DecimalMade {
	//nums에 있는 숫자들 중 서로 다른 3개를 골라 더했을 때 소수가 되는 경우
	
	   public static int solution(int[] nums) {
	        int answer = 0;
	        
	      for (int i = 0; i < nums.length; i++) {
	    	 
	    	  for (int b = i+1; b < nums.length; b++) {
	    		  
	    		  for (int k = b+1; k < nums.length; k++) {
	    			  int res = nums[i]+nums[b]+nums[k];
	    			  
	    			answer += isres(res)? 1:0;
	    			  
				}	   
	    	  }
	    	  
	  	    
	      }            
	        return answer;
	    }
	   
	   
	   private static  boolean isres(int num) {
		     
		   for (int i = 2; i <= Math.sqrt(num) ; i++) {
		
			  if(num % i == 0) {
				  return false;
			  }	 
			 
		   }
		   return true;	
	   }
	   
	
	   

	public static void main(String[] args) {
		
		
		  int[] nums = {1, 2, 7, 6, 4};
		System.out.println(solution(nums));
		

	}

}
