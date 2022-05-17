package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//id_list								report													k	result
//["muzi", "frodo", "apeach", "neo"]	["muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"]	2	[2,1,1,0]
//["con", "ryan"]						["ryan con", "ryan con", "ryan con", "ryan con"]					3	[0,0]
//return �ϴ� �迭�� id_list�� ��� id ������� �� ������ ���� ��� ���� ���� ������ �˴ϴ�.

public class OutputResult_03 {
	
	public static int[] Solution(String[] id_list, String[] report, int k) {
		
		int[] anwer = new int[id_list.length];
		Map<String, Integer> index = new HashMap<>();
		Map<String, List<Integer> > stopidlist = new HashMap<>();
		
		for (int i = 0; i < id_list.length; i++) {
			index.put(id_list[i], i);
		}
		
		for (int i = 0; i < report.length; i++) {
		 String reportN[] = report[i].split(" ");
		 String stopid = reportN[1] , userid = reportN[0];

		 if(!stopidlist.containsKey(stopid)) {
			 stopidlist.put(stopid, new ArrayList<Integer>());	
		 }
		 if(! stopidlist.get(stopid).contains(index.get(userid))) {	 
			 stopidlist.get(stopid).add(index.get(userid));}	 
		}
		
		for (int i = 0; i < id_list.length; i++) {
			
		String id  = id_list[i];
		if(stopidlist.containsKey(id)) {
		List<Integer> tt = stopidlist.get(id);
			for (int j = 0; j < tt.size(); j++) {
			int a = tt.get(j);
			 if(stopidlist.get(id).size() >= k) {
					anwer[a]++;
				 }			
			}	
		}
		}
	
		
		
				
		return anwer;
		
	}
	

	
	
	


	public static void main(String[] args) {
		//String[] id_list = { "muzi", "frodo", "apeach", "neo" };
		//String[] report =  {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
		
		String[] id_list = { "con", "ryan" };
		String[] report =  {"ryan con", "ryan con", "ryan con", "ryan con"};
		int k = 2;
		int[] output = 	Solution(id_list, report, k);
		
		System.out.println(output);

	}

}
