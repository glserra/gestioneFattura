package it.exp75.gestionefatture.business;

public interface IConstanti {

	/*
	 * Stringhe per errori controllo Codice Fiscale
	 */
	static final String CF_ERRORE_LUNGHEZZA = "La lunghezza del codice fiscale non � corretta: \nil codice fiscale dovrebbe essere lungo esattamente 16 caratteri.";
	static final String CF_ERRORE_CARATTERI_NON_VALIDI = "Il codice fiscale contiene dei caratteri non validi:\ni soli caratteri validi sono le lettere e le cifre.";
	static final String CF_ERRORE_CODICE_CONTROLLO = "Il codice fiscale non � corretto: \nil codice di controllo non corrisponde.";
		
	/*
	 * Stringhe per errori controllo Partita IVA
	 */
	static final String PIVA_ERRORE_LUNGHEZZA =  "La lunghezza della partita IVA non � corretta: \nla partita IVA dovrebbe essere lunga esattamente 11 caratteri.";
	static final String PIVA_ERRORE_CARATTERI_NON_VALIDI = "La partita IVA contiene dei caratteri non ammessi:\n la partita IVA dovrebbe contenere solo cifre.";
	static final String PIVA_ERRORE_CODICE_CONTROLLO = "La partita IVA non � valida: \nil codice di controllo non corrisponde.";

	/*
	 * campi panel Cliente 
	 */
	static final String RAGIONE_SOCIALE = "Ragione Sociale";
	static final String PARTITA_IVA = "Partita IVA";
	static final String CODICE_FISCALE = "Codice Fiscale";
	static final String INDIRIZZO = "Indirizzo";
	static final String CAP = "CAP";
	static final String CITTA = "Citt\u00E0";
	static final String PROVINCIA = "Provincia";
	static final String NOTE = "Note";

	/*
	 * campi panel Prestazioni 
	 */
	static final String PRESTAZ_DESCRIZIONE = "Descrizione";
	static final String PRESTAZ_SEZIONE = "Sezione";
	static final String PRESTAZ_QUANTITA = "Quantit\\u00E0";
	static final String PRESTAZ_IMPORTO = "Importo";
	static final String PRESTAZ_IVA = "IVA";
	static final String PRESTAZ_UM = "UM";

	
	/*
	 * Liste
	 */
	static final String LISTA_FATTURE = "Lista Fatture";
	static final String LISTA_CLIENTI = "Lista Clienti";
	static final String LISTA_PRESTAZIONI = "Lista Prestazioni";
	
	/*
	 * Messaggi errore
	 */
	static final String ERRORE_RECUPERO_DATI = "Si � verificato un errore nel recupero dati: ";
	
	/*
	 * NOme oggetti
	 */
	static final String PANEL_FATTURE = "pnlFatture";
	static final String PANEL_CLIENTI = "pnlClienti";
	static final String PANEL_HOME = "pnlHome";
	
	/*
	 * varie
	 */
	
	static final int ROUND_VALUE = 2;
}
