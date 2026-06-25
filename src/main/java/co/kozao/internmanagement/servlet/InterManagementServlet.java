package co.kozao.internmanagement.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import co.kozao.internmanagement.model.Intern;
import co.kozao.internmanagement.model.Supervisor;
import co.kozao.internmanagement.service.InternManagementService;
import co.kozao.internmanagement.service.InternManagementServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/interManagement")
public class InterManagementServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final InternManagementService service = new InternManagementServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		Supervisor supervisor = (Supervisor) session.getAttribute("supervisor");

		String action = request.getParameter("action");

		try {

			if ("delete".equals(action)) {

				int id = Integer.parseInt(request.getParameter("id"));

				service.remove(id);
				service.remove(id);

				session.setAttribute("success", "Stagiaire supprimé avec succès.");

				response.sendRedirect("interManagement");
				return;
			}

			if ("edit".equals(action)) {

				int id = Integer.parseInt(request.getParameter("id"));

				Intern internToEdit = service.getByIdAndSupervisor(id, supervisor.getId());

				request.setAttribute("internToEdit", internToEdit);
			}

			List<Intern> listInterns = service.getBySupervisor(supervisor.getId());

			request.setAttribute("listeStagiaires", listInterns);

			request.getRequestDispatcher("dashboard.jsp").forward(request, response);

		} catch (Exception e) {

			request.setAttribute("error", e.getMessage());

			List<Intern> listInterns = service.getBySupervisor(supervisor.getId());

			request.setAttribute("listeStagiaires", listInterns);

			String success = (String) session.getAttribute("success");

			if (success != null) {

				request.setAttribute("success", success);

				session.removeAttribute("success");
			}
			request.getRequestDispatcher("dashboard.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		Supervisor supervisor = (Supervisor) session.getAttribute("supervisor");

		String action = request.getParameter("action");

		try {

			String name = request.getParameter("name");

			String surname = request.getParameter("surname");

			String email = request.getParameter("email");

			int group = Integer.parseInt(request.getParameter("group"));

			LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));

			LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));

			if ("add".equals(action)) {

				Intern intern = new Intern();

				intern.setName(name);
				intern.setSurname(surname);
				intern.setEmail(email);
				intern.setGroup(group);
				intern.setStartDate(startDate);
				intern.setEndDate(endDate);

				intern.setSupervisor(supervisor);

				service.register(intern);

				session.setAttribute("success", "Stagiaire enregistré avec succès.");

				response.sendRedirect("interManagement");

				return;
			}

			if ("update".equals(action)) {

				int id = Integer.parseInt(request.getParameter("id"));

				Intern intern = new Intern();

				intern.setId(id);
				intern.setName(name);
				intern.setSurname(surname);
				intern.setEmail(email);
				intern.setGroup(group);
				intern.setStartDate(startDate);
				intern.setEndDate(endDate);

				intern.setSupervisor(supervisor);

				service.modify(intern);

				session.setAttribute("success", "Stagiaire modifié avec succès.");

				response.sendRedirect("interManagement");

				return;
			}

		} catch (Exception e) {

			request.setAttribute("error", e.getMessage());

			List<Intern> listInterns = service.getBySupervisor(supervisor.getId());

			request.setAttribute("listeStagiaires", listInterns);

			request.getRequestDispatcher("dashboard.jsp").forward(request, response);
		}
	}
}