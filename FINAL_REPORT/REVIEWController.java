package FINAL_REPORT;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

/**
 * Servlet implementation class StudentController
 */
@WebServlet("/reviewController")
public class REVIEWController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	REVIEWDAO dao;
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = new REVIEWDAO();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		String view = "";
		
		if(action == null) {
			getServletContext().getRequestDispatcher("/reviewController?action=list").forward(request, response);
		} else {
			switch(action) {
			case "list": view = list(request, response);break;
			case "insert": view = insert(request, response);break;
			}
			getServletContext().getRequestDispatcher("/FINAL_REPORT/"+view).forward(request, response);		
		}
	}
	
	public String list(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("REVIEW", dao.getAll());
		return "REVIEWInfo.jsp";		
	}
	
	public String insert(HttpServletRequest request, HttpServletResponse response) {
		REVIEW r = new REVIEW();
		try {
			BeanUtils.populate(r, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		dao.insert(r);
		return list(request, response);
	}
}