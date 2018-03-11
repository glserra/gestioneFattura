package it.exp75.gestionefatture.business;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DBConnection {
	
	private static Config configuration;

	public static Connection getConnection() throws SQLException{
		Connection con = null;

		setConfiguration(Config.getIstance());
		getConfiguration().loadConfig();

		if(con == null) {
			MysqlDataSource dataSource = new MysqlDataSource();

			dataSource.setServerName(getConfiguration().getDbHost());
			dataSource.setPortNumber(new Integer(getConfiguration().getDbPort()).intValue());
			dataSource.setUser(getConfiguration().getDbUser());
			dataSource.setPassword(getConfiguration().getDbPassword());
			dataSource.setDatabaseName(getConfiguration().getDbName());
			con = dataSource.getConnection();

		}
		return con;
	}
	
	public static Config getConfiguration() {
		return configuration;
	}

	public static void setConfiguration(Config config) {
		configuration = config;
	}

}
