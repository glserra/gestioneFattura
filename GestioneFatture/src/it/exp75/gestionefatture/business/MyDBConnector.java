package it.exp75.gestionefatture.business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class MyDBConnector {

	private static MyDBConnector instance;
	private Connection conn;
	private static Config configuration;
	
	private MyDBConnector() throws SQLException{
		
		setConfiguration(Config.getIstance());
		getConfiguration().loadConfig();

		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setServerName(getConfiguration().getDbHost());
		dataSource.setPortNumber(new Integer(getConfiguration().getDbPort()).intValue());
		dataSource.setUser(getConfiguration().getDbUser());
		dataSource.setPassword(getConfiguration().getDbPassword());
		dataSource.setDatabaseName(getConfiguration().getDbName());
		conn = dataSource.getConnection();

	}
	
	public static MyDBConnector getConnector() throws SQLException{
		if(instance==null)
			instance = new MyDBConnector();
		return instance;
	}
	
	public Connection getConnention(){
		return conn;
	}
	
	public void close(){
		if(conn!=null)
			try{
				conn.close();
			}catch(Exception e){}
		instance = null;
	}
	
	
	public static Config getConfiguration() {
		return configuration;
	}

	public static void setConfiguration(Config config) {
		configuration = config;
	}

}


