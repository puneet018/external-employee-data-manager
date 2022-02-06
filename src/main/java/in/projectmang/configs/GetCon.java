package in.projectmang.configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetCon {
	private GetCon() {
	}

	private static Connection con;
	static {
		try {
			if(con == null || con.isClosed()){
				Class.forName(DBIntializer.DRIVER);
				con = DriverManager.getConnection(DBIntializer.CON_STRING);
			}else{
				System.out.println("Connection Opened");
			}
			
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			System.out.println("Exception in GetCon");
		}

	}

	//@Bean
	public static Connection getCon() {
		
		return con;
	}
}
