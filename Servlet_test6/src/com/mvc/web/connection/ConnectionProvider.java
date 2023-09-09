package com.mvc.web.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {
	public static Connection getConnection() {
		Connection con = null;
		
		try {
			 String url = "jdbc:mysql://192.168.0.78:3306/servlet_test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			 String id = "mysqluser";
			 String pass = "111111";
			 String driver = "com.mysql.jdbc.Driver";
			 Class.forName(driver);
			 con = DriverManager.getConnection(url, id, pass);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
