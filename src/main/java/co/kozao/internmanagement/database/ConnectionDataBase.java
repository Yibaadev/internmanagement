package co.kozao.internmanagement.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionDataBase {

    private ConnectionDataBase() {
    }

    public static Connection getConnection() throws SQLException {

        String url = System.getenv("DB_URL");
        String username = System.getenv("DB_USERNAME");
        String password = System.getenv("DB_PASSWORD");

        if (url == null || username == null || password == null) {
            throw new RuntimeException(
                "Variables d'environnement manquantes : DB_URL, DB_USERNAME ou DB_PASSWORD"
            );
        }

        try {
            Class.forName("org.postgresql.Driver"); 
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(
                "Driver PostgreSQL introuvable. Vérifie la dépendance JDBC.",
                e
            );
        }

        return DriverManager.getConnection(url, username, password);
    }
}