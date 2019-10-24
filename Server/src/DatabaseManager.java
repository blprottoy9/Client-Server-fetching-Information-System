import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DatabaseManager {
	Connection connection;
	ResultSet resultSet = null;
	public DatabaseManager() {
		connection = DBHandler.connect();
	}

	public void statusData(String queryCondition) throws SQLException {
		
		PreparedStatement preparedStatement = null;

		String query = "select * from Question_answer where "+queryCondition;

		try {
			preparedStatement = connection.prepareStatement(query);

			resultSet = preparedStatement.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}




}
