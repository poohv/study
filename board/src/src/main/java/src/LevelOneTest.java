package src;

import java.util.Arrays;

public class LevelOneTest {
	
	public String solution(String s, int n) {
		
		
		
		String answer = "";
		for(int i=0; i<s.length(); i++) {
			
			char ch = s.charAt(i);
			
			if(ch==' ') { //공백
				answer += ch;
				continue;
			}
			
			if(ch>='a' && ch<='z') { //소문자
				if(ch+n > 'z') {
					answer += (char)(ch-26+n);
				} else {
					answer += (char)(ch+n);
				}
			}
			
			
			else if(ch>='A' && ch<='Z') { //대문자
				if(ch+n > 'Z') {
					answer += (char)(ch-26+n);
				}else {
					answer += (char)(ch+n);
				}
			}
		}
		
		return answer;
	}

	
	
	
	
	/*
	 * // a B z (4) -> "e F d" // ab (1) -> bc // a (1) -> b
	 * //AB"는 1만큼 밀면 "BC"가 되고, 3만큼 밀면 "DE"가 됩니다.
	 * 
	 * private String[] params =
	 * {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s",
	 * "t","u","v","w","x","y","z"};
	 * 
	 * 
	 * public String solution(String s, int n) {
	 * 
	 * String answer = ""; int len = s.length(); String ch =
	 * s.substring(s.length()-1); String[] split = s.split(""); String ss = "";
	 * 
	 * for (int i = 0; i < split.length; i++) { ss += split[i];
	 * if(split[i].toLowerCase().equals(split[(i+1) % split.length].toLowerCase()))
	 * { ss +=" "; } }
	 * 
	 * 
	 * 
	 * if(s.contains(" ") || ss.contains(" ")) {
	 * 
	 * answer += amodel(split,n);
	 * 
	 * } else { answer +=bmodel(split,n,ch,len); }
	 * 
	 * return answer; }
	 * 
	 * 
	 * 
	 * 
	 * public String amodel(String[] sp, int n) { System.out.println("A모델 실행");
	 * String resulte = ""; for (int i = 0; i < sp.length; i++) { for (int j = 0; j
	 * < params.length; j++) { if(params[j].equals(sp[i])) { for (int j2 = j+n; j2
	 * <= j+n; j2++) { sp[i] = params[j2 % params.length]; } break; } else
	 * if(params[j].equals(sp[i].toLowerCase())) { for (int j2 = j+n; j2 <= j+n;
	 * j2++) { sp[i] = params[j2 % params.length].toUpperCase(); } break; } }
	 * if(sp[i].equals("")) { resulte += " "; }else { resulte += sp[i]; }
	 * 
	 * }
	 * 
	 * return resulte; }
	 * 
	 * 
	 * public String bmodel(String[] sp, int n ,String ch ,int len) {
	 * System.out.println("b모델 실행"); String resulte = "";
	 * 
	 * for (int i = 0; i < params.length; i++) {
	 * 
	 * if(ch.equals( params[i])) { if(len > 1) { for (int j = i; j <= i+n; j++) {
	 * resulte += params[j % params.length]; } break;
	 * 
	 * }else { for (int j = i+n; j <= i+n; j++) { resulte += params[j %
	 * params.length]; } break; } }
	 * 
	 * if(ch.equals(params[i].toUpperCase())) {
	 * 
	 * 
	 * if(1>=n) { for (int j = i+1; j <=j; j++) { resulte += params[j %
	 * params.length].toUpperCase();} }
	 * 
	 * 
	 * 
	 * if(2 >= len ) { for (int j = i; j <=i+n; j++) { resulte += params[j %
	 * params.length].toUpperCase();} resulte =
	 * resulte.substring(resulte.length()-len,resulte.length()); }
	 * 
	 * if(len > 2) {
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * if(n > 1) { for (int j = i+1; j <=len+n; j++) { resulte += params[j %
	 * params.length].toUpperCase(); } break;} else { for (int j = i; j <= len+n;
	 * j++) { resulte += params[j % params.length].toUpperCase(); } break;}
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * } }
	 * 
	 * // resulte = resulte.substring(resulte.length()-len,resulte.length());
	 * 
	 * return resulte;
	 */
	

	public static void main(String[] args) {
		LevelOneTest a = new LevelOneTest();
		// A a Z z = Z z Y y
		// a b c d e f
		// BCDE
		// a -> b 1
		// ab -> de 3
		
		//ab->bc 1, ab-> cd 2, ab -> de 3

		// abc -> cde 1
		// abc -> def 2
		
		//abc -> cde
		// abcd -> defg 1
		// abcd -> efgh 2
		
		System.out.println(a.solution("ABC", 2));

	}

}
