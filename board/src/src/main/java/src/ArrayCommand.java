package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*입출력 예
		array					commands					return
[1, 5, 2, 6, 3, 7, 4]	[[2, 5, 3], [4, 4, 1], [1, 7, 3]]	[5, 6, 3]

입출력 예 설명
[1, 5, 2, 6, 3, 7, 4]를 2번째부터 5번째까지 자른 후 정렬합니다. [2, 3, 5, 6]의 세 번째 숫자는 5입니다.
[1, 5, 2, 6, 3, 7, 4]를 4번째부터 4번째까지 자른 후 정렬합니다. [6]의 첫 번째 숫자는 6입니다.
[1, 5, 2, 6, 3, 7, 4]를 1번째부터 7번째까지 자릅니다. [1, 2, 3, 4, 5, 6, 7]의 세 번째 숫자는 3입니다.

*/

public class ArrayCommand {
	
	   public static int[] solution(int[] array, int[][] commands) {
	        int[] answer = new int[commands.length];
	        	  	        
	        for (int i = 0; i < commands.length; i++) {
	        	List<Integer> list = new ArrayList<>();
	        	
				for (int j = 0; j < commands.length; j++) {
					
					int	fr = commands[i][j];
					int br = commands[i][j+1];
					int num = commands[i][j+2];
										
					for (int k = fr-1; k <= br-1; k++) {					
						list.add(array[k]);
					}
					Collections.sort(list);
					answer[i]=list.get(num-1);
					break;
				}				
			}	       	          
	        return answer;
	    }
	   
	   
	public static void main(String[] args) {
		int[] array = {1, 5, 2, 6, 3, 7, 4};
		int[][] comm = {{2,5,3},{4,4,1},{1,7,3}};
		
		System.out.println(solution(array,comm));
		
	}

}
