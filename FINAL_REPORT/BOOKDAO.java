package FINAL_REPORT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BOOKDAO {
	Connection conn = null;
	PreparedStatement pstmt;

//	final String JDBC_DRIVER = "org.h2.Driver";
//	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/jwbookdb";
	
	final String JDBC_DRIVER = "oracle.jdbc.OracleDriver";
	final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:"; // 'orcl'은 예시 SID입니다.
	public BOOKDAO(Connection conn) {
        this.conn = conn;
    }
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
	
	public void insert(BOOK b) {
		open();
		String sql = "INSERT INTO BOOK(BOOK_NUMBER, BOOK_NAME, BOOK_COUNT, BOOK_CATEGORY) values(?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b.getBOOK_NUMBER());
			pstmt.setString(2, b.getBOOK_NAME());
			pstmt.setInt(3, b.getBOOK_COUNT());
			pstmt.setString(4, b.getBOOK_CATEGORY());
			
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public List<BOOK> getAll() {
		open();
		List<BOOK> books = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement("select * from BOOK");
			ResultSet rs = pstmt.executeQuery();
	
			while(rs.next()) {
				BOOK b = new BOOK();
				b.setBOOK_NUMBER(rs.getInt("BOOK_NUMBER"));
				b.setBOOK_NAME(rs.getString("BOOK_NAME"));
				b.setBOOK_COUNT(rs.getInt("BOOK_COUNT"));
				b.setBOOK_CATEGORY(rs.getString("BOOK_CATEGORY"));

				books.add(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return books;
	}
}
