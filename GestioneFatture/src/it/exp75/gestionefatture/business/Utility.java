package it.exp75.gestionefatture.business;

public class Utility {
	
	/*
	 * 
	 * ************************************************
	 * Funzione per il controllo del codice fiscale.
	 * Linguaggio: Java.
	 * Versione del: 2002-07-07
	 * ************************************************
	 *
	 */

	public static String ControllaCF(String cf) {
		int i, s, c;
		String cf2;
		int setdisp[] = {1, 0, 5, 7, 9, 13, 15, 17, 19, 21, 2, 4, 18, 20,
				11, 3, 6, 8, 12, 14, 16, 10, 22, 25, 24, 23 };
		if( cf.length() == 0 ) return "";
		if( cf.length() != 16 )
			return IConstanti.CF_ERRORE_LUNGHEZZA;
		cf2 = cf.toUpperCase();
		for( i=0; i<16; i++ ){
			c = cf2.charAt(i);
			if( ! ( c>='0' && c<='9' || c>='A' && c<='Z' ) )
				return IConstanti.CF_ERRORE_CARATTERI_NON_VALIDI;
		}
		s = 0;
		for( i=1; i<=13; i+=2 ){
			c = cf2.charAt(i);
			if( c>='0' && c<='9' )
				s = s + c - '0';
			else
				s = s + c - 'A';
		}
		for( i=0; i<=14; i+=2 ){
			c = cf2.charAt(i);
			if( c>='0' && c<='9' )	 c = c - '0' + 'A';
			s = s + setdisp[c - 'A'];
		}
		if( s%26 + 'A' != cf2.charAt(15) )
			return IConstanti.CF_ERRORE_CODICE_CONTROLLO;
		return "";
	}

	/*
	 * ***************************************************
	 * Funzione per il controllo della partita IVA.
	 * Linguaggio: Java.
	 * Versione del: 2002-07-07
	 * ****************************************************
	 */

	public static String ControllaPIVA(String pi)
	{
		int i, c, s;
		if( pi.length() == 0 )  return "";
		if( pi.length() != 11 )
			return IConstanti.PIVA_ERRORE_LUNGHEZZA;
		for( i=0; i<11; i++ ){
			if( pi.charAt(i) < '0' || pi.charAt(i) > '9' )
				return IConstanti.PIVA_ERRORE_CARATTERI_NON_VALIDI;
		}
		s = 0;
		for( i=0; i<=9; i+=2 )
			s += pi.charAt(i) - '0';
		for( i=1; i<=9; i+=2 ){
			c = 2*( pi.charAt(i) - '0' );
			if( c > 9 )  c = c - 9;
			s += c;
		}
		if( ( 10 - s%10 )%10 != pi.charAt(10) - '0' )
			return IConstanti.PIVA_ERRORE_CODICE_CONTROLLO;
		return "";
	}
	
	public static boolean checkRequiredFieldEmpty(String txt) {
		if(txt.trim().length() == 0 || txt.equals("")) {
			return true;
		} else {
			return false;
		}
	}

}
