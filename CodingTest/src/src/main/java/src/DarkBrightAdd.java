package src;

import java.util.HashMap;
import java.util.Map;

public class DarkBrightAdd {
	
	   public static int solution(int[] absolutes, boolean[] signs) {
	        int answer = 0;
	        int sum = 0;
	        
	        for (int i = 0; i < absolutes.length; i++) {
	        	sum+= absolutes[i] * (signs[i] ?1 : -1);
			}
	   	        	        
			/*
			 * for (int i = 0; i < signs.length; i++) { 
			 * answer = absolutes[i]; 
			 * if(signs[i]) { sum+=answer; } 
			 * else { sum+= -answer; } }
			 */	        
	        return sum;
	    }

	public static void main(String[] args) {
		int[] ab = {4,7,12};
		
		boolean[] signs = {true,false,true};
		System.out.println(solution(ab,signs));		
		

	}

}
