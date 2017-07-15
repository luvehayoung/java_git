import java.util.*;

public class QuizEx {
	public static int score = 0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] arr;
		String question;
		String answer;
		String input;
		Scanner sc = new Scanner(System.in);
		ArrayList<String> data_list = new ArrayList<String>();
		
		String[] data = {
			"다음 중 키워드가 아닌 것은?'2'final'True'if'public",
			"다음 중 자바의 연산자가 아닌 것은?'5'&'|'++'|='/'^",
			"다음 중 메서드의 반환값이 없음을 의미하는 키워드는?'1'void'null'false"
		};
		
		data_list = QuizEx.shuffle(data);
		
		for(int i=0;i<data_list.size();i++){
			arr = QuizEx.splitQuiz(data_list.get(i));
			question = arr[0];
			answer = arr[1];
			
			System.out.println("["+(i+1)+"]"+question);
						
			for(int j=2;j<arr.length;j++){
				System.out.print((j-1)+"."+arr[j]+" ");
			}
			//입력받기
			System.out.println("");
			input = sc.nextLine();
				
			guessAnswer(input, answer);
			
		}
		
		System.out.println("정답개수/전체문항수: "+score + '/' + data.length);
	}
	
	public static String[] splitQuiz(String q) {
		// TODO Auto-generated method stub
		String[] arr;
		arr = q.split("'");
		return arr;
	}
	
	public static void guessAnswer(String input, String answer){
		if(answer.equals(input)){
			score++;
			System.out.println("정답입니다!");
		}else{
			System.out.println("오답입니다");
		}
		System.out.println("정답: "+ answer);
	}
	
    public static ArrayList<String> shuffle(String[] input_arr) {
		// TODO Auto-generated method stub
    	ArrayList<String> input_list = new ArrayList<String>(Arrays.asList(input_arr));
    	ArrayList<String> temp = new ArrayList<String>();
    	int len = input_arr.length;
    	int i;
    	int index;
    	
    	for(i=len;i>0;i--){
    		index = (int)(Math.random()*i);
    		temp.add(input_list.get(index));
    		input_list.remove(index);
    	}
    	return temp;
    }
}
