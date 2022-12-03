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
			stmt=con.createStatement(); 
			System.out.println("success");

		}catch(Exception e) {
			System.out.println(e.toString());
		}
		/*finally {
			try {
				stmt.close();
				con.close();
			}catch(Exception e) {
				System.out.println(e.toString());
			}
		}*/
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MySQL mysql = new MySQL();
		
		//mysql.insertMember();
		//mysql.insertInstructor();
		//mysql.insertTrainingProgram();
		
		//mysql.showTraingProgram();
		//mysql.showTraingProgramCost();
		//mysql.showAllMember();
		
		//특정 강습을 등록한 회원보기
		
		//회원검색
		//회원이 등록한 강습보기
		//회원이 등록한 자유이용권보기
		//
	}
	
	void insertMember() {
			// TODO Auto-generated method stub
		try{
			String name, sex, ssn, job, phoneNum;
			System.out.println("회원이름: ");
			name = scanner.next();
			System.out.println("주민등록번호: ");
			ssn = scanner.next();
			System.out.println("성(남/여): ");
			sex = scanner.next();
			System.out.println("직업(학생/교직원/일반): ");
			job = scanner.next();
			System.out.println("전화번호: ");
			phoneNum = scanner.next();

			//mysql 수행
			//INSERT INTO Member VALUES ('박지성', '1234-1234', '남','일반','010-1234-1234');

			String sql = "INSERT INTO Member VALUES(?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, ssn);	
			pstmt.setString(3, sex);
			pstmt.setString(4, job);
			pstmt.setString(5, phoneNum);
			
			pstmt.executeUpdate();		
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	void insertInstructor() {
		// TODO Auto-generated method stub
		try{
			String name, exercise, ssn, phoneNum;
			int empNum;
			System.out.println("강사이름: ");
			name = scanner.next();
			System.out.println("운동종목: ");
			exercise = scanner.next();
			System.out.println("주민번호: ");
			ssn = scanner.next();
			System.out.println("사번: ");
			empNum = scanner.nextInt();
			System.out.println("전화번호: ");
			phoneNum = scanner.next();
	
			//mysql 수행
			//INSERT INTO Instructor VALUES (1, '수영', '서예미', '871234-1343234', '010-3592-6701')
			String sql = "INSERT INTO Member VALUES(?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, empNum);
			pstmt.setString(2, exercise);	
			pstmt.setString(3, name);
			pstmt.setString(4, ssn);
			pstmt.setString(5, phoneNum);
			pstmt.executeUpdate();		
		}catch(Exception e) {
		System.out.println(e);
		}
	}
	
	void insertTrainingProgram() {
		// TODO Auto-generated method stub
		try{
			int exerciseNum,empNum; 
			String exercise, time, day, level;
			
			System.out.println("운동번호: ");
			exerciseNum = scanner.nextInt();
			
			System.out.println("운동종목: ");
			exercise = scanner.next();
	
			System.out.println("사번: ");
			empNum = scanner.nextInt();
			
			System.out.println("시간: ");
			time = scanner.next();
			
			System.out.println("요일(월수금/화목): ");
			day = scanner.next();
			
			System.out.println("반(상급/중급/초급): ");
			level = scanner.next();
	
			//mysql 수행
			//INSERT INTO Training_program VALUES (1,'수영', 1, '9:00','월수금','상');
	
			String sql = "INSERT INTO Member VALUES(?,?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, exerciseNum);
			pstmt.setString(2, exercise);	
			pstmt.setInt(3, empNum);
			pstmt.setString(4, time);
			pstmt.setString(5, day);
			pstmt.setString(6, level);
			pstmt.executeUpdate();		
		}catch(Exception e) {
			System.out.println(e);
		}
	}

	void showTraingProgram() {
		try {
			
			System.out.println("보고싶은 운동을 선택하세요");
			String exercise = scanExercise();
			
			String sql = "SELECT * FROM Training_program WHERE exercisename = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, exercise);

			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {											
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+
						" "+rs.getString(3) + " "+rs.getString(4)+ " "+rs.getString(5));
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	void showTraingProgramCost() {
		try {
			
			System.out.println("보고싶은 운동을 선택하세요");
			String exercise = scanExercise();
			
			String sql = "SELECT * FROM Training_cost WHERE exercisename = ? ";
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, exercise);

			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {											
				System.out.println(rs.getString(1)+" "+rs.getString(2)+
						" "+rs.getString(3) + " "+rs.getInt(4));
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	void showAllMember() {
		try {
			System.out.println("==전체 조회 ==");
			String viewStr = "SELECT * FROM Member";
			ResultSet rs = stmt.executeQuery(viewStr);
			while(rs.next()) 
				System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+""); 
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	void  showMemberTakingTrainPro() {
		
		System.out.println("보고싶은 운동을 선택하세요");
		String exercise = scanExercise();
		
		try {
			System.out.println("==전체 조회 ==");
			String viewStr = "SELECT * FROM Member";
			ResultSet rs = stmt.executeQuery(viewStr);
			while(rs.next()) 
				System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+""); 
		}catch(Exception e) {
			System.out.println(e);
		}
	}

	
	private String scanExercise() {
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
