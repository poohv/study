package src;


/*s		return
"abcde"	"c"
"qwer"	"we"
*/
public class WordCenter {
	
    public String solution(String s) {
        String answer = "";
        
        for(int i=0; i<s.length(); i++){
            if(s.length()%2==1) {
            	
            int a =	(s.length()-1)/2;
            answer=   s.substring(a,a+1);
            	
            }else {
                int a =	(s.length()-1)/2;
                answer=   s.substring(a,a+2);              	          	
            } 
        }
        
        
        return answer;
    }

	public static void main(String[] args) {
		WordCenter a = new WordCenter();
		System.out.println(a.solution("qwer"));
		

	}

}
