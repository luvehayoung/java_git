import java.util.Scanner;

public class WordScramble {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] strArr = {"CHANGE","LOVE","HOPE","VIEW"};
		String input;
		Scanner sc = new Scanner(System.in);
		while(true){
			String answer = getAnswer(strArr);
			String question = getScrambledWord(answer);
			char[] hint = new char[answer.length()];
			
			for(int i=0;i<hint.length;i++){
				hint[i]='_';
			}
			
			System.out.println("Question:" + question);
			System.out.println("Your Answer is:");
			input = sc.nextLine();
			
			if(input.equals("q")||input.equals("Q")){
				System.out.println("프로그램을 종료합니다");
				break;
			}
			
			while(true){
				if(input.equals(answer)){
					System.out.println("정답입니다");
					break;
				}else{
					System.out.println("다시 시도해보세요");
					System.out.println("Hint:"+getHint(answer,hint));
					input = sc.nextLine();
				}
			}
			System.out.println("다음 문제 입니다");
		}
	}

	public static String getHint(String answer, char[] hint) {
		// TODO Auto-generated method stub
		int limit=0;
		while(limit<hint.length){
			int i =(int)(Math.random()*hint.length);
			if(hint[i]=='_'){
				hint[i]=answer.charAt(i);
				break;
			}
			limit++;
		}
		
		return String.valueOf(hint);
	}

	public static String getAnswer(String[] strArr) {
		// TODO Auto-generated method stub
		int r_index = (int)(Math.random()*4);
			
		return strArr[r_index];
	}

	
	public static String getScrambledWord(String answer) {
		// TODO Auto-generated method stub
		
		String question = WordScramble.scramble(answer);
		return question;
	}
	
	public static String scramble(String answer) {
		// TODO Auto-generated method stub
		StringBuilder temp = new StringBuilder(answer);
		StringBuilder str = new StringBuilder("");
		int index;
		int len = answer.length();
		String j;
		
		for(int i=len; i>0; i--){
			index = (int)(Math.random()*i);
			j = String.valueOf(temp.charAt(index));
			str.append(j);
			temp.deleteCharAt(index);
		}
		
		return str.toString();
	}

	
}
