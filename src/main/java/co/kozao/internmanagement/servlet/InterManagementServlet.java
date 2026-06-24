package co.kozao.internmanagement.servlet;

import co.kozao.internmanagement.service.InternManagementService;
import co.kozao.internmanagement.service.InternManagementServiceImpl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import co.kozao.internmanagement.model.Intern;
import co.kozao.internmanagement.model.Supervisor;
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

        if (session == null || session.getAttribute("supervisor") == null) {
            response.sendRedirect("login");
            return;
        }

        String action = request.getParameter("action");

        try {

            if ("delete".equals(action)) {

                int id = Integer.parseInt(request.getParameter("id"));
                service.remove(id);

                response.sendRedirect("interManagement");
                return;
            }

            if ("edit".equals(action)) {

                int id = Integer.parseInt(request.getParameter("id"));
                Intern internToEdit = service.getById(id);

                request.setAttribute("internToEdit", internToEdit);
            }

            List<Intern> listInterns = service.getAll();
            request.setAttribute("listeStagiaires", listInterns);

            request.getRequestDispatcher("dashboard.jsp")
                    .forward(request, response);

        } catch (Exception e) {

            request.setAttribute("error", e.getMessage());

            List<Intern> listInterns = service.getAll();
            request.setAttribute("listeStagiaires", listInterns);

            request.getRequestDispatcher("dashboard.jsp")
                    .forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("supervisor") == null) {
            response.sendRedirect("login");
            return;
        }

        Supervisor supervisor = (Supervisor) session.getAttribute("supervisor");
        String action = request.getParameter("action");

        try {

            if ("add".equals(action)) {

                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String email = request.getParameter("email");
                String groupStr = request.getParameter("group");
                String startDateStr = request.getParameter("startDate");
                String endDateStr = request.getParameter("endDate");

                LocalDate startDate = LocalDate.parse(startDateStr);
                LocalDate endDate = LocalDate.parse(endDateStr);
                int group = Integer.parseInt(groupStr);

                Intern intern = new Intern();
                intern.setName(name);
                intern.setSurname(surname);
                intern.setEmail(email);
                intern.setGroup(group);
                intern.setStartDate(startDate);
                intern.setEndDate(endDate);
                intern.setSupervisor(supervisor);

                service.register(intern);

                response.sendRedirect("interManagement");
                return;
            }

       
            if ("update".equals(action)) {

                int id = Integer.parseInt(request.getParameter("id"));

                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String email = request.getParameter("email");
                String groupStr = request.getParameter("group");
                String startDateStr = request.getParameter("startDate");
                String endDateStr = request.getParameter("endDate");

                LocalDate startDate = LocalDate.parse(startDateStr);
                LocalDate endDate = LocalDate.parse(endDateStr);
                int group = Integer.parseInt(groupStr);

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

                response.sendRedirect("interManagement");
            }

        } catch (Exception e) {

            request.setAttribute("error", e.getMessage());

            List<Intern> listInterns = service.getAll();
            request.setAttribute("listeStagiaires", listInterns);

            request.getRequestDispatcher("dashboard.jsp")
                    .forward(request, response);
        }
    }
}