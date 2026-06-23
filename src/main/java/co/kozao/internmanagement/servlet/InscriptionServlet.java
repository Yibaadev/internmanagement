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

/**
 * Servlet implementation class InscriptionServlet
 */
@WebServlet("/inscription")
public class InscriptionServlet extends HttpServlet {
	private SupervisorDAO supervisorDAO = new SupervisorDAOimpl();
	
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("inscription.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		  
		Supervisor supervisor = new Supervisor(login, password);
		
		supervisorDAO.save(supervisor);
		
		response.sendRedirect("login");
		
		
	
	}

}
