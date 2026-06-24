package co.kozao.internmanagement.service;

import java.util.List;

import co.kozao.internmanagement.model.Supervisor;

public interface SupervisorService {
	
	void register(Supervisor supervisor);
	
	List<Supervisor> getAllSupervisors();
	
	Supervisor authentificate( String login , String password);
}
