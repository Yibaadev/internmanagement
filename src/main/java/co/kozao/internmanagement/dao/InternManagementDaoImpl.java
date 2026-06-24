package co.kozao.internmanagement.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import co.kozao.internmanagement.database.ConnectionDataBase;
import co.kozao.internmanagement.model.*;

public class InternManagementDaoImpl implements InternManagementDao {

	@Override
	public void create(Intern intern) {

		String sql = "INSERT INTO Intern (name, surname, email, startDate, endDate, \"group\", supervisorId) VALUES (?,?,?,?,?,?,?)";
		try (Connection connection = ConnectionDataBase
				        .getInstance()
				        .getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql)) {
			
			stmt.setString(1, intern.getName());
			stmt.setString(2, intern.getSurname());
			stmt.setString(3, intern.getEmail());
			stmt.setString(4, intern.getStartDate());
			stmt.setString(5, intern.getEndDate());
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

		try (Connection connection = ConnectionDataBase
				.getInstance()
		        .getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {

				Supervisor supervisor = new Supervisor();
				supervisor.setId(rs.getInt("supervisorId"));
				
				return new Intern(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getString("email"),
						rs.getString("startDate"), rs.getString("endDate"), rs.getInt("group"), supervisor);
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

		try (Connection connection = ConnectionDataBase
				.getInstance()
		        .getConnection();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {

				Supervisor supervisor = new Supervisor();
				supervisor.setId(rs.getInt("supervisorId"));
				
				liste.add(new Intern(rs.getInt("id"), rs.getString("name"), rs.getString("surname"),
						rs.getString("email"), rs.getString("startDate"), rs.getString("endDate"), rs.getInt("group"),
						supervisor));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return liste;
	}

	@Override
	public void update(Intern intern) {

		String sql = "UPDATE Intern SET name = ?, surname = ?, email = ?, startDate = ?, endDate = ?, \"group\" = ?, supervisorId = ?  WHERE id = ?";
		
		try(Connection connection = ConnectionDataBase
				.getInstance()
		        .getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setString(1, intern.getName());
			stmt.setString(2, intern.getSurname());
			stmt.setString(3, intern.getEmail());
			stmt.setString(4, intern.getStartDate());
			stmt.setString(5, intern.getEndDate());
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
		
		try(Connection connection = ConnectionDataBase
				.getInstance()
		        .getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
