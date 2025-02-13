package FINAL_REPORT;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

@WebServlet("/bookController")
public class BOOKController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private BOOKDAO bookDAO;
    private BORROWDAO borrowDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            String jdbcURL = "jdbc:oracle:thin:@localhost:1521:orcl";
            String jdbcUsername = "yeonseo";
            String jdbcPassword = "010713";
            Connection conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            bookDAO = new BOOKDAO(conn);
            borrowDAO = new BORROWDAO(conn);
        } catch (SQLException e) {
            e.printStackTrace(); // SQLException 메시지 출력
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        service(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        service(request, response);
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        String view = "";

        if (action == null) {
            action = "listBorrowedBooks";
        }

        switch (action) {
            case "list":
                view = list(request, response);
                break;
            case "insert":
                view = insert(request, response);
                break;
            case "listBorrowedBooks":
                view = listBorrowedBooks(request, response);
                break;
            default:
                view = listBorrowedBooks(request, response);
                break;
        }
        getServletContext().getRequestDispatcher("/FINAL_REPORT/" + view).forward(request, response);
    }

    public String list(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("BOOK", bookDAO.getAll());
        return "BOOKInfo.jsp";
    }

    public String insert(HttpServletRequest request, HttpServletResponse response) {
        BOOK b = new BOOK();
        try {
            BeanUtils.populate(b, request.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
        bookDAO.insert(b);
        return list(request, response);
    }

    public String listBorrowedBooks(HttpServletRequest request, HttpServletResponse response) {
        List<BORROW> borrowList = null;
        try {
            borrowList = borrowDAO.getAllBorrowedBooksWithBookNames();
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("borrowList", borrowList);
        return "bookList.jsp";
    }
}
