import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Console {
	static String[] argArr;
	static LinkedList q = new LinkedList();
	static final int MAX_SIZE = 5;
	static File curDir;	//현재디렉토리
	
	static{
		try{
			String dir = System.getProperty("user.dir");
			curDir = new File(dir);
		}catch(Exception e){}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String input;
		String prompt;
		
		while(true){
			try {
				prompt = curDir.getCanonicalPath()+">>";
				System.out.print(prompt);
				//화면에서 라인단위로 입력받는다
				input = sc.nextLine();
				//저장하기
				save(input);
				//공백제거
				input = input.trim();
				//argArr = new String[input.split("\\s+").length];
				//배열 새로만들고 원래있는 배열이 가리키게만 하면됨!!
				//배열의 원래크기는 변하지 않으니 새로운걸 만들어서 가르키게해주면 끝
				argArr = input.split("\\s+");
				String command = argArr[0].trim();
				
				//명령이 빈칸일때
				if("".equals(command))continue;
				
				command = command.toLowerCase();
				
				if(command.equals("Q")){
					System.out.println("종료합니다");
					System.exit(0);;
				}else if(command.equals("history")){
					history();
				}else if(command.equals("dir")){
					dir();
				}else if(command.equals("type")){
					type();
				}else{
					for(int i=0;i<argArr.length; i++){
						System.out.println(argArr[i]);
					}	
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("입력오류입니다.");
				e.printStackTrace();
				
			}	
		}
	}

	public static void history() {
		// TODO Auto-generated method stub
		int i=0;
		ListIterator it = q.listIterator();
		
		while(it.hasNext()){
			System.out.println(++i+"."+it.next());
		}
	}

	public static void save(String input) {
		// TODO Auto-generated method stub
		if(input==null||"".equals(input))return;
		
		//tail에 삽입
		q.offer(input);
		if(q.size()>MAX_SIZE){	
			//head를 삭제한다.
			q.remove();
		}
	}

	public static void dir(){
		String p="";
		
		File[] fileList = curDir.listFiles();
		switch(argArr.length){
		case 1:
			for(int i=0;i<fileList.length;i++){
				if(fileList[i].isDirectory()){
					System.out.println("["+fileList[i].getName()+"]");
				}else{
					System.out.println(fileList[i].getName());
				}
			}
			break;
		case 2:
			p = argArr[1];
			p = p.toUpperCase();
			p = p.replace(".","\\.");
			p = p.replace("*",".*");
			p = p.replace("?", ".{1}");
			
			Pattern pattern = Pattern.compile(p);
			for(int i=0;i<fileList.length;i++){
				Matcher matcher = pattern.matcher(fileList[i].getName().toUpperCase());
				if(matcher.matches()){
					if(fileList[i].isDirectory()){
						System.out.println("["+fileList[i].getName()+"]");
					}else{
						System.out.println(fileList[i].getName());
					}
				}
			}
			break;
		default:
			System.out.println("USAGE : dir [FILENAME]");
		}
		
		
	}

	public static void type() throws IOException{
		if(argArr.length != 2){
			System.out.println("Usage: type FILE_NAME");
			return;
		}
		String fileName = argArr[1];
		
		File tmp  = new File(fileName);
		
		if(tmp.exists()){
			//fileReader가 입력 스트림을 생성한다.
			//bufferedReader이런애들이 스트림에서 읽어오는
			BufferedReader br = new BufferedReader(new FileReader(tmp.getName()));
			while(true){
				String line = br.readLine();
				if(line==null)break;
				System.out.println(line);
			}
			br.close();
		}else{
			System.out.println("존재하지 않는 파일입니다.");
		}
	}
}
