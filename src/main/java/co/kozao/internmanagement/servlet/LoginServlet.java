package co.kozao.internmanagement.servlet;

import java.io.IOException;

import co.kozao.internanagement.utils.SessionManager;
import co.kozao.internmanagement.dao.SupervisorDAO;
import co.kozao.internmanagement.dao.SupervisorDAOimpl;
import co.kozao.internmanagement.model.Supervisor;
import co.kozao.internmanagement.service.SupervisorService;
import co.kozao.internmanagement.service.SupervisorServiceImpl;
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
		HttpSession session = request.getSession(false);

		if (session != null && session.getAttribute("supervisor") != null) {
			response.sendRedirect("/interManagement");
			return;
		}

		request.getRequestDispatcher("login.jsp").forward(request, response);

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

			Supervisor supervisor = supervisorService.authentificate(login, password);

			if (SessionManager.isConnected(login)) {

				request.setAttribute("error", "Ce compte est déjà connecté.");
				request.setAttribute("login", login);
				request.getRequestDispatcher("login.jsp").forward(request, response);

				return;
			}

			HttpSession session = request.getSession();

			session.setAttribute("supervisor", supervisor);

			SessionManager.connect(login);

			response.sendRedirect("interManagement");

		} catch (Exception e) {

			request.setAttribute("error", e.getMessage());
			request.setAttribute("login", login);

			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
