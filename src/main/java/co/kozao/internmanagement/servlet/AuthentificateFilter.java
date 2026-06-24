package co.kozao.internmanagement.servlet;

import java.io.IOException;

import co.kozao.internmanagement.model.Supervisor;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthentificateFilter implements Filter {

	@Override
	public void doFilter(jakarta.servlet.ServletRequest request, jakarta.servlet.ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		HttpServletResponse resp = (HttpServletResponse) response;

		String uri = req.getRequestURI();

		boolean publicResource = uri.endsWith("/login") || uri.endsWith("/inscription") || uri.contains("/css/")
				|| uri.contains("/js/") || uri.contains("/images/");

		if (publicResource) {
			chain.doFilter(request, response);
			return;
		}

		HttpSession session = req.getSession(false);

		Supervisor supervisor = null;

		if (session != null) {
			supervisor = (Supervisor) session.getAttribute("supervisor");
		}

		if (supervisor == null) {

			resp.sendRedirect(req.getContextPath() + "/login");

			return;
		}

		chain.doFilter(request, response);
	}
}