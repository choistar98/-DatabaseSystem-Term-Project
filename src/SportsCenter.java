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
			System.out.println("\n\n");
		}
		System.out.println("종료되었습니다.");
		
	}
	
	void showMenu() {
		System.out.println("===================SPORTSCENTER MEMU=====================");
		System.out.println("||***1.회원추가     2.강사추가       3.강습추가   4.자유등록권조회 ***||");
		System.out.println("||***5.개설강습조회  6.자유등록권비용조회 7.강습비용조회 8.자유이용권등록 ***||");
		System.out.println("||**9.강습등록     10.전체회원조회    11.회원정보검색 0.종료      ***||");
		System.out.println("==========================================================");
		System.out.print("원하는 메뉴의 번호를 입력하세요:");
	}
	
	void function(int chooseNum) {
		switch(chooseNum) {
		case 1:
			mysql.insertMember();
			break;
		case 2:
			mysql.insertInstructor();
			break;
		case 3:
			mysql.insertTrainingProgram();
			break;
		case 4:
			mysql.showFreeProgram();
			break;
		case 5:
			mysql.showTraingProgram();
			break;
		case 6:
			mysql.showFreeProgramCost();
			break;
		case 7:
			mysql.showTraingProgramCost(); 
			break;
		case 8:
			mysql.regFreePass();
			break;
		case 9:
			mysql.regTraingProgram(); 
			break;
		case 10:
			mysql.showAllMember();
			break;
		case 11:
			mysql.searchMemberInfo();
			break;
		case 0:
			return;
		default:
			System.out.println("잘못 입력하셨습니다.");
		}
	}


	

}
