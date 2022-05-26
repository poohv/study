package src;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Marathon {
	
	  public static String solution(String[] participant, String[] completion) {
		  String answer = "";        
		  
			/*
			 * Arrays.sort(participant); Arrays.sort(completion);
			 * 
			 * for (int i = 0; i < completion.length; i++) {
			 * if(!participant[i].equals(completion[i])) { answer = participant[i]; break; }
			 * } if(answer.equals("")) answer = participant[participant.length-1];
			 * 
			 * return answer;
			 */
	
	  HashMap<String, Integer> hm = new HashMap<>();
	        for (String player : participant) 
	        	hm.put(player, hm.getOrDefault(player,0) + 1);
	        for (String player : completion) 
	        	hm.put(player, hm.get(player) - 1);

	        for (String key : hm.keySet()) {
	            if (hm.get(key) != 0){
	                answer = key;
	            }
	        }
	        return answer;
	        
	        
	    }
	  
	  
	  

	public static void main(String[] args) {
		String[] a = {"mislav", "stanko", "mislav", "ana"};
		String[] b = {"stanko", "ana", "mislav"};
		System.out.println(	solution(a,b));
	}

}
