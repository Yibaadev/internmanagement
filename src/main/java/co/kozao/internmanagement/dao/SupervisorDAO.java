package co.kozao.internmanagement.dao;

import co.kozao.internmanagement.model.Supervisor;

public interface SupervisorDAO {
	
	void save(Supervisor supervisor);

	Supervisor findByLogin(String login);
	
	Supervisor login(String login , String password);
	
}
