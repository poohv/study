package src;

import java.util.HashMap;
import java.util.Map;

//�ְ����
//��������

/*
1	6�� ��ȣ�� ��� ��ġ
2	5�� ��ȣ�� ��ġ
3	4�� ��ȣ�� ��ġ
4	3�� ��ȣ�� ��ġ
5	2�� ��ȣ�� ��ġ
6(��÷)	�� ��
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
