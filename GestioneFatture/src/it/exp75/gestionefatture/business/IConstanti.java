package it.exp75.gestionefatture.business;

public interface IConstanti {

	/*
	 * Stringhe per errori controllo Codice Fiscale
	 */
	static final String CF_ERRORE_LUNGHEZZA = "La lunghezza del codice fiscale non è corretta:\nil codice fiscale dovrebbe essere lungo esattamente 16 caratteri.";
	static final String CF_ERRORE_CARATTERI_NON_VALIDI = "Il codice fiscale contiene dei caratteri non validi:\ni soli caratteri validi sono le lettere e le cifre.";
	static final String CF_ERRORE_CODICE_CONTROLLO = "Il codice fiscale non è corretto:\nil codice di controllo non corrisponde.";
		
	/*
	 * Stringhe per errori controllo Partita IVA
	 */
	static final String PIVA_ERRORE_LUNGHEZZA =  "La lunghezza della partita IVA non è corretta:\nla partita IVA dovrebbe essere lunga esattamente 11 caratteri.";
	static final String PIVA_ERRORE_CARATTERI_NON_VALIDI = "La partita IVA contiene dei caratteri non ammessi:\n la partita IVA dovrebbe contenere solo cifre.";
	static final String PIVA_ERRORE_CODICE_CONTROLLO = "La partita IVA non è valida:\nil codice di controllo non corrisponde.";

	/*
	 * campi panel Cliente 
	 */
	static final String RAGIONE_SOCIALE = "Ragione Sociale";
	static final String PARTITA_IVA = "Partita IVA";
	static final String CODICE_FISCALE = "Codice Fiscale";
	static final String INDIRIZZO = "Indirizzo";
	static final String CAP = "CAP";
	static final String CITTA = "Città";
	static final String PROVINCIA = "Provincia";
	static final String NOTE = "NOte";

}
