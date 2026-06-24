package co.kozao.internmanagement.servlet;

import co.kozao.internanagement.utils.SessionManager;
import co.kozao.internmanagement.model.Supervisor;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {

		Supervisor supervisor = (Supervisor) se.getSession().getAttribute("supervisor");

		if (supervisor != null) {

			SessionManager.disconnect(supervisor.getLogin());
		}
	}
}