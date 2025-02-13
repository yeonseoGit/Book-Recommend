package FINAL_REPORT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class REVIEWDAO {
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
	
	public void insert(REVIEW r) {
		open();
		String sql = "INSERT INTO REVIEW(REVIEW_NUM, BORROW_NUMBER, STUDENT_ID, BOOK_NUMBER, RATING) values(?,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, r.getREVIEW_NUM());
			pstmt.setInt(2, r.getBORROW_NUMBER());
			pstmt.setInt(3, r.getSTUDENT_ID());
			pstmt.setInt(4, r.getBOOK_NUMBER());
			pstmt.setInt(5, r.getRATING());
			
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public List<REVIEW> getAll() {
		open();
		List<REVIEW> reviews = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement("select * from REVIEW");
			ResultSet rs = pstmt.executeQuery();
	
			while(rs.next()) {
				REVIEW r = new REVIEW();
				r.setREVIEW_NUM(rs.getInt("REVIEW_NUM"));
				r.setBORROW_NUMBER(rs.getInt("BORROW_NUMBER"));
				r.setSTUDENT_ID(rs.getInt("STUDENT_ID"));
				r.setBOOK_NUMBER(rs.getInt("BOOK_NUMBER"));
				r.setRATING(rs.getInt("RATING"));

				reviews.add(r);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return reviews;
	}
}
