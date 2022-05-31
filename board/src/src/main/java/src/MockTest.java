package src;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//1번 : 1,2,3,4,5
//2번 :  2, 1, 2, 3, 2, 4, 2, 5
//3번 : 3, 3, 1, 1, 2, 2, 4, 4, 5, 5
public class MockTest {
	
	 public static int[] solution(int[] answers) {
		 	int len = answers.length;
		 	int [] one = {1,2,3,4,5};
		    int [] two = { 2, 1, 2, 3, 2, 4, 2, 5};	    
		    int [] three = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
		  	    	        
	        int count1 =0;
	        int count2 =0;
	        int count3 =0;
	        	        
	        Map<Integer, Integer> mp = new HashMap<Integer, Integer>();
	     
	        //1번
	       
	        for (int i = 0; i < len; i++) {	        	            	
	        	if(answers[i]==one[i%one.length]) {
	        		++count1;
	        	}             	
			}
	        mp.put(count1, 1);
	        	        
	        //2번
	        				
	        for (int i = 0; i < len; i++) {		
				if(answers[i]==two[i%two.length]) {
	        		++count2;
	        	}		
			}	        
	        mp.put(count2, 2);
	        //3번    			
	        for (int i = 0; i < len; i++) {	     
	        	if(answers[i]==three[i%three.length]) {
	        		++count3;
	        	}
			}
	        mp.put(count3, 3);
	        
	        int[] answer = {};
	          	       
	        int maxnum = Math.max(count3, Math.max(count1, count2));         
	        answer= new int[] {mp.get(Math.max(count3, Math.max(count1, count2)))};
	       
	        if(maxnum==count2) {
	        	if(maxnum==count3) {
	        		 answer= new int[] {2,3};
	        	}
	        	if(maxnum==count1) {
	        		 answer= new int[] {1,2};
	        	}
	        }
	        if(maxnum==count1) {
	        	if(maxnum==count3) {
	        		answer= new int[] {1,3};
	        	}
	        }
	        			
			 if (count1==count2) { 
				 if (count2==count3) { 
					 answer= new int[] {1,2,3}; 
					 
				 } }
			 
	              
	        return answer;
	 } 

	public static void main(String[] args) {
		
		int[] one = {1,2,3,4,5};
		int[] out = solution(one);
		

		for (int i = 0; i < out.length; i++) {
			System.out.println(out[i]);
		}
		
		
		
		
		

	}

}
