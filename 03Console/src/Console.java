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
	static String dir;
	
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
				}else if(command.equals("find")){
					find();
				}else if(command.equals("find2")){
					find2();
				}else if(command.equals("cd")){
					cd();
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

	public static void find() throws IOException{
		Boolean key_exist = false;
		int line_num=0;
		if(argArr.length!=3){
			System.out.println("USAGE: find Keyword File_NAME");
			return;
		}
		String keyword = argArr[1];
		String fileName = argArr[2];
		
		File tmp = new File(fileName);
		
		if(tmp.exists()){
			//fileReader가 입력 스트림을 생성한다.
			//bufferedReader이런애들이 스트림에서 읽어오는
			BufferedReader br = new BufferedReader(new FileReader(tmp.getName()));
			while(true){
				String line = br.readLine();
				if(line==null)break;
				line_num++;
				if(line.contains(keyword)){
					System.out.println(line_num+": "+line);
					key_exist=true;
				}
			}
			
			if(key_exist==false)
				System.out.println("파일에 키워드가 포함되지않습니다.");
			br.close();
		}else{
			System.out.println("존재하지 않는 파일입니다.");
		}
		
	}

	public static void find2() throws IOException{
		File[] fileList = curDir.listFiles();
		int line_num = 0;
		Boolean key_exist = false;
		if(argArr.length!=3){
			System.out.println("USAGE: find Keyword File_NAME");
			return;
		}
		
		String keyword = argArr[1];
		String p = argArr[2];
		p = p.toUpperCase();
		p = p.replace(".","\\.");
		p = p.replace("*",".*");
		p = p.replace("?", ".{1}");
		Pattern pattern = Pattern.compile(p);
				
		for(int i=0;i<fileList.length;i++){
			Matcher matcher = pattern.matcher(fileList[i].getName().toUpperCase());
					
			if(matcher.matches()){
				System.out.println("---------"+fileList[i].getName());
				BufferedReader br = new BufferedReader(new FileReader(new File(fileList[i].getName()).getName()));
				while(true){
					String line = br.readLine();
					if(line==null)break;
					line_num++;
					if(line.contains(keyword)){
						System.out.println(line_num+": "+line);
						key_exist=true;
					}
				}
				
			}
			
			if(!key_exist)
				System.out.println("이 파일에는 키워드가 존재하지 않습니다.");
		}
		
	}

	public static void cd(){
		if(argArr.length==1){
			System.out.println(curDir);
			return;
		}else if(argArr.length > 2){
			System.out.println("USAGE : cd directory");
			return;
		}
		String subDir = argArr[1];
		
		switch(subDir){
		case "..":
			File pDir=new File(curDir.getParent());
//			//설정을 바꾼뒤
//			System.setProperty("user.dir", pDir.getAbsolutePath());
//			//설정바꾼값을 가져와서 넣어준
//			dir = System.getProperty("user.dir");
//			curDir = new File(dir);
			
			curDir = curDir.getParentFile();
			break;
		case ".":
			System.out.println(curDir);
			break;
		default:
//			Boolean dir_exist = false;
//			File[] fileList = curDir.listFiles();
//			for(int i=0;i<fileList.length;i++){
//				if(fileList[i].getName().equals(subDir)){
//					dir_exist = true;
//					System.setProperty("user.dir", fileList[i].getAbsolutePath());
//					dir = System.getProperty("user.dir");
//					curDir = new File(dir);
					File newDir = new File(curDir, subDir);
		
					if(newDir.exists()&&newDir.isDirectory()){
						curDir = newDir;
					}else{
						System.out.println("유효하지 않은 디렉토리입니다.");
					}
				
			}
			
			
			
	
		
	}
}
