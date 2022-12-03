import java.sql.*;
import java.util.Scanner; 

public class SportsCenter {
	
	private MySQL mysql;
    private Scanner scanner;	
    

    SportsCenter(){
    	scanner = new Scanner(System.in);
    	mysql = new MySQL();
    }
	
	public static void main(String[] args) {
		SportsCenter app = new SportsCenter();
		int chooseNum = -1;
		while(chooseNum != 0) {			
			app.showMenu();
			chooseNum = app.scanner.nextInt();
			app.function(chooseNum);
		}
		
	}
	
	void showMenu() {
			System.out.println("1.회원추가, 2.강사추가, 3.강습추가, 4.개설강습조회, 5.강습비용조회");
			System.out.println("6.자유이용권등록, 7.강습등록, 8.전체회원조회, 9.강습등록 회원조회, 10.자유이용권 회원수조회");
			System.out.println(",0.종료");
	}
	
	void function(int chooseNum) {
		switch(chooseNum) {
		case 1:
			insertMember(); 
			break;
		case 2:
			insertInstructor();
			break;
		case 3:
			insertTrainingProgram();
			break;
		case 4:
			showTraingProgram(); 
			break;
		case 5:
			showTraingProgramCost();
		case 6:
			regFreePass(); 
			break;
		case 7:
			regTraingProgram();
			break;
		case 8:
			showAllMember();
			break;
		case 9:
			showMemberTakingTrainPro(); 
			break;
		case 10:
			showAllMemberCountForFreePass();	
		default:
			System.out.println("잘못 입력하셨습니다.");
		}
	}


	

	private void insertMember() {
		// TODO Auto-generated method stub
		String name, sex, ssn, job, phoneNum;
		System.out.println("회원이름: ");
		name = scanner.next();
		System.out.println("주민등록번호: ");
		ssn = scanner.next();
		System.out.println("성(남,여): ");
		sex = scanner.next();
		System.out.println("직업(학생/교직원/일반): ");
		job = scanner.next();
		System.out.println("전화번호: ");
		phoneNum = scanner.next();

		//mysql 수행
		
	}
	
	private void insertInstructor() {
		// TODO Auto-generated method stub
 
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
		
	}
	
	private void insertTrainingProgram() {
		// TODO Auto-generated method stub
		
		
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
		
		//mysql수행
		
	}
	

	private void regFreePass() {
		// TODO Auto-generated method stub
		
	}
	
	private void regTraingProgram() {
		// TODO Auto-generated method stub
		
	}
	
	private void showTraingProgram() {
		// TODO Auto-generated method stub
		System.out.println("보고싶은 운동을 선택하세요");
		String exercise = scanExercise();
		
		//mysql 수행
	}
	private void showTraingProgramCost() {
		// TODO Auto-generated method stub

		System.out.println("보고싶은 운동을 선택하세요");
		String exercise = scanExercise();
		//mysql 수행
	}
	
	private void showAllMember() {
		// TODO Auto-generated method stub
		//mysql 수행
	}
	
	private void showMemberTakingTrainPro() {
		// TODO Auto-generated method stub
		
		System.out.println("보고싶은 운동을 선택하세요");
		String exercise = scanExercise();
		
	}
	
	private void showAllMemberCountForFreePass() {
		// TODO Auto-generated method stub
		//mysql 수행
	}
	
	

}
