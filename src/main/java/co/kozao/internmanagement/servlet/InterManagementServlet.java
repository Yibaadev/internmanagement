package co.kozao.internmanagement.servlet;


import co.kozao.internmanagement.service.InternManagementService;
import co.kozao.internmanagement.service.InternManagementServiceImpl;
import java.io.IOException;
import java.util.List;

import co.kozao.internmanagement.model.Intern;
import co.kozao.internmanagement.model.Supervisor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class InterManagementServlet
 */
@WebServlet("/interManagement")
public class InterManagementServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private InternManagementService service = new InternManagementServiceImpl();

	public void init() throws ServletException {
		

	}

	/**
	 * Default constructor.
	 */
	public InterManagementServlet() {

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("supervisor") == null) {

			response.sendRedirect("login");

			return;
		}
		
		List<Intern> listInterns = service.getAll();
		
	
		request.setAttribute("listeStagiaires", listInterns);
		
		request.getRequestDispatcher("dashboard.jsp").forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		Supervisor supervisor = (Supervisor) session.getAttribute("supervisor");

	    // 2. Identifier l'action envoyée par le formulaire hidden
	    String action = request.getParameter("action");

	    if ("add".equals(action)) {
	        try {
	            // 3. Récupération des données du formulaire JSP
	            String name = request.getParameter("name");
	            String surname = request.getParameter("surname");
	            String email = request.getParameter("email");
	            String startDate = request.getParameter("startDate");
	            String endDate = request.getParameter("endDate");
	            int group = Integer.parseInt(request.getParameter("group"));

	            // 4. Instanciation de l'objet Intern avec son Superviseur
	            Intern newIntern = new Intern();
	            newIntern.setName(name);
	            newIntern.setSurname(surname);
	            newIntern.setEmail(email);
	            newIntern.setStartDate(startDate);
	            newIntern.setEndDate(endDate);
	            newIntern.setGroup(group);
	            newIntern.setSupervisor(supervisor); // Liaison clé étrangère

	            // 5. Appel de la couche Service pour l'insertion en BDD
	            service.register(newIntern); // Ajustez selon le nom dans votre InternManagementService

	            // 6. Redirection vers le doGet pour rafraîchir et afficher la nouvelle liste
	            response.sendRedirect("interManagement");

	        } catch (Exception e) {
	            e.printStackTrace();
	            // En cas d'erreur (ex: problème de format de nombre), on recharge la page avec un message
	            request.setAttribute("error", "Erreur lors de l'ajout du stagiaire : " + e.getMessage());
	            doGet(request, response);
	        }
	    }
		

	}

}
