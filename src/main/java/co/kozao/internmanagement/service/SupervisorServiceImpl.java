package co.kozao.internmanagement.service;

import org.mindrot.jbcrypt.BCrypt;

import co.kozao.internmanagement.dao.SupervisorDAO;
import co.kozao.internmanagement.dao.SupervisorDAOimpl;
import co.kozao.internmanagement.model.Supervisor;

public class SupervisorServiceImpl implements SupervisorService  {
	
	 private SupervisorDAO supervisorDAO;
	 
	 public SupervisorServiceImpl() {
	        supervisorDAO = new SupervisorDAOimpl();
	    }
	@Override
	public void register(Supervisor supervisor) {
		
		if(supervisor == null) {
			throw new IllegalArgumentException("le superviseur est obligatoire");
		}
		
		if(supervisor.getLogin() == null || supervisor.getLogin().trim().isEmpty()) {
			throw new IllegalArgumentException("le login est obligatoire");
		}
		
		if(supervisor.getPassword() == null || supervisor.getPassword().trim().isEmpty() ) {
			throw new IllegalArgumentException("le mot de passe  est obligatoire");
		}
		
		if(supervisorDAO.findByLogin(supervisor.getLogin()) != null ) {
			throw new IllegalArgumentException("cet utilisateur existe déja");

		}
		
		String hashedPassword = BCrypt.hashpw(supervisor.getPassword(), BCrypt.gensalt());

        supervisor.setPassword(hashedPassword);
		supervisorDAO.save(supervisor);
		
	}

	@Override
	public Supervisor authentificate(String login, String password) {
		if(login == null || login.trim().isEmpty()) {
			throw new IllegalArgumentException("le login est obligatoire");

		}
		
		if(password == null || password.trim().isEmpty()) {
			throw new IllegalArgumentException("le mot de passe est obligatoire");

		}
		Supervisor supervisor = supervisorDAO.login(login, password);
		
		if(supervisor == null) {
			throw new IllegalArgumentException("login ou mot de passe incorecte");

		}

		return supervisor;
	}

}
