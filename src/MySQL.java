import java.sql.*;
import java.util.Scanner;

public class MySQL {
	private Connection con = null;
	private Statement stmt = null;
	private Scanner scanner;
	ResultSet rs;
	
	MySQL() {
		scanner = new Scanner(System.in);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			con=DriverManager.getConnection("jdbc:mysql://192.168.56.101:4567/sportscenter",
					"yousung","1234");
			stmt= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);; 
			rs = null;

		}catch(Exception e) {
			System.out.println(e.toString());
		}
		
	}

	
	void insertMember() { //회원추가
			// TODO Auto-generated method stub
		try{

			System.out.println("====회원추가=====");
			String name, sex, ssn, job, phoneNum;
			System.out.print("회원이름:");
			name = scanner.next();
			System.out.print("주민등록번호:");
			ssn = scanner.next();
			System.out.print("성(남/여):");
			sex = scanner.next();
			System.out.print("직업(학생/교직원/일반):");
			job = scanner.next();
			System.out.print("전화번호:");
			phoneNum = scanner.next();

			//INSERT INTO Member VALUES ('박지성', '1234-1234', '남','일반','010-1234-1234');

			String sql = "INSERT INTO Member VALUES(?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, ssn);	
			pstmt.setString(3, sex);
			pstmt.setString(4, job);
			pstmt.setString(5, phoneNum);
			
			pstmt.executeUpdate();	
			System.out.println("회원 등록완료");
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	void insertInstructor() {//강사등록
		// TODO Auto-generated method stub
		try{

			System.out.println("====강사추가=====");
			String name, exercise, ssn, phoneNum;
			int empNum;
			System.out.print("강사이름:");
			name = scanner.next();
			System.out.print("운동종목:");
			exercise = scanner.next();
			System.out.print("주민번호:");
			ssn = scanner.next();
			System.out.print("사번:");
			empNum = scanner.nextInt();
			System.out.print("전화번호:");
			phoneNum = scanner.next();
	
			//INSERT INTO Instructor VALUES (1, '수영', '서예미', '871234-1343234', '010-3592-6701')
			String sql = "INSERT INTO Instructor VALUES(?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, empNum);
			pstmt.setString(2, exercise);	
			pstmt.setString(3, name);
			pstmt.setString(4, ssn);
			pstmt.setString(5, phoneNum);
			pstmt.executeUpdate();		
			System.out.println("강사 등록완료");
		}catch(Exception e) {
		System.out.println(e);
		}
	}
	
	void insertTrainingProgram() { //강습프로그램 추가
		// TODO Auto-generated method stub
		try{

			System.out.println("====강습프로그램추가=====");
			int exerciseNum,empNum; 
			String exercise, time, day, level;
			
			System.out.print("운동번호:");
			exerciseNum = scanner.nextInt();
			
			System.out.print("운동종목:");
			exercise = scanner.next();
	
			System.out.print("사번:");
			empNum = scanner.nextInt();
			
			System.out.print("시간:");
			time = scanner.next();
			
			System.out.print("요일(월수금/화목):");
			day = scanner.next();
			
			System.out.print("반(상급/중급/초급):");
			level = scanner.next();
	
			//INSERT INTO Training_program VALUES (1,'수영', 1, '9:00','월수금','상');
	
			String sql = "INSERT INTO Training_program VALUES(?,?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, exerciseNum);
			pstmt.setString(2, exercise);	
			pstmt.setInt(3, empNum);
			pstmt.setString(4, time);
			pstmt.setString(5, day);
			pstmt.setString(6, level);
			pstmt.executeUpdate();		
			
			System.out.println("프로그램 등록완료");
		}catch(Exception e) {
			System.out.println(e);
		}
	}

	void showTraingProgram() { //원하는 운동의 개설강습정보 보기
		try {

			System.out.println("====개설강습조회=====");
			System.out.println("보고싶은 운동을 선택하세요");
			String exercise = scanExercise();
			
			TraingProgramCost(exercise);
			
			String sql = "SELECT * FROM Training_program WHERE exercisename = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, exercise);

			rs = pstmt.executeQuery();

			System.out.println("============"+exercise+"강습정보============");
			while(rs.next()) {											
				System.out.println("강습번호:"+rs.getInt(1)+
						" 시간: "+rs.getString(3) + " 강사사번:"+rs.getString(4)+ " 요일:"+rs.getString(5)+ " 반:"+rs.getString(6) );
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	void showFreeProgram() {// 원하는 운동의 자유운동권 보기
		try {

			System.out.println("====자유운동권조회=====");
			System.out.println("보고싶은 운동을 선택하세요");
			String exercise = scanExercise();
			
			showFreeProgramCost(exercise); //비용 정보보여주기
			
			String sql = "SELECT * FROM Free_program WHERE exercisename = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, exercise);

			rs = pstmt.executeQuery();

			System.out.println("============자유"+exercise+"정보============");
			while(rs.next()) {											
				System.out.println("프로그램번호:"+rs.getInt(1)+
						" 운동: "+rs.getString(2) + " 월:"+rs.getString(3)+ "개월:");
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	void showTraingProgramCost() { // 원하는 강습 운동의 비용정보 보기
			System.out.println("보고싶은 운동을 선택하세요");
			String exercise = scanExercise();
			TraingProgramCost(exercise);
	}
	
	void TraingProgramCost(String exercise) { // 원하는 강습 운동의 비용정보 보기
		try {
			
			String sql = "SELECT * FROM Training_cost WHERE exercisename = ? ";
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, exercise);

			rs = pstmt.executeQuery();
			
			System.out.println("============"+exercise+"강습가격============");

			while(rs.next()) {											
				System.out.println("운동:"+rs.getString(1)+" 요일:"+rs.getString(2)+
						" 직업:"+rs.getString(3) + " 가격:"+rs.getInt(4));
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	
	void showFreeProgramCost() { //원하는 자유운동의 비용정보 보기
			System.out.println("보고싶은 운동을 선택하세요");
			String exercise = scanExercise();
			showFreeProgramCost(exercise);	
	}
	void showFreeProgramCost(String exercise) { //원하는 자유운동의 비용정보 보기
		try {
			
			String sql = "SELECT * FROM Free_cost WHERE exercisename = ? ";
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, exercise);

			rs = pstmt.executeQuery();
			
			System.out.println("============자유"+exercise+"권가격============");

			while(rs.next()) {											
				System.out.println("운동:"+rs.getString(1)+" 개월:"+rs.getString(2)+
						" 직업:"+rs.getString(3) + " 가격:"+rs.getInt(4));
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	
	void showAllMember() { //모든 회원의 정보 조회
		try {
			System.out.println("====전체회원조회=====");
			String sql = "SELECT * FROM Member";
			rs = stmt.executeQuery(sql);
			while(rs.next()) 
				System.out.println("이름: "+rs.getString(1)+" 주민번호:"+rs.getString(2)+" 성별:"+rs.getString(3)+" 직업:"+rs.getString(4)+" 전화번호"+rs.getString(5)+""); 
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	

	
	void searchMemberInfo() { //원하는 회원의 정보 검색
		try {
			System.out.println("====회원조회=====");
			System.out.print("찾고 싶은 회원의 주민번호:");
			String ssn = scanner.next();
			String sql = "SELECT * FROM Member where Member.ssn = ?;";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ssn);
			rs = pstmt.executeQuery();
			
			System.out.println("=====회원정보=====");

			while(rs.next()) {											
				System.out.println(rs.getString(1)+" "+rs.getString(2)+
						" "+rs.getString(3) + " "+rs.getString(4));
			}
			System.out.println("=====등록한 자유이용권=====");
			sql = "SELECT * from Reg_free where ssn = ?;";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ssn);
			rs = pstmt.executeQuery();
			while(rs.next()) {											
				System.out.println("자유운동권 번호:"+rs.getString(2)+" 운동:"+rs.getString(3)+
						" 시작일:"+rs.getString(4));
			}
			
			System.out.println("=====등록한 강습프로그램=====");
			sql = "SELECT * from Reg_training where ssn = ?;";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ssn);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {											
				System.out.println("프로그램 번호:"+rs.getInt(2)+" 운동:"+rs.getString(3)+
						" 신청월:"+rs.getInt(4));
			}
		
			
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
		
	}
	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//MySQL mysql = new MySQL();
		
		//mysql.insertMember();
		//mysql.insertInstructor();
		//mysql.insertTrainingProgram();
		
		
		//mysql.showFreeProgram();
		mysql.showTraingProgram(); //강습정보보기
		//mysql.showFreeProgramCost(); //원하는 자유운동권 비용보기
		//mysql.showTraingProgramCost(); // 원하는 강습비용 비용보기
		//mysql.showAllMember();  //전체 멤버보기
		
		//mysql.regFreePass(); //자유등록권 신청
		//mysql.regTraingProgram(); //강습신청
		//mysql.searchMemberInfo(); //회원검색(강습신청, 자유이용권 확인 가능)
	}
	*/
	//INSERT INTO Reg_free VALUES ('6789-6789',1,'수영', '2022-12-1');
	//INSERT INTO Reg_training VALUES ('3456-3456',1,'수영',12);
	void regFreePass() { //자유이용권 등록
		try {
			System.out.println("=====자유이용권 등록=====");
			String ssn,exercise, startdate;
			int pro_Num;
			
			System.out.print("주민등록번호: ");
			ssn = scanner.next();
			System.out.print("자유운동권번호: ");
			pro_Num = scanner.nextInt();
			System.out.print("운동: ");
			exercise = scanner.next();
			System.out.print("시작일: ");
			startdate = scanner.next();
			
			String sql = "INSERT INTO Reg_free VALUES(?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ssn);
			pstmt.setInt(2, pro_Num);	
			pstmt.setString(3, exercise);
			pstmt.setString(4, startdate);
			
			pstmt.executeUpdate();
			System.out.println("등록 완료");
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	void regTraingProgram() { // 강습 등록하기
		try {
			System.out.println("=====강습 등록=====");
			String ssn,exercise, startdate;
			int pro_Num;
			
			System.out.print("주민등록번호: ");
			ssn = scanner.next();
			System.out.print("강습번호: ");
			pro_Num = scanner.nextInt();
			System.out.print("운동: ");
			exercise = scanner.next();
			System.out.print("시작월: ");
			startdate = scanner.next();
			
			String sql = "INSERT INTO Reg_training VALUES(?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ssn);
			pstmt.setInt(2, pro_Num);	
			pstmt.setString(3, exercise);
			pstmt.setString(4, startdate);
			
			pstmt.executeUpdate();
			System.out.println("등록 완료");
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
	
	}

	
	private String scanExercise() { //원하는 운동 문자열 반환 메소드
		int chooseNum;
		String exercise = null;
		boolean flag =true;
		while(flag) {
			flag = false;
			System.out.println("1.수영, 2.골프, 3.요가, 4.필라테스, 5.헬스, 6.스쿼시");
			chooseNum =scanner.nextInt();
			switch(chooseNum) {
			case 1: exercise="수영" ; break;
			case 2: exercise="골프" ; break;
			case 3: exercise="요가" ; break;
			case 4: exercise="필라테스" ; break;
			case 5: exercise="헬스" ; break;
			case 6: exercise="스쿼시" ; break;
			default:
				System.out.println("잘못 입력하셨습니다. ");
				flag = true;
			}
		}
		return exercise;
	}
}
