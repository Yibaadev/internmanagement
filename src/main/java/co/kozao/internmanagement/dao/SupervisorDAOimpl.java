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
	private static final String SQL_SELECT_LOGIN = "SELECT * FROM Supervisor WHERE login =? AND password =?";

	@Override
	public void save(Supervisor supervisor) {
		try {

			Connection conn = ConnectionDataBase.getInstance().getConnection();
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

			Connection conn = ConnectionDataBase.getInstance().getConnection();
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

	        // 2. CORRECTION : On appelle rs.next() pour se positionner sur la ligne trouvée
	        if (rs.next()) {
	            // On récupère le mot de passe haché stocké en BDD
	            String hashedPassword = rs.getString("password");

	            // 3. Vérification BCrypt : on compare le mot de passe saisi en clair avec le haché de la BDD
	            if (BCrypt.checkpw(password, hashedPassword)) {
	                // Si c'est correct, on crée et on renvoie le superviseur
	                return new Supervisor(rs.getLong("id"), rs.getString("login"), hashedPassword);
	            }
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    // Si l'utilisateur n'existe pas ou si le mot de passe est faux
	    return null; 
	}

}
