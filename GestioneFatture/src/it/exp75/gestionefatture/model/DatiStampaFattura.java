package it.exp75.gestionefatture.model;

import java.util.List;

public class DatiStampaFattura {

	private Intestazione intestazione;
	private Cliente cliente;
	private Fattura fattura;
	private List<Prestazione> listaPrestazioni;
	private Pagamento pagamento;
	private List<Misure> misure;
	
	public DatiStampaFattura(Intestazione intestazione, Cliente cliente, Fattura fattura,
			List<Prestazione> listaPrestazioni, Pagamento pagamento, List<Misure> misure) {
		super();
		this.intestazione = intestazione;
		this.cliente = cliente;
		this.fattura = fattura;
		this.listaPrestazioni = listaPrestazioni;
		this.pagamento = pagamento;
		this.misure = misure;
	}
	
	public Intestazione getIntestazione() {
		return intestazione;
	}

	public void setIntestazione(Intestazione intestazione) {
		this.intestazione = intestazione;
	}

	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Fattura getFattura() {
		return fattura;
	}
	public void setFattura(Fattura fattura) {
		this.fattura = fattura;
	}
	public List<Prestazione> getListaPrestazioni() {
		return listaPrestazioni;
	}
	public void setListaPrestazioni(List<Prestazione> listaPrestazioni) {
		this.listaPrestazioni = listaPrestazioni;
	}
	public Pagamento getPagamento() {
		return pagamento;
	}
	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	public List<Misure> getMisure() {
		return misure;
	}
	public void setMisure(List<Misure> misure) {
		this.misure = misure;
	}

	
	
}
