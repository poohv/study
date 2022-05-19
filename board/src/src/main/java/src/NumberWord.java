package src;

import java.util.HashMap;
import java.util.Map;


public class NumberWord {
	
	public static int solution(String s) {
		String[] en = {"zero","one","two","three","four","five","six","seven","eight","nine"};
		String rex = "[^0-9]";
		
		Map<String, Integer> word = new HashMap<>();
		String imsi = null ;
	
		
		for (int i = 0; i < en.length; i++) {
			word.put (en[i], i);
		}
		
		for (int i = 0; i < en.length; i++) {
			 int gg = word.get(en[i]);
			 String asd = String.valueOf(gg);
			 	 
			s = s.replaceAll(en[i],asd);
		}
		
		
        int answer = 0;

        return answer;
        
    }

	public static void main(String[] args) {
		//1478,234567,234567,123
		
		  String[] params = {"one4seveneight","23four5six7","2three45sixseven","123"};
		  
		  for(int i=0; params.length > i ; i++) { solution(params[i]); }
		 
		

	}

}
