package it.exp75.gestionefatture.business;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DBConnection {

	public static Connection getConnection() throws SQLException{
		Connection con = null;
		
		Config config = new Config();
		Properties props = new Properties();
		
		try {
			props = config.getPropertiesValues();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(con == null) {
			MysqlDataSource dataSource = new MysqlDataSource();

			dataSource.setServerName(props.getProperty("server"));
			dataSource.setPortNumber(new Integer(props.getProperty("port")).intValue());
			dataSource.setUser(props.getProperty("user"));
			dataSource.setPassword(props.getProperty("password"));
			dataSource.setDatabaseName(props.getProperty("database"));
			con = dataSource.getConnection();

		}
		return con;
	}
}
