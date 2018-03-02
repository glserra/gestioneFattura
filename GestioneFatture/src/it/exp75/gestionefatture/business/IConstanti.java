package it.exp75.gestionefatture.business;

public interface IConstanti {

	/*
	 * Stringhe per errori controllo Codice Fiscale
	 */
	static final int CF_ERRORE_LUNGHEZZA_KEY = 1;
	static final int CF_ERRORE_CARATTERI_NON_VALIDI_KEY = 2;
	static final int CF_ERRORE_CODICE_CONTROLLO_KEY = 3;
	
	static final String CF_ERRORE_LUNGHEZZA = "La lunghezza del codice fiscale non è corretta:\nil codice fiscale dovrebbe essere lungo esattamente 16 caratteri.";
	static final String CF_ERRORE_CARATTERI_NON_VALIDI = "Il codice fiscale contiene dei caratteri non validi:\ni soli caratteri validi sono le lettere e le cifre.";
	static final String CF_ERRORE_CODICE_CONTROLLO = "Il codice fiscale non è corretto:\nil codice di controllo non corrisponde.";
	
}
