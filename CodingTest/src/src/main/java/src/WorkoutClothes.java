package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class WorkoutClothes {
	
	/* 
	 * {1,2,4,6};{1,2}
	{1,2,4,6};	{2,3}
	 *  */
	
	public static int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;   
        int num = 0;
        int count = 0;
      
        for (int i = 0; i < lost.length; i++) {
			for (int j = 0; j < reserve.length; j++) {
				if(lost[i]==reserve[j]) {
					lost[i]=-1;
					reserve[j]=-1;
					break;
				}								
			}
		}
        
        Arrays.sort(lost);
        Arrays.sort(reserve);
          
        for (int i = 0; i < lost.length; i++) {
        	if(lost[i]!=-1) {
			int fr = lost[i]-1; 
			int br = lost[i]+1; 
				
			++count;
			
			for (int j = 0; j < reserve.length; j++) {
				if(reserve[j]!=-1) {			
					if(fr==reserve[j]) {
						reserve[j]=0;	
						++num;
					break;
					}
					else if(br == reserve[j]) {
						reserve[j]=0;
						++num;
						break;
						}						
				}
			}		
		}
        	   
        }
        answer = n-count+num;
             
        return answer;
    }

	public static void main(String[] args) {
		int n = 6;
		int[] lost = {1,2,4,6};
		int[] res = {1,2,4,6}; //{3}
		//2
		
		System.out.println(solution(n,lost,res));
	}

}
