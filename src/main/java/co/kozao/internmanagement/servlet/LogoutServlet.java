package co.kozao.internmanagement.servlet;

import java.io.IOException;

import co.kozao.internanagement.utils.SessionManager;
import co.kozao.internmanagement.model.Supervisor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session != null) {

			Supervisor supervisor = (Supervisor) session.getAttribute("supervisor");

			if (supervisor != null) {

				SessionManager.disconnect(supervisor.getLogin());
			}

			session.invalidate();
		}

		response.sendRedirect("login");
	}
}