package it.exp75.gestionefatture.resources;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class Log {

	    static Handler fileHandler = null;
	    private static final Logger LOGGER = Logger.getLogger(Log.class
	            .getClass().getName());

	    public static void setup() {

	        try {
	            fileHandler = new FileHandler("./logfile.log");//file
	            SimpleFormatter simple = new SimpleFormatter();
	            fileHandler.setFormatter(simple);

	            LOGGER.addHandler(fileHandler);//adding Handler for file

	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	        }

	    }
	
}
