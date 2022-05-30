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
		 	int [] one = new int[len];
		    int [] two = { 2, 1, 2, 3, 2, 4, 2, 5};	    
		    int [] three = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
		  	    	        
	        int count1 =0;
	        int count2 =0;
	        int count3 =0;
	        
	    
	        
	        Map<Integer, Integer> mp = new HashMap<Integer, Integer>();
	     
	        //1번
	        int g= 1;
	        for (int i = 0; i < len; i++) {	        	
	        	one[i] = g;       	
	        	if(answers[i]==g) {
	        		++count1;
	        	}
	        	++g;
	        	if(g==6){g=1;}       	
			}
	        mp.put(count1, 1);
	        	        
	        //2번
	        	g=0;			
	        for (int i = 0; i < len; i++) {
		
				if(answers[i]==two[g]) {
	        		++count2;
	        	}
				++g;
				if(g == 8)
				g=0;
			}
	        
	        mp.put(count2, 2);
	        //3번
	    	g=0;			
	        for (int i = 0; i < len; i++) {	     
	        	if(answers[i]==three[g]) {
	        		++count3;
	        	}
	        	++g;
				if(g == 10)
				g=0;
			}
	        mp.put(count3, 3);
	        
	        int[] answer = {};
	        
	        if(count1 > count2)
	        answer= new int[] {1};
	        else if (count2 > count1)
	        	answer= new int[] {2};
	        else if (count3 > count2)
	        	answer= new int[] {3};
	        else if (count1 > count3)
	        	answer= new int[] {1};
	        else if (count2 > count3)
	        	answer= new int[] {2};
	        	        
	       
	        
	        answer= new int[] {mp.get(Math.max(count1, count2))};
	       
	        
	         if (count1==count2) {
	        	if (count2==count3) {
	        		answer= new int[] {1,2,3};	
	        	}	        		
	        }
	        
	      
	 System.out.println();       
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
