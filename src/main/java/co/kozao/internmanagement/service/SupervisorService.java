package co.kozao.internmanagement.service;

import co.kozao.internmanagement.model.Supervisor;

public interface SupervisorService {
	
	void register(Supervisor supervisor);
	
	Supervisor authentificate( String login , String password);
}
