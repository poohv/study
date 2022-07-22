package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*����� ��
		array					commands					return
[1, 5, 2, 6, 3, 7, 4]	[[2, 5, 3], [4, 4, 1], [1, 7, 3]]	[5, 6, 3]

����� �� ����
[1, 5, 2, 6, 3, 7, 4]�� 2��°���� 5��°���� �ڸ� �� �����մϴ�. [2, 3, 5, 6]�� �� ��° ���ڴ� 5�Դϴ�.
[1, 5, 2, 6, 3, 7, 4]�� 4��°���� 4��°���� �ڸ� �� �����մϴ�. [6]�� ù ��° ���ڴ� 6�Դϴ�.
[1, 5, 2, 6, 3, 7, 4]�� 1��°���� 7��°���� �ڸ��ϴ�. [1, 2, 3, 4, 5, 6, 7]�� �� ��° ���ڴ� 3�Դϴ�.

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
