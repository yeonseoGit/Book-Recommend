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
@WebServlet("/usagerController")
public class USAGERController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	USAGERDAO dao;
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = new USAGERDAO();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		String view = "";
		
		if(action == null) {
			getServletContext().getRequestDispatcher("/usagerController?action=list").forward(request, response);
		} else {
			switch(action) {
			case "list": view = list(request, response);break;
			case "insert": view = insert(request, response);break;
			}
			getServletContext().getRequestDispatcher("/FINAL_REPORT/"+view).forward(request, response);		
		}
	}
	
	public String list(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("USAGER", dao.getAll());
		return "USAGERInfo.jsp";		
	}
	
	public String insert(HttpServletRequest request, HttpServletResponse response) {
		USAGER u = new USAGER();
		try {
			BeanUtils.populate(u, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		dao.insert(u);
		return list(request, response);
	}
}