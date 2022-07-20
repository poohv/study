package src;

//[[1,2],[2,3]]	[[3,4],[5,6]] ->	[[4,6],[7,9]]
public class ArrayAdd {
	
	   public int[][] solution(int[][] arr1, int[][] arr2) {
	        int[][] answer = new int[arr1.length][arr1[0].length];
	        
	        for (int i = 0; i < arr1.length; i++) {
				for (int j = 0; j < arr1[0].length; j++) {
					int a = arr1[i][j];
					int b = arr2[i][j];
					int sum = a+b;
					answer[i][j]= sum;									
				}	
			}
	        	        
	        return answer;
	    }
	   
	   
	   
	   

	public static void main(String[] args) {
		ArrayAdd a = new ArrayAdd();
		/*
		 * int[][] arr1 = {{1},{2}}; int[][] arr2 = {{3},{4}};
		 */
		
		  int[][] arr1 = {{1,2},{2,3}}; int[][] arr2 = {{3,4},{5,6}};
		
		a.solution(arr1,arr2);

	}

}
