package SchoolManagement;
//xay dung cac ham ho tro ket noi database

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
	public static Connection getConnection() {
		String URL = "jdbc:mysql://127.0.0.1:3306/schoolmanagement";
		String UserName = "root";
		String Password = "";
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, UserName, Password);
//			System.out.println("Database exist");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Database not exist");
			e.printStackTrace();
		}
		return connection;
	}

	public static void CloseConnection(Connection connect) {
		try {
			connect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Database not exist");
			e.printStackTrace();
		}
	}
}

