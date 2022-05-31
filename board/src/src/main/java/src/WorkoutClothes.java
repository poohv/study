package src;

public class WorkoutClothes {
	
	public static int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;   
        int num = 0;

        
        for (int i = 0; i < lost.length; i++) {
			int fr = lost[i]-1; //1
			int br = lost[i]+1; //3
        		
			for (int j = 0; j < reserve.length; j++) {			
					if(fr==reserve[j]) {
						reserve[j]=0;	
						++num;
					break;
					}
					else if(br == reserve[j]) {
						reserve[j]=0;
						++num;
						break;
						}			
			}
		}
        answer = n-lost.length+num;
             
        return answer;
    }

	public static void main(String[] args) {
		int n = 5;
		int[] lost = {3,5};
		int[] res = {1}; //{3}
		//5
		
		System.out.println(solution(n,lost,res));
	}

}
