package FINAL_REPORT;
import java.sql.Date;

public class USAGER {
	private int STUDENT_ID;
	private String USER_NAME;
	private int BOOK_LOANCOUNT;
	
	public int getSTUDENT_ID() {
		return STUDENT_ID;
	}
	public void setSTUDENT_ID(int sTUDENT_ID) {
		STUDENT_ID = sTUDENT_ID;
	}
	public String getUSER_NAME() {
		return USER_NAME;
	}
	public void setUSER_NAME(String uSER_NAME) {
		USER_NAME = uSER_NAME;
	}
	public int getBOOK_LOANCOUNT() {
		return BOOK_LOANCOUNT;
	}
	public void setBOOK_LOANCOUNT(int bOOK_LOANCOUNT) {
		BOOK_LOANCOUNT = bOOK_LOANCOUNT;
	}
}
