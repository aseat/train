package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDatabase {
	private Connection connection = null;

	public Statement getStatement() {
		try {
			if (connection == null) {
				connection = DriverManager.getConnection("jdbc:postgresql:postgres", "postgres", "E231callme");
			}
			return connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return null;
	}

	public void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
				connection = null;
			} catch (SQLException exception) {
				exception.printStackTrace();
			}
		}
	}
}
