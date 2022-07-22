import java.lang.reflect.Array;
import java.util.Arrays;

public class Budget {
    
	
	
	public int solution(int[] d, int budget) {
        int answer = 0;
       
        int sum = 0;
        int[] list = new int[d.length];
        
        Arrays.sort(d);
        
        
        for (int i = 0; i < d.length; i++) {
        	sum = d[i];
        	int	count = 0;
        	
			for (int j = i+1; j < d.length; j++) {
				
				
				if(sum <= budget) {		
					sum +=d[j];	
					count++;		
				}else {
					sum -= d[j];
				}
				
				
			}
			
			
			if(answer < count) {
				answer = count;
			}
		
		}
        
        
        
        return answer;
    }
	
		
	public static void main(String[] args) {	
		
		Budget a = new Budget();
		//int[] d = {1,3,2,5,4};		
		int[] d = {2,2,3,3};
				//3
		System.out.println(a.solution(d, 10));
		
	}

}
