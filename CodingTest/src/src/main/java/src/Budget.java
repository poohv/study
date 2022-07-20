package src;

import java.util.Arrays;

/*
d	budget	result
[1,3,2,5,4]	9	3
[2,2,3,3]	10	4

*/
public class Budget {
	
	
    public int solution(int[] d, int budget) {
        int answer = 0;
        int sum = 0;
        int count =0;
        //Á¤·Ä
        Arrays.sort(d);
        
        
        int[] leng = new int [d.length];
        
        for (int i = 0; i < d.length; i++) {
        	sum += d[i];
        	if(sum <= budget) {
        		count++;
        	}
        	
		}
        
    	answer = count;
    	
    	
        return answer;
    }
	

	public static void main(String[] args) {
		Budget a = new Budget();
		
		int[] d = {2,2,3,3};
		
		System.out.println(a.solution(d, 1));		

	}

}
