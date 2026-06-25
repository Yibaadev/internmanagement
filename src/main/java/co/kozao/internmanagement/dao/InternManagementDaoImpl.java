package co.kozao.internmanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import co.kozao.internmanagement.database.ConnectionDataBase;
import co.kozao.internmanagement.model.Intern;
import co.kozao.internmanagement.model.Supervisor;

public class InternManagementDaoImpl implements InternManagementDao {

	@Override
	public void create(Intern intern) {

		String sql = "INSERT INTO Intern (name, surname, email, startDate, endDate, \"group\", supervisorId) VALUES (?,?,?,?,?,?,?)";
		try (Connection connection = ConnectionDataBase.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql)) {

			stmt.setString(1, intern.getName());
			stmt.setString(2, intern.getSurname());
			stmt.setString(3, intern.getEmail());
			stmt.setObject(4, intern.getStartDate());
			stmt.setObject(5, intern.getEndDate());
			stmt.setInt(6, intern.getGroup());
			stmt.setLong(7, intern.getSupervisor().getId());
			stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public Intern read(int id) {

		String sql = "SELECT * FROM Intern WHERE id = ? ";

		try (Connection connection = ConnectionDataBase.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {

				Supervisor supervisor = new Supervisor();
				supervisor.setId(rs.getInt("supervisorId"));

				return new Intern(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getString("email"),
						rs.getObject("startDate", LocalDate.class), rs.getObject("endDate", LocalDate.class),
						rs.getInt("group"), supervisor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Intern> readAll() {

		List<Intern> liste = new ArrayList<>();

		String sql = "SELECT * FROM Intern";

		try (Connection connection = ConnectionDataBase.getConnection();

				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {

				Supervisor supervisor = new Supervisor();
				supervisor.setId(rs.getInt("supervisorId"));

				liste.add(new Intern(rs.getInt("id"), rs.getString("name"), rs.getString("surname"),
						rs.getString("email"), rs.getObject("startDate", LocalDate.class),
						rs.getObject("endDate", LocalDate.class), rs.getInt("group"), supervisor));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return liste;
	}

	@Override
	public Intern findByEmail(String email) {

		String sql = "SELECT * FROM Intern WHERE email = ?";

		try (Connection connection = ConnectionDataBase.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql)) {

			stmt.setString(1, email);

			try (ResultSet rs = stmt.executeQuery()) {

				if (rs.next()) {

					Supervisor supervisor = new Supervisor();
					supervisor.setId(rs.getInt("supervisorId"));

					return new Intern(rs.getInt("id"), rs.getString("name"), rs.getString("surname"),
							rs.getString("email"), rs.getObject("startDate", LocalDate.class),
							rs.getObject("endDate", LocalDate.class), rs.getInt("group"), supervisor);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void update(Intern intern) {

		String sql = "UPDATE Intern SET name = ?, surname = ?, email = ?, startDate = ?, endDate = ?, \"group\" = ?, supervisorId = ?  WHERE id = ?";

		try (Connection connection = ConnectionDataBase.getConnection();

				PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, intern.getName());
			stmt.setString(2, intern.getSurname());
			stmt.setString(3, intern.getEmail());
			stmt.setObject(4, intern.getStartDate());
			stmt.setObject(5, intern.getEndDate());
			stmt.setInt(6, intern.getGroup());
			stmt.setLong(7, intern.getSupervisor().getId());
			stmt.setInt(8, intern.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM Intern WHERE id = ?";

		try (Connection connection = ConnectionDataBase.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Intern> findBySupervisor(long supervisorId) {

		List<Intern> interns = new ArrayList<>();

		String sql = "SELECT * FROM Intern WHERE supervisorId = ?";

		try (Connection connection = ConnectionDataBase.getConnection();

				PreparedStatement stmt = connection.prepareStatement(sql)) {

			stmt.setLong(1, supervisorId);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Supervisor supervisor = new Supervisor();

				supervisor.setId(rs.getLong("supervisorId"));

				interns.add(new Intern(rs.getInt("id"), rs.getString("name"), rs.getString("surname"),
						rs.getString("email"), rs.getObject("startDate", LocalDate.class),
						rs.getObject("endDate", LocalDate.class), rs.getInt("group"), supervisor));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return interns;
	}

	@Override
	public Intern findByIdAndSupervisor(int internId, long supervisorId) {

		String sql = """
				SELECT *
				FROM Intern
				WHERE id = ?
				AND supervisorId = ?
				""";

		try (Connection connection = ConnectionDataBase.getConnection();

				PreparedStatement stmt = connection.prepareStatement(sql)) {

			stmt.setInt(1, internId);
			stmt.setLong(2, supervisorId);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				Supervisor supervisor = new Supervisor();

				supervisor.setId(rs.getLong("supervisorId"));

				return new Intern(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getString("email"),
						rs.getObject("startDate", LocalDate.class), rs.getObject("endDate", LocalDate.class),
						rs.getInt("group"), supervisor);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
