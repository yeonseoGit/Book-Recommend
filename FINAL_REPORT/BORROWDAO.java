package FINAL_REPORT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BORROWDAO {
    Connection conn = null;
    PreparedStatement pstmt;
 
    final String JDBC_DRIVER = "oracle.jdbc.OracleDriver";
    final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:"; // 'orcl'은 예시 SID입니다.
    public BORROWDAO(Connection conn) {
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
            if (pstmt != null && !pstmt.isClosed()) {
                pstmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(BORROW o) {
        open();
        String sql = "INSERT INTO BORROW(TODAY_DATE, BORROW_NUMBER, BOOK_NUMBER, STUDENT_ID, DUE_DATE) values(?,?,?,?,?)";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, o.getTODAY_DATE());
            pstmt.setInt(2, o.getBORROW_NUMBER());
            pstmt.setInt(3, o.getBOOK_NUMBER());
            pstmt.setInt(4, o.getSTUDENT_ID());
            pstmt.setString(5, o.getDUE_DATE());

            pstmt.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public List<BORROW> getAll() {
        open();
        List<BORROW> reviews = new ArrayList<>();

        try {
            pstmt = conn.prepareStatement("select * from BORROW");
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                BORROW o = new BORROW();
                o.setTODAY_DATE(rs.getString("TODAY_DATE"));
                o.setBORROW_NUMBER(rs.getInt("BORROW_NUMBER"));
                o.setSTUDENT_ID(rs.getInt("STUDENT_ID"));
                o.setBOOK_NUMBER(rs.getInt("BOOK_NUMBER"));
                o.setDUE_DATE(rs.getString("DUE_DATE"));

                reviews.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return reviews;
    }
    public List<BORROW> getAllBorrowedBooksWithBookNames() {
        open();
        List<BORROW> borrowList = new ArrayList<>();
        String sql = "SELECT br.BORROW_NUMBER, br.BOOK_NUMBER, br.STUDENT_ID, br.DUE_DATE, br.TODAY_DATE, b.BOOK_NAME " +
                     "FROM BORROW br " +
                     "JOIN BOOK b ON br.BOOK_NUMBER = b.BOOK_NUMBER";
        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                BORROW borrow = new BORROW();
                borrow.setBORROW_NUMBER(rs.getInt("BORROW_NUMBER"));
                borrow.setBOOK_NUMBER(rs.getInt("BOOK_NUMBER"));
                borrow.setSTUDENT_ID(rs.getInt("STUDENT_ID"));
                borrow.setDUE_DATE(rs.getString("DUE_DATE"));
                borrow.setTODAY_DATE(rs.getString("TODAY_DATE"));
                borrow.setBOOK_NAME(rs.getString("BOOK_NAME")); // BOOK_NAME 설정
                borrowList.add(borrow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return borrowList;
    }
}
