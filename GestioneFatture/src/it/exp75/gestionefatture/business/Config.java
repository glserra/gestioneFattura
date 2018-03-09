package it.exp75.gestionefatture.business;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

import it.exp75.gestionefatture.resources.Log;

public class Config {
	
	private static final String DEFAULT_DB_HOST = "127.0.0.1";
	private static final String DEFAULT_DB_PORT = "3306";
	private static final String DEFAULT_DB_NAME = "gestionefatture";
	private static final String DEFAULT_DB_USER = "root";
	private static final String DEFAULT_DB_PASSWORD = "root"; 
	
	private static final String CONFIG_FILE_NAME = "config.properties";
	private static final String CONFIG_DB_HOST = "server";
	private static final String CONFIG_DB_PORT = "port";
	private static final String CONFIG_DB_NAME = "database";
	private static final String CONFIG_DB_USER = "user";
	private static final String CONFIG_DB_PASSWORD = "password"; 
	
	private String dbHost;
	private String dbPort;
	private String dbName;
	private String dbUser;
	private String dbPassword;
	
	private static Config config;
	
	private Properties configFile;
	
    private static final Logger LOGGER = Logger.getLogger(Config.class.getClass().getName());
	
	public static Config getIstance() {
		if(config == null) {
			config = new Config();
		}
		
		return config;
	}
	
	public static final String getProgramDir() {
		return System.getProperty("user.home") + "";
	}
	
	private void configure(String dbHost, String dbPort, String dbName, String dbUser, String dbPassword) {
		LOGGER.info("configure :: setup parametes");
		
		getIstance().setDbHost(dbHost);
		getIstance().setDbPort(dbPort);
		getIstance().setDbName(dbName);
		getIstance().setDbUser(dbUser);
		getIstance().setDbPassword(dbPassword);
		 
	}

	public boolean loadConfig() {
		LOGGER.info("loadConfig :: loading Configuration");
		
		
		configFile = new Properties();
		try {
			configFile.load(getConfigFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		if(configFile != null) {
			configure(
					configFile.getProperty(CONFIG_DB_HOST), 
					configFile.getProperty(CONFIG_DB_PORT),
					configFile.getProperty(CONFIG_DB_NAME),
					configFile.getProperty(CONFIG_DB_USER),
					configFile.getProperty(CONFIG_DB_PASSWORD)
					);
		} else {
			configure(
					DEFAULT_DB_HOST,
					DEFAULT_DB_PORT,
					DEFAULT_DB_NAME,
					DEFAULT_DB_USER,
					DEFAULT_DB_PASSWORD
					);
		}
		
		LOGGER.info("loadConfig :: loading Completed");
		
		return true;
	}
	
	private InputStream getConfigFile() throws FileNotFoundException {

		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE_NAME);

		if (inputStream == null) {
			throw new FileNotFoundException("property file '" + CONFIG_FILE_NAME + "' not found in the classpath");
		}
		
		return inputStream;
	}
	
	public String getDbHost() {
		return dbHost;
	}

	public void setDbHost(String dbHost) {
		this.dbHost = dbHost;
	}

	public String getDbPort() {
		return dbPort;
	}

	public void setDbPort(String dbPort) {
		this.dbPort = dbPort;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	
}
