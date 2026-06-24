package co.kozao.internmanagement.dao;
import java.util.List;

import co.kozao.internmanagement.model.*;

public interface InternManagementDao {
	
	void create(Intern intern);
	
	Intern read(int id);
	
	List<Intern> readAll();
	
	void update(Intern intern);
	
	void delete(int id);

	Intern findByEmail(String email);


	

}
