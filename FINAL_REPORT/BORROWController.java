package FINAL_REPORT;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

@WebServlet("/borrowController")
public class BORROWController extends HttpServlet {
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
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        String view = "";

        if (action == null) {
            getServletContext().getRequestDispatcher("/borrowController?action=list").forward(request, response);
        } else {
            switch (action) {
                case "list":
                    view = list(request, response);
                    break;
                case "insert":
                    view = insert(request, response);
                    break;
            }
            getServletContext().getRequestDispatcher("/FINAL_REPORT/" + view).forward(request, response);
        }
    }

    public String list(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("BORROW", borrowDAO.getAll());
        return "BORROWInfo.jsp";
    }

    public String insert(HttpServletRequest request, HttpServletResponse response) {
        BORROW b = new BORROW();
        try {
            BeanUtils.populate(b, request.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
        borrowDAO.insert(b);
        return list(request, response);
    }
}
