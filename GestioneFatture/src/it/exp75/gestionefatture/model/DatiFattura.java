package it.exp75.gestionefatture.model;

import java.util.List;

public class DatiFattura {

	private Cliente cliente;
	private Fattura fattura;
	private List<Prestazione> listaPrestazioni;
	private Pagamento pagamento;
	private List<Misure> misure;
	
	public DatiFattura(it.exp75.gestionefatture.model.Cliente cliente, Fattura fattura,
			List<Prestazione> listaPrestazioni, Pagamento pagamento, List<Misure> misure) {
		super();
		this.cliente = cliente;
		this.fattura = fattura;
		this.listaPrestazioni = listaPrestazioni;
		this.pagamento = pagamento;
		this.misure = misure;
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
