package src;

import java.util.HashMap;
import java.util.Map;

//최고순위
//최저순위

/*
1	6개 번호가 모두 일치
2	5개 번호가 일치
3	4개 번호가 일치
4	3개 번호가 일치
5	2개 번호가 일치
6(낙첨)	그 외
*/

/*		lottos					win_nums			result
[44, 1, 0, 0, 31, 25]	[31, 10, 45, 1, 6, 19]		[3, 5]
[0, 0, 0, 0, 0, 0]		[38, 19, 20, 40, 15, 25]	[1, 6]
[45, 4, 35, 20, 3, 9]	[20, 9, 3, 45, 4, 35]		[1, 1]
*/
public class RottoLank {

	 public static int[] solution(int[] lottos, int[] win_nums) {
	        int[] answer = {};
	        int win_count=0;
	        int lower_count = 0;
	        
	        Map<Integer, Integer> win_map =  new HashMap<>();
	        
	        for (int i = 0; i < win_nums.length; i++) {
	        		win_map.put(i, win_nums[i]);
			}
	        
	        for (int i = 0; i < lottos.length; i++) {
	        	if(win_map.containsValue(lottos[i])) {
	        		++win_count;
	        	} else if(lottos[i]==0) {
	        		++win_count;
	        		++lower_count;
	        	}        	
			}
	        
	         lower_count = 7-(win_count-lower_count);
	         win_count = 7-win_count;
	         
	         if(win_count >= 7) 
	        	 win_count = 6;
	         
	         if(lower_count >= 7) 
	        	 lower_count = 6;
	                    
	         answer = new int[] {win_count,lower_count};
	         
	        return answer;
	    }
	 
	 
	public static void main(String[] args) {
		int[] lot = {0, 0, 0, 0, 0, 0};
		int[] win = {31, 10, 45, 1, 6, 19};
		int[] out =	solution(lot, win);
		
		System.out.println(out[0]+","+out[1]);
	}

}
