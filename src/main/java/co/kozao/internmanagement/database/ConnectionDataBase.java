package co.kozao.internmanagement.database;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import co.kozao.internanagement.exception.ConfiguartionDatabasseException;

public class ConnectionDataBase {

	private static String url;
	private static String username;
	private static String password;

	private static ConnectionDataBase instance;

	private ConnectionDataBase() {
	}

	public static ConnectionDataBase getInstance() {

		if (instance == null) {

			Properties properties = new Properties();

			try (InputStream is = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("application.properties")) {

				properties.load(is);

				url = properties.getProperty("url");
				username = properties.getProperty("nomutilisateur");
				password = properties.getProperty("motdepasse");

				Class.forName(properties.getProperty("driver"));

			} catch (Exception e) {
				throw new RuntimeException("Erreur config DB", e);
			}

			instance = new ConnectionDataBase();
		}

		return instance;
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
}
