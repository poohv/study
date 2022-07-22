package src;


//���̵��� ���̴� 3 ~ 15�� ���Ͽ��� �մϴ�.
//���̵�� ���ĺ� �ҹ���, ����, ����(-), ����(_), ��ħǥ(.) ���ڸ� ����� �� �ֽ��ϴ�.
//��, ��ħǥ(.)�� ó���� ���� ����� �� ������ ���� �������� ����� �� �����ϴ�.

/*
1�ܰ� new_id�� ��� �빮�ڸ� �����Ǵ� �ҹ��ڷ� ġȯ�մϴ�.
2�ܰ� new_id���� ���ĺ� �ҹ���, ����, ����(-), ����(_), ��ħǥ(.)�� ������ ��� ���ڸ� �����մϴ�.
3�ܰ� new_id���� ��ħǥ(.)�� 2�� �̻� ���ӵ� �κ��� �ϳ��� ��ħǥ(.)�� ġȯ�մϴ�.
4�ܰ� new_id���� ��ħǥ(.)�� ó���̳� ���� ��ġ�Ѵٸ� �����մϴ�.
5�ܰ� new_id�� �� ���ڿ��̶��, new_id�� "a"�� �����մϴ�.
6�ܰ� new_id�� ���̰� 16�� �̻��̸�, new_id�� ù 15���� ���ڸ� ������ ������ ���ڵ��� ��� �����մϴ�.
    ���� ���� �� ��ħǥ(.)�� new_id�� ���� ��ġ�Ѵٸ� ���� ��ġ�� ��ħǥ(.) ���ڸ� �����մϴ�.
    
7�ܰ� new_id�� ���̰� 2�� ���϶��, new_id�� ������ ���ڸ� new_id�� ���̰� 3�� �� ������ �ݺ��ؼ� ���� ���Դϴ�.
*/
//"...!@BaT#*..y.abcdefghijklm"

public class Newidcreate {
	
	public static String solution(String new_id) {
        String answer = new_id;
        String removechar = "[^a-z0-9-_.]";
        answer = answer.toLowerCase();
        answer = answer.replaceAll(removechar, ""); //...bat..y.abcdefghijklm
        answer = answer.replaceAll("\\.{2,}", "."); //.bat.y.abcdefghijklm
        
      
        if(answer.startsWith(".")) {
        	  answer=answer.substring(1,answer.length());
        	  if(answer.isEmpty()) 
        	     answer = "a";  	
        }
   
        if(answer.length() >= 16)
            answer = answer.substring(0, 15);	
     
        if (answer.endsWith("."))
        answer =answer.substring(0,answer.length()-1);
        
        String add = answer.substring(answer.length()-1, answer.length());
        
        while(answer.length() < 3) {
        	answer += add;
        	
        }       
        return answer;
    }

	public static void main(String[] args) {
		String typeone = "...!@BaT#*..y.abcdefghijklm..";
		String typetwo = "z-+.^.";
		String typethree = "abcdefghijklmn.p";
		String basfd= "=.=";
		String sfas= "123_.def";
		
		System.out.println(solution(typeone));
		System.out.println(solution(typetwo));
		System.out.println(solution(typethree));
		System.out.println(solution(basfd));
		System.out.println(solution(sfas));
	}

}
