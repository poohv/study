package src;
	
import java.util.*;

public class OutputResult_02 {
	
	  static  public int[] solution(String[] id_list, String[] report, int k) {
		  	//id_list : { "con", "ryan" };
		  	//repot :  {"ryan con", "ryan con", "ryan con", "ryan con"};
	        int[] ret = new int[id_list.length];
	        //[0, 0]
	        
	        Map<String, Integer> index = new HashMap<>();
	        
	        Map<String, List<Integer>> list = new HashMap<>();
			
	        for(int i=0 ; i<id_list.length ; i++) {
				index.put(id_list[i],i);
			}//{ryan=1, con=0}
	        
	        
	        for(String rep : report) {
	        	
	        	String[] ids = rep.split(" ");
	        	
	        	
	        	String fromId=ids[0], toId=ids[1];
	        	
	        	
	        	if(!list.containsKey(toId)) 
	        		
	        		
	        	list.put(toId, new ArrayList<Integer>());
	        	
	        	List<Integer> tmp = list.get(toId);
	        	
	        	
	        	if(!tmp.contains(index.get(fromId))) 
	        		
	        		tmp.add(index.get(fromId));
	        }
	        
	        for(int i=0 ; i<id_list.length ; i++) {
	        	String id = id_list[i];
	        	if(list.containsKey(id) && list.get(id).size()>=k) {
		        	for(int idx : list.get(id)) {
		        		ret[idx]++;
		        	}
	        	}
	        }
	        
	        return ret;
	    }

	public static void main(String[] args) {
		String[] id_list = { "muzi", "frodo", "apeach", "neo" };
		String[] report =  {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
		int k = 2;
		int[] output = 	solution(id_list, report, k);
		
		System.out.println(output);
		

	}

}
