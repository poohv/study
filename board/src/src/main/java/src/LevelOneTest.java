package src;

import java.util.Arrays;

public class LevelOneTest {
	 // a B z (4) -> "e F d"
	// ab (1) -> bc 
	// a (1) -> b

	private String[] params = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
      	

	public String solution(String s, int n) {
	      				
			String answer = "";					
	        int  len =  s.length();
	        String ch = s.substring(s.length()-1);
	        String[] split = s.split("");			
			boolean check = false;
			String ss = "";
			  for (int i = 0; i <= split.length; i++) {
				if(split[i].toLowerCase().equals(split[(i+1) % split.length].toLowerCase())) {
					
					check = true;
					split[i] =  " "+split[i];
					
					ss += split[i];
				    // answer = amodel(split,n);
				}
			}
			  split =ss.split("");
			  
			
			  		
	        
	        if(s.contains(" ")) {       

	        	answer += amodel(split,n);
	                	        
	        } else {
	        	answer +=bmodel(split,n,ch,len);
	        }
	               	     	
	      	return answer;
	}
	
	
	
	
	public String amodel(String[] sp,  int n) {
		System.out.println("A¸ðµ¨ ½ÇÇà");
		String resulte = "";
	     for (int i = 0; i < sp.length; i++) {        	
					for (int j = 0; j < params.length; j++) {
						if(params[j].equals(sp[i])) {					
							for (int j2 = j+n; j2 <= j+n; j2++) {							
								sp[i] = params[j2 % params.length];																		 
							}
							break;
						} else if(params[j].equals(sp[i].toLowerCase())) {
							for (int j2 = j+n; j2 <= j+n; j2++) {	
								sp[i] = params[j2 % params.length].toUpperCase();																															 							 
							}
							break;				
						}
					}
					if(sp[i].equals("")) {
						resulte += " ";
					}else {
						resulte += sp[i];	
					}
									
				}	
		 
		return resulte;
	}
	
	
	public String bmodel(String[] sp,  int n ,String ch ,int len) {
		System.out.println("b¸ðµ¨ ½ÇÇà");
		String resulte = "";
		
        for (int i = 0; i < params.length; i++) {
	    	  if(ch.equals( params[i])) {
	    		  
	    		  if(len > 1) {
		    		  for (int j = i; j <= i+n; j++) {
		    			  sp[i] = params[j % params.length];
		    			  
		    			  
		    			resulte += params[j % params.length];
					}
		    		  break;
		    		  
	    		  }else {
	    			  for (int j = i+n; j <= i+n; j++) {
	    				  resulte += params[j % params.length];
					}	
	    			  break;
	    		  }   	    		 
	    	  }  
	    	  
	    	  	if(ch.equals(params[i].toUpperCase())) {
	    		  
	    		  if(len > 1) {
		    		  for (int j = i; j <= i+n; j++) {
		    			  resulte += params[j % params.length].toUpperCase();
					}
		    		  break;
		    		  
	    		  }else {
	    			  for (int j = i+n; j <= i+n; j++) {
	    				  resulte += params[j % params.length].toUpperCase();
					}	
	    			  break;
	    		  }   	    		 
	    	  } 
		}	  	              	
    		 
		return resulte;
	}
	

	
	
	public static void main(String[] args) {
		LevelOneTest a = new LevelOneTest();
		//A a Z z = Z z Y y
		System.out.println(a.solution("Aa",25));
		
	
		

	}

}
