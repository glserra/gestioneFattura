package it.exp75.gestionefatture.test;

import java.util.regex.Pattern;

public class EmailValidator {
	
	public static boolean validate(final String emailAddress) {
	    if ( emailAddress == null ) return false;
	    String patternStr = "^[-!#$%&'*+/0-9=?A-Z^_a-z{|}~](\\.?[-!#$%&'*+/0-9=?A-Z^_a-z{|}~])*@[a-zA-Z](-?[a-zA-Z0-9])*(\\.[a-zA-Z](-?[a-zA-Z0-9])*)+$";
	    Pattern emailPattern = Pattern.compile(patternStr);
	    return emailPattern.matcher(emailAddress).matches();
	  }
}
