package co.kozao.internmanagement.service;

import java.util.List;

import co.kozao.internmanagement.model.Intern;

public interface InternManagementService {
	
	List<Intern> getAllInterns();
	
	void addIntern(Intern intern);
	
	void getInternById(int id);
	
	void updateIntern(Intern intern);
	
	void deleteIntern(int id);
	
	List<Intern> getSupervisorInterns(int supervisorId);
}
