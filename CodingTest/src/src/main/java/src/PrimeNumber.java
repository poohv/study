package src;

import java.util.ArrayList;
import java.util.List;

public class PrimeNumber {
	
	public int solution(int n) {
		 int answer = 0;
		
		 boolean[] list = new boolean[n];
		 
		 list[0]=true;
		 
		 for (int i = 2; i*i < n; i++) {			 
			 if(!list[i-1]) {
				 for (int j = i*i; j <= n; j +=i) {
						list[j-1] = true;
					}
				 
			 }
	
			
		}
		 
		 
		 		 
		 for (int i = 0; i < list.length; i++) {
			if(!list[i]) {
				answer++;
				
			
			}
			
		}		 	 
	        return answer;
   }
	

	public static void main(String[] args) {
		PrimeNumber a = new PrimeNumber();
		
		System.out.println(a.solution(10));


	}

}
