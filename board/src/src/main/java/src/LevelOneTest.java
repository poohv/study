package src;

import java.util.Arrays;

public class LevelOneTest {
	// a B z (4) -> "e F d"
	// ab (1) -> bc 
	// a (1) -> b

	public String solution(String s, int n) {
	        String answer = "";
	        String[] params = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	        					
	        					
	        int  len =  s.length();
	        String ch = s.substring(s.length()-1);
	        String[] split = s.split("");
	        if(s.contains(" ")) {       

	        for (int i = 0; i < split.length; i++) {
	        	
				for (int j = 0; j < params.length; j++) {
					if(params[j].equals(split[i])) {					
						for (int j2 = j+n; j2 <= j+n; j2++) {							
							split[i] = params[j2 % params.length];																		 
						}
						break;
					} else if(params[j].equals(split[i].toLowerCase())) {
						for (int j2 = j+n; j2 <= j+n; j2++) {	
							split[i] = params[j2 % params.length].toUpperCase();																															 							 
						}
						break;				
					}
				}
				if(split[i].equals("")) {
					answer += " ";
				}else {
					answer += split[i];	
				}
								
			}	
	        
	        	        
	        }else {
	        	
	            for (int i = 0; i < params.length; i++) {
	  	    	  if(ch.equals( params[i])) {
	  	    		  
	  	    		  if(len > 1) {
	  		    		  for (int j = i; j <= i+n; j++) {
	  		    			split[i] = params[j % params.length];
	  		    			  
	  		    			  
	  		    			  answer += params[j];
	  					}
	  		    		  break;
	  		    		  
	  	    		  }else {
	  	    			  for (int j = i+n; j <= i+n; j++) {
	  	    				  answer += params[j % params.length];
	  					}	
	  	    			  break;
	  	    		  }   	    		 
	  	    	  }  
	  	    	  
	  	    	  	if(ch.equals(params[i].toUpperCase())) {
	  	    		  
	  	    		  if(len > 1) {
	  		    		  for (int j = i; j <= i+n; j++) {
	  		    			  answer += params[j % params.length].toUpperCase();
	  					}
	  		    		  break;
	  		    		  
	  	    		  }else {
	  	    			  for (int j = i+n; j <= i+n; j++) {
	  	    				  answer += params[j % params.length].toUpperCase();
	  					}	
	  	    			  break;
	  	    		  }   	    		 
	  	    	  } 
	  		}	  	              	
	        }
	        
	        	        	     	
	      	return answer;
	}
	

	
	
	public static void main(String[] args) {
		LevelOneTest a = new LevelOneTest();
		//A a Z z = Z z Y y
		System.out.println(a.solution("Aa",25));
		
	
		

	}

}
