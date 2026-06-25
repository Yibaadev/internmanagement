package co.kozao.internmanagement.service;

import java.util.List;

import co.kozao.internmanagement.dao.InternManagementDao;
import co.kozao.internmanagement.dao.InternManagementDaoImpl;
import co.kozao.internmanagement.model.Intern;

public class InternManagementServiceImpl implements InternManagementService {

	private final InternManagementDao dao;

	public InternManagementServiceImpl() {
		this.dao = new InternManagementDaoImpl();
	}

	@Override
	public void register(Intern intern) {

		validateIntern(intern);

		Intern existingIntern = dao.findByEmail(intern.getEmail());

		if (existingIntern != null) {
			throw new IllegalArgumentException("Un stagiaire avec cet email existe déjà.");
		}

		dao.create(intern);
	}

	@Override
	public List<Intern> getAll() {
		return dao.readAll();
	}

	@Override
	public Intern getById(int id) {

		if (id <= 0) {
			throw new IllegalArgumentException("Identifiant invalide.");
		}

		Intern intern = dao.read(id);

		if (intern == null) {
			throw new IllegalArgumentException("Aucun stagiaire trouvé avec l'identifiant " + id);
		}

		return intern;
	}

	@Override
	public void modify(Intern intern) {

		if (intern == null) {
			throw new IllegalArgumentException("entrer les nouveaux attributs pour le stagiaire.");
		}

		if (intern.getId() <= 0) {
			throw new IllegalArgumentException("Identifiant invalide.");
		}

		validateIntern(intern);

		Intern existingIntern = dao.findByEmail(intern.getEmail());

		if (existingIntern != null && existingIntern.getId() != intern.getId()) {

			throw new IllegalArgumentException("Cet email est déjà utilisé par un autre stagiaire.");
		}

		dao.update(intern);
	}

	@Override
	public void remove(int id) {

		if (id <= 0) {
			throw new IllegalArgumentException("Identifiant invalide.");
		}


		dao.delete(id);
	}

	private void validateIntern(Intern intern) {

		if (intern == null) {
			throw new IllegalArgumentException("Le stagiaire est obligatoire.");
		}

		if (intern.getName() == null || intern.getName().trim().isEmpty()) {
			throw new IllegalArgumentException("Le nom est obligatoire.");
		}

		if (intern.getSurname() == null || intern.getSurname().trim().isEmpty()) {
			throw new IllegalArgumentException("Le prénom est obligatoire.");
		}

		if (intern.getEmail() == null || intern.getEmail().trim().isEmpty()) {
			throw new IllegalArgumentException("L'email est obligatoire.");
		}

		if (intern.getStartDate() == null) {
			throw new IllegalArgumentException("La date de début est obligatoire.");
		}

		if (intern.getEndDate() == null) {
			throw new IllegalArgumentException("La date de fin est obligatoire.");
		}

		if (intern.getStartDate().isAfter(intern.getEndDate())) {
			throw new IllegalArgumentException("La date de début doit être antérieure à la date de fin.");
		}

		if (intern.getGroup() <= 0) {
			throw new IllegalArgumentException("Le groupe doit être supérieur à zéro.");
		}

		if (intern.getSupervisor() == null) {
			throw new IllegalArgumentException("Le superviseur est obligatoire.");
		}
	}

	@Override
	public List<Intern> getBySupervisor(long supervisorId) {

		return dao.findBySupervisor(supervisorId);
	}

	@Override
	public Intern getByIdAndSupervisor(int internId, long supervisorId) {

		if (internId <= 0) {
			throw new IllegalArgumentException("Identifiant stagiaire invalide.");
		}

		if (supervisorId <= 0) {
			throw new IllegalArgumentException("Identifiant superviseur invalide.");
		}

		Intern intern = dao.findByIdAndSupervisor(internId, supervisorId);

		if (intern == null) {
			throw new IllegalArgumentException("Ce stagiaire n'existe pas ou ne vous appartient pas.");
		}

		return intern;
	}
}