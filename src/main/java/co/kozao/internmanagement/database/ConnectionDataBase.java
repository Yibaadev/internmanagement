package co.kozao.internmanagement.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import co.kozao.internanagement.exception.ConfiguartionDatabasseException;
import co.kozao.internmanagement.dao.SupervisorDAO;
import co.kozao.internmanagement.dao.SupervisorDAOimpl;
import co.kozao.internmanagement.model.Supervisor;

public class ConnectionDataBase {
	private static final String FICHIER_PROPERTIES = "application.properties";
	private static final String PROPERTY_URL = "url";
	private static final String PROPERTY_DRIVER = "driver";
	private static final String PROPERTY_NOM_UTILISATEUR = "nomutilisateur";
	private static final String PROPERTY_MOT_DE_PASSE = "motdepasse";

	private static   String url;
	private static   String username;
	private static   String password;

	public ConnectionDataBase(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	public static ConnectionDataBase getInstance() throws ConfiguartionDatabasseException {
		Properties properties = new Properties();
		String url;
		String driver;
		String nomUtilisateur;
		String motDePasse;

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream fichierProperties = classLoader.getResourceAsStream(FICHIER_PROPERTIES);
		if (fichierProperties == null) {
			throw new ConfiguartionDatabasseException(
					"Le fichier properties " + FICHIER_PROPERTIES + " est introuvable.");
		}

		try {
			properties.load(fichierProperties);
			url = properties.getProperty(PROPERTY_URL);
			driver = properties.getProperty(PROPERTY_DRIVER);
			nomUtilisateur = properties.getProperty(PROPERTY_NOM_UTILISATEUR);
			motDePasse = properties.getProperty(PROPERTY_MOT_DE_PASSE);
		} catch (IOException e) {
			throw new ConfiguartionDatabasseException(
					"Impossible de charger le fichier properties " + FICHIER_PROPERTIES, e);

		}
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new ConfiguartionDatabasseException("Le driver est introuvable dans le classpath.", e);
		}
		ConnectionDataBase instance = new ConnectionDataBase(url, nomUtilisateur, motDePasse);
		
		return instance;
	}

	/*
	 * Méthode chargée de fournir une connexion à la base de données
	 */
	/* package */
	public static  Connection getConnection() throws SQLException {
	    Connection connection = DriverManager.getConnection(url, username, password);
	    System.out.println("Connexion à la base de données réussie !");
	    return connection;
	}
	
	/*
	 * Méthodes de récupération de l'implémentation des différents DAO (un seul pour
	 * le moment)
	 */

	
}
