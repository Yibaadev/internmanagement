package co.kozao.internmanagement.service;

import java.util.List;

import co.kozao.internmanagement.dao.InternManagementDao;
import co.kozao.internmanagement.dao.InternManagementDaoImpl;
import co.kozao.internmanagement.model.Intern;

public class InternManagementServiceImpl implements InternManagementService {
	
	private InternManagementDao internDao = new InternManagementDaoImpl();

	@Override
	public void register(Intern intern) {
		internDao.create(intern);
		
		
	}

	@Override
	public Intern getById(int id) {
		return internDao.read(id);
	}

	@Override
	public List<Intern> getAll() {
		return internDao.readAll();
	}

	@Override
	public void modify(Intern intern) {
		internDao.update(intern);
		
	}

	@Override
	public void remove(int id) {
		internDao.delete(id);
		
	}

}
