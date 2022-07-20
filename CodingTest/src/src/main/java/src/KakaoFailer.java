package src;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
  	N		stages						result
	5	[2, 1, 2, 6, 2, 4, 3, 3]	[3,4,2,1,5]
	4	[4,4,4,4,4]					[4,1,2,3]
	3   [1,1,1]						[0,0,0]
*/
public class KakaoFailer {
		
	public static int[] solution(int N, int[] stages) {
        //{0,0,0,0,0}
		//double[] result = new double[N];
		
		int[] answer = new int[N];

		//8(1)	,7(2),					4(2),2(1),1(0)
		//[0.125, 0.42857142857142855, 0.5, 0.5, 0.0]
		// 1            2 				3	4	5
		Map<Integer,Double> rslist = new HashMap<>();
		List<Double> result2 = new ArrayList<>();
        
	  //스테이지 실패율 값 
		for (int i = 1; i <= N; i++) {
			//i=1
		      double stgcount = 0;
		      double ing = 0;
			for (int j = 0; j < stages.length; j++) {
				//j=1 //stages: 2
				 if(i <= stages[j]) { ++stgcount; } 
				 if(i == stages[j]) {++ing;}
				 
				 
			} 
			//스테이지에 도달했으나 아직 클리어하지 못한 플레이어의 수 / 스테이지에 도달한 플레이어 수
			
			if(stgcount==0 && ing==0)
			{
				result2.add(0.0);	
				rslist.put(i, 0.0);
			}else {
				result2.add(ing/stgcount);	
				rslist.put(i, ing/stgcount);
			}
		}
		
		Collections.sort(result2, Collections.reverseOrder());
		//[0.5, 0.5, 0.42857142857142855, 0.125, 0.0]

		for (int i = 0; i < result2.size(); i++) {
			for (int j = 0; j <= N; j++) {
				//0.5
				if(result2.get(i).equals(rslist.get(j+1))) {
					answer[i]=j+1;
					rslist.remove(j+1);
					break;
				}
			}
		}
		
        return answer;
    }
	
	
	

	public static void main(String[] args) {
		int n = 3;
		int[] st ={1,1,1};
			//{4,4,4,4}; 
			
		int[] rs = solution(n,st);
		
		for (int i = 0; i < rs.length; i++) {
			System.out.println(rs[i]);	
		}
		
		
		

	}

}
