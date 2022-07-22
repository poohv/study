package src;

import java.util.ArrayList;
import java.util.List;

public class Divisor {
	
	public int solution(int left, int right) {
        int answer = 0;
       
        List<Integer> list =new ArrayList<Integer>();
        int[] countlist = new int[(right-left)+1];
        int num= 0;
        
        
        //left~right 숫자 담기
        for (int i = left; i <= right; i++) {				
        	list.add(i);
			//약수 갯수 구하기 	
        	 int count = 0;
			for (int j = 1; j <= i; j++) {
				if(0==i%j) {
					count++;		
				} 
			}			
			countlist[num] = count;
			++num;
			
			
			
		}
        
        for (int i = 0; i < countlist.length; i++) {
			if (countlist[i]%2==0) {
				answer+= list.get(i);
			}else {
				answer+= -list.get(i);
				
			}
			 
		}
            
        return answer;
    }
	
	
	
	
	
	
	
	public static void main(String[] arg) {
		
		Divisor a = new Divisor();
		System.out.println(a.solution(24, 27));
		
		
	}

}
