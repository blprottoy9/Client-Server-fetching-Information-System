

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHandler {
	public static Connection connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection =
					DriverManager.getConnection
					("jdbc:sqlite:lib/questiion_answer.db");
			System.out.println("Connected");
			return connection;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
