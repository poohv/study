package src;


//				numbers				  hand		result
//{7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2}	"right"	"LRLL LRLLRRL"
//"LRLLRRLLLRR"
// LRLLRRLLLRR

/*
1 2 3
4 5 6
7 8 9
  0
  */
public class Keypad {
	
	
	   public static String solution(int[] numbers, String hand) {
		   String answer = "";
		   int right = 12;
		   int left = 10;
		   for (int i = 0; i < numbers.length; i++) {
			   int params =numbers[i];
			   if(params == 0) {
				   params =11;
			   }
			   if(params==1 || params==4 || params==7) {
				   answer += "L";
				   left = params;
			   }else if (params==3 || params==6 || params==9) {
				   answer += "R";
				   right = params;
			   }else if (params==2 ||params==5 ||params==8 ||params==11) {

				   int LS =Math.abs((left -params))/3  + Math.abs((left-params))%3;
				   int RS = Math.abs((right - params))/3 +Math.abs((right-params))%3;
				   
				   if(LS>RS) {
					   answer +="R";
					   right  =params;
				   }else if (LS==RS) {
					   
					   if(hand.contains("right")) {
						   answer +="R";
						   right  =params;
					   }else {
						   answer +="L";
						   left = params;
					   }
				   }
				   
				   else {
					   answer +="L";
					   left = params;
				   }
				   
			   }
			   	   
		   }
		  	  
	        return answer;
	    }
	   
	   
	   //피타고라스 
	static double getDistance(int x, int y, int x1, int y1){		
			return Math.sqrt(Math.pow(Math.abs(x1-x), 2) + Math.pow(Math.abs(y1-y), 2));
		}
	
	
	
	public static void main(String[] args) {
		int[] num = {7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2};
		String hand = "left";
		
		System.out.println(getDistance(0,1,1,1));
	 System.out.println(solution(num,hand));

	}

}
