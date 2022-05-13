

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
//return �ϴ� �迭�� id_list�� ��� id ������� �� ������ ���� ��� ���� ���� ������ �˴ϴ�.

public class Ex03 {

	private List<String> solution(List<String> id_list, List<String> report, int k) {
		Map<String, Set<String>> result = new HashMap<>();
		Map<String, Set<String>> stopID = new HashMap<>();

		
		for (int i = 0; i < id_list.size(); i++) {	
			Set<String> reporter =  new HashSet<String>();
			int	j=0;
			
			for ( j = 0; j < report.size(); j++) {			
				if(id_list.get(i).equals(report.get(j).split(" ")[0])) {				
					reporter.add(report.get(j).split(" ")[1]);
					result.put(id_list.get(i), reporter);
				}						
			}		
		}
		
		System.out.println("�Ű����� ����Ʈ: "+result);		
		 
		
		Set<String> setlist =  new HashSet<String>();
		for (int i = 0; i < report.size(); i++) {
			setlist.add(report.get(i).split(" ")[1]);			
		}
		
		
		
		Iterator<String> it = setlist.iterator();
		
		while (it.hasNext()) {
			String fireid = it.next();
			Set<String> test =  new HashSet<String>();
			int i =0;
			for ( i = 0; i < report.size(); i++) {
				if(fireid.equals(report.get(i).split(" ")[1])) {
					test.add(report.get(i).split(" ")[0]);
					stopID.put(fireid, test);
				}			
			}			
		}
		
		System.out.println("�Ű��� ����Ʈ: "+stopID);
		
		//�ߺ����� üũ
		//Collections.frequency(a,b); 
		
	
		return null;
	}

	public static void main(String[] args) {
		// String[] id_list = {"muzi", "frodo", "apeach", "neo"};
		Ex03 soultion = new Ex03();

		List<String> output = new ArrayList<>();
		List<String> id_list = new ArrayList<>(Arrays.asList(new String[] { "muzi", "frodo", "apeach", "neo" }));
		List<String> report = new ArrayList<>(
				Arrays.asList(new String[] { "muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi" }));
		int k = 2;

		output = soultion.solution(id_list, report, k);
		

	}

}
