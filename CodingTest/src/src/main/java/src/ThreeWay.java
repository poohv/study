package src;

import java.util.ArrayList;
import java.util.List;

public class ThreeWay {
	
	public Long solution(int n) {
		 Long answer = (long) 0;
		 Long sum = (long) 0;
		 boolean ch = true;
		 List<Long> moc = new ArrayList<Long>();
		 List<Long> mod = new ArrayList<Long>();
		 
		 Long num =  (long) n;	
		 if(n !=1 ) {
		 while(ch) {			
			 if(3<= num ) {
				 	mod.add((long) Math.floorMod(num, 3));	
					 num  = Math.floorDiv(num, 3);
					 moc.add(num);					
				 }else {
				 ch = false; 
				 mod.add(moc.get(moc.size()-1));
				 }	 
		 }
		 
		 
		 num = (long) 1;
		 for (int i =  mod.size()-1; i >= 0; i--) {		
			sum = num*mod.get(i);
			num = num*3;			
			answer += sum; 
		}
		 }else {answer = (long) 1;}
		 		 
		 return answer;
    }
	
	//다른사람 풀이
	 public int solution2(int n) {
	        String a = "";

	        while(n > 0){
	            a = (n % 3) + a;
	            n /= 3;
	        }
	        a = new StringBuilder(a).reverse().toString();


	        return Integer.parseInt(a,3);
	    }
	
	
	
	

	public static void main(String[] args) {
		ThreeWay a = new ThreeWay();
		System.out.println(a.solution2(45));
		

	}

}
