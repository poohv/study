package src;

import java.util.HashMap;
import java.util.Map;

public class NoNumberAdd {
	
	
	   public static int solution(int[] numbers) {
	        int answer = 0;
	        int[] condition = {0,1,2,3,4,5,6,7,8,9};
	        
	        Map<Integer, Integer> index = new HashMap<>();
	        for (int i = 0; i < numbers.length; i++) {
				index.put(numbers[i], i);
			}
	        
	        for (int i = 0; i < condition.length; i++) {
				int num = condition[i];
	        	if(!index.containsKey(num)) {
	        		answer =answer+num;
	        	}
			}        
	        //result : 14
	        return answer;
	    }

	public static void main(String[] args) {
		
		int[] params = {1,2,3,4,6,7,8,0};
		
		System.out.println(solution(params));

	}

}
