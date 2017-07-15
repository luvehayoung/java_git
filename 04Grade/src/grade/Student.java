package grade;

public class Student implements Comparable<Student>{
	public String name;
	public int classNo;
	public int studentNo;
	public int Korean;
	public int Math;
	public int English;
	public int Total;
	public int schoolRank= 0;	//전교등수
	public int classRank = 0;	//반등수
	
	
	public String getName() {
		return name;
	}

	public int getClassNo() {
		return classNo;
	}

	public int getStudentNo() {
		return studentNo;
	}

	public int getKorean() {
		return Korean;
	}

	public int getMath() {
		return Math;
	}

	public int getEnglish() {
		return English;
	}

	public int getTotal() {
		return Total;
	}

	public void setSchoolRank(int schoolRank) {
		this.schoolRank = schoolRank;
	}
	
	public void setClassRank(int classRank) {
		this.classRank = classRank;
	}
	
	public int getClassRank() {
		return classRank;
	}
	
	public Student(String name, int classNo, int studentNo, int Korean, int Math, int English) {
		// TODO Auto-generated constructor stubs
		this.name = name;
		this.classNo = classNo;
		this.studentNo = studentNo;
		this.Korean = Korean;
		this.Math = Math;
		this.English = English;
		this.Total = this.Korean + this.Math + this.English;
		
	}

	//toString()은 생략도 가능하다 Student객체 부르면 자동으로호출된다. .toString을 써도같다.
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%s\t%d\t%d\t%d\t%d\t%d\t%d\t%d\t%d",name, classNo, studentNo, Korean, Math, English, Total, schoolRank, classRank);
	}

	@Override
	public int compareTo(Student o) {
		// TODO Auto-generated method stub
		//내림차순
		if(Total>o.getTotal()){
			return -1;
		}else if(Total<o.getTotal()){
			return 1;
		}else{
			return 0;
		}
		
	}

	

}
