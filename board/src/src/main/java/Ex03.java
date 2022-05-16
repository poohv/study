

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

//				id_list								report													k	result
//["muzi", "frodo", "apeach", "neo"]	["muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"]	2	[2,1,1,0]
//["con", "ryan"]						["ryan con", "ryan con", "ryan con", "ryan con"]					3	[0,0]
//return 하는 배열은 id_list에 담긴 id 순서대로 각 유저가 받은 결과 메일 수를 담으면 됩니다.

public class Ex03 {

	private int[] solution(String[] id_list, String[] report, int k) {
		Map<String, String[]> result = new HashMap<>();
		Map<String, String[]> stopID = new HashMap<>();

		//{muzi=[neo, frodo], frodo=[neo], apeach=[muzi, frodo]}
		for (int i = 0; i < report.length; i++) {	
			//Set<String> reporter =  new HashSet<String>();
			String[] name = report[i].split(" ");
			String[] sfa= {name[1]};
	
			if(result.get(name[0]) !=null) {
				if(!result.get(name[0])[0].equals(name[1])) {
				sfa = new String[sfa.length+1];
				sfa[0]=name[1];
				sfa[1]=result.get(name[0])[0];	
				}
			}
			result.put(name[0],sfa);		
		}
		
		
		for (int i = 0; i < report.length; i++) {	
			//Set<String> reporter =  new HashSet<String>();
			String[] name = report[i].split(" ");
			String[] sfa= {name[0]};
			
			if(stopID.get(name[1]) !=null ) {
				if(!stopID.get(name[1])[0].equals(name[0])) {
				sfa = new String[sfa.length+1];
				sfa[0]=name[0];
				sfa[1]=stopID.get(name[1])[0];	
				}
			}
			stopID.put(name[1],sfa);		
		}
		 
		/*
		 * Set<String> setlist = new HashSet<String>(); for (int i = 0; i <
		 * report.length; i++) { setlist.add(report[i].split(" ")[1]); }
		 * 
		 * 
		 * Iterator<String> it = setlist.iterator();
		 * 
		 * while (it.hasNext()) { String fireid = it.next(); Set<String> test = new
		 * HashSet<String>(); int i =0; for ( i = 0; i < report.length; i++) {
		 * if(fireid.equals(report[i].split(" ")[1])) {
		 * test.add(report[i].split(" ")[0]); stopID.put(fireid, test); } } }
		 */
		
		System.out.println("신고자 리스트: "+stopID);
		
		int[] tnt = new int[id_list.length];
		
		for (int j = 0; j < id_list.length; j++) {
			int b = 0;
			if(result.get(id_list[j])!=null) {
			String[] aa = result.get(id_list[j]); 	
			
		
			for (int i = 0; i < aa.length; i++) {
				if(stopID.get(aa[i]).length >= 2) {
					++b;
				}
			}	
			tnt[j]= b;
			}
		}
			
	
		
		//중복수량 체크
		//Collections.frequency(a,b); 
		
		return tnt;
	}

	public static void main(String[] args) {
		// String[] id_list = {"muzi", "frodo", "apeach", "neo"};
		Ex03 ab = new Ex03();	
		String[] id_list = { "con", "ryan" };
		String[] report =  {"ryan con", "ryan con", "ryan con", "ryan con"};
		int k = 2;
		int[] output = 	ab.solution(id_list, report, k);
		
		System.out.println(output);
	
		

	}

}
