package FINAL_REPORT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class USAGERDAO {
	Connection conn = null;
	PreparedStatement pstmt;

//	final String JDBC_DRIVER = "org.h2.Driver";
//	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/jwbookdb";
	
	final String JDBC_DRIVER = "oracle.jdbc.OracleDriver";
	final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:"; // 'orcl'은 예시 SID입니다.

	public void open(){
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL,"yeonseo","010713");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void close() {
		try {
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insert(USAGER u) {
		open();
		String sql = "INSERT INTO USAGER(STUDENT_ID, USER_NAME, BOOK_LOANCOUNT) values(?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, u.getSTUDENT_ID());
			pstmt.setString(2, u.getUSER_NAME());
			pstmt.setInt(3, u.getBOOK_LOANCOUNT());
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public List<USAGER> getAll() {
		open();
		List<USAGER> usagers = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement("select * from USAGER");
			ResultSet rs = pstmt.executeQuery();
	
			while(rs.next()) {
				USAGER u = new USAGER();
				u.setSTUDENT_ID(rs.getInt("STUDENT_ID"));
				u.setUSER_NAME(rs.getString("USER_NAME"));
				u.setBOOK_LOANCOUNT(rs.getInt("BOOK_LOANCOUNT"));

				usagers.add(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return usagers;
	}
}
