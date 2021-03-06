package it.exp75.gestionefatture.business;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

public class ConfigOld {
	String result = "";
	InputStream inputStream;
 
	public static void main(String[] args) throws IOException {
		ConfigOld properties = new ConfigOld();
		properties.getPropValues();
	}
	
	public HashMap<String, String> getPropValues() throws IOException {
 
		HashMap<String, String> hash = null;
		
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
 
			hash = new HashMap<String,String>();
			hash.put("server","127.0.0.1");
			hash.put("port","3306");
			hash.put("user","root");
			hash.put("password","root");
			hash.put("database","gestionefatture");

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return hash;
	}
	
	
	public Properties getPropertiesValues() throws IOException {
		Properties props = new Properties();
		
		try {

			props = new Properties();
			String propFileName = "config.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				props.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
 

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return props;
	}
}
