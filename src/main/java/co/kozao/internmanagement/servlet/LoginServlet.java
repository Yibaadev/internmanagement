package co.kozao.internmanagement.servlet;

import java.io.IOException;

import co.kozao.internmanagement.dao.SupervisorDAO;
import co.kozao.internmanagement.dao.SupervisorDAOimpl;
import co.kozao.internmanagement.model.Supervisor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private SupervisorDAO supervisorDAO = new SupervisorDAOimpl();
	private static final long serialVersionUID = 1L;

   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 	HttpSession session = request.getSession(false);
	 	
	 	if( session != null && session.getAttribute("supervisor") != null) {
	 		response.sendRedirect("dashboard.jsp");
	 		return ;
	 	}
	 	
	 	request.getRequestDispatcher("login.jsp").forward(request, response);
	 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		Supervisor supervisor = supervisorDAO.login(login, password);
		
		if(supervisor != null) {
			HttpSession session = request.getSession();
			
			session.setAttribute("supervisor", supervisor);
			
			response.sendRedirect("dashboard.jsp");
		}else {
			
			request.setAttribute("ERREUR", "login ou mot de pasee incorecct");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
