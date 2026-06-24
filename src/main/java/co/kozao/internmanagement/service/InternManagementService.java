package co.kozao.internmanagement.service;

import java.util.List;

import co.kozao.internmanagement.model.Intern;

public interface InternManagementService {
	
	void register(Intern intern);
	
    Intern getById(int id);
    
    List<Intern> getAll();
    
    void modify(Intern intern);
    
    void remove(int id);
}
