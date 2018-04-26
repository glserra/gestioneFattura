package it.exp75.gestionefatture.model;

import java.util.Date;

public class Fattura {
	private Integer id_fattura;
	private Integer id_cliente;
	private Integer num_fattura;
	private Date data_fattura;
	private Integer pagamento;
	private boolean pagata;
	private String note;
	
	
	
	
	
	public Integer getId_fattura() {
		return id_fattura;
	}
	public void setId_fattura(Integer id_fattura) {
		this.id_fattura = id_fattura;
	}
	public Integer getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}
	public Integer getNum_fattura() {
		return num_fattura;
	}
	public void setNum_fattura(Integer num_fattura) {
		this.num_fattura = num_fattura;
	}
	public Date getData_fattura() {
		return data_fattura;
	}
	public void setData_fattura(Date data_fattura) {
		this.data_fattura = data_fattura;
	}
	public Integer getPagamento() {
		return pagamento;
	}
	public void setPagamento(Integer pagamento) {
		this.pagamento = pagamento;
	}
	public boolean isPagata() {
		return pagata;
	}
	public void setPagata(boolean pagata) {
		this.pagata = pagata;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	

}
