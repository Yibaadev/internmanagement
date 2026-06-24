package co.kozao.internmanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.mindrot.jbcrypt.BCrypt;

import co.kozao.internmanagement.database.ConnectionDataBase;
import co.kozao.internmanagement.model.Supervisor;

public class SupervisorDAOimpl implements SupervisorDAO {
	private static final String SQL_INSERT = "INSERT INTO supervisor (login , password) VALUES (?, ?)";
	private static final String SQL_SELECT = "SELECT * FROM supervisor WHERE login =?";

	@Override
	public void save(Supervisor supervisor) {
		try {

			Connection conn = ConnectionDataBase.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQL_INSERT);
			ps.setString(1, supervisor.getLogin());
			ps.setString(2, supervisor.getPassword());

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Supervisor findByLogin(String login) {

		try {

			Connection conn = ConnectionDataBase.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQL_SELECT);
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				return new Supervisor(rs.getLong("id"), rs.getString("login"), rs.getString("password"));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	
	@Override
	public Supervisor login(String login, String password) {
	    // 1. On cherche l'utilisateur uniquement par son login (on réutilise SQL_SELECT)
	    try {
	        Connection conn = ConnectionDataBase.getInstance().getConnection();
	        PreparedStatement ps = conn.prepareStatement(SQL_SELECT); // Utilisation de SQL_SELECT au lieu de SQL_SELECT_LOGIN
	        ps.setString(1, login);
	        ResultSet rs = ps.executeQuery();

		try {
			Connection conn = ConnectionDataBase.getConnection();

			PreparedStatement ps = conn.prepareStatement(SQL_SELECT);

			ps.setString(1, login);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				String hashedPassword = rs.getString("password");

				if (BCrypt.checkpw(password, hashedPassword)) {

					return new Supervisor(rs.getLong("id"), rs.getString("login"), hashedPassword);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
