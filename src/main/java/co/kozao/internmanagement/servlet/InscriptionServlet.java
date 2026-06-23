package co.kozao.internmanagement.servlet;

import java.io.IOException;

import co.kozao.internmanagement.model.Supervisor;
import co.kozao.internmanagement.service.SupervisorService;
import co.kozao.internmanagement.service.SupervisorServiceImpl;
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
	private SupervisorService supervisorService;
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		supervisorService = new SupervisorServiceImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("inscription.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String login = request.getParameter("login");
		String password = request.getParameter("password");

		try {

			Supervisor supervisor = new Supervisor(login, password);

			supervisorService.register(supervisor);

			request.setAttribute("success", "Compte créé avec succès.");

			response.sendRedirect("login");

		} catch (Exception e) {

			request.setAttribute("error", e.getMessage());

			request.getRequestDispatcher("inscription.jsp").forward(request, response);
		}
	}

}
