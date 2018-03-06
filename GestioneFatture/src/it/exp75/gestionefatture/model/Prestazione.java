package it.exp75.gestionefatture.model;

public class Prestazione {

	private Integer id;
	private Integer id_fattura;
	private String sezione;
	private String descrizione;
	private Integer unita_misura;
	private Double quantita;
	private Double importo;
	private Integer iva;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId_fattura() {
		return id_fattura;
	}
	public void setId_fattura(Integer id_fattura) {
		this.id_fattura = id_fattura;
	}
	public String getSezione() {
		return sezione;
	}
	public void setSezione(String sezione) {
		this.sezione = sezione;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public Integer getUnita_misura() {
		return unita_misura;
	}
	public void setUnita_misura(Integer unita_misura) {
		this.unita_misura = unita_misura;
	}
	public Double getQuantita() {
		return quantita;
	}
	public void setQuantita(Double quantita) {
		this.quantita = quantita;
	}
	public Double getImporto() {
		return importo;
	}
	public void setImporto(Double importo) {
		this.importo = importo;
	}
	public Integer getIva() {
		return iva;
	}
	public void setIva(Integer iva) {
		this.iva = iva;
	}
	
	
}
